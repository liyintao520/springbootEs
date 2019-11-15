package com.lyt.controller;

import com.lyt.entity.Book;
import com.lyt.entity.EsEntity;
import com.lyt.util.EsUtil;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BookService
 * @Description TODO
 * @Author liyintao
 * @date 2019/11/15 15:15
 */
@RestController
@RequestMapping("/book")
@Service
public class BookController {

    @Autowired
    private EsUtil esUtil;

    /**
     * @param id 获取某一个
     */
    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") int id) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
//        查询包含关键词字段的文档：如下，表示查询出来所有包含user字段且user字段包含kimchy值的文档
//        builder.query(QueryBuilders.termQuery("id", id));
        builder.query(new TermQueryBuilder("id", id));
        List<Book> res = esUtil.search(EsUtil.INDEX_NAME, builder, Book.class);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return null;
        }
    }
    /**
     * @param name 获取某一个
     * 使用MatchQueryBuilder配置查询参数
     */
    @GetMapping("/{name}")
    public Book getByName(@PathVariable("name") String name) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", name);
        // 启动模糊查询
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        // 在匹配查询上设置前缀长度选项
        matchQueryBuilder.prefixLength(3);
        // 设置最大扩展选项以控制查询的模糊过程
        matchQueryBuilder.maxExpansions(10);
        builder.query(matchQueryBuilder);
        List<Book> res = esUtil.search(EsUtil.INDEX_NAME, builder, Book.class);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return null;
        }
    }
    /**
     * 获取全部
     */
    @GetMapping("/")
    public List<Book> getAll() {
        return esUtil.search(EsUtil.INDEX_NAME, new SearchSourceBuilder(), Book.class);
    }

    /**
     * 根据关键词搜索某用户下的书
     *
     * @param content 关键词
     */
    @GetMapping("/search")
    public List<Book> searchByUserIdAndName(int userId, String content) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("userId", userId));
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", content));
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(10).query(boolQueryBuilder);
        return esUtil.search(EsUtil.INDEX_NAME, builder, Book.class);

    }

    /**
     * 单个插入
     *
     * @param book book
     */
    @PutMapping("/")
    public void putOne(@RequestBody Book book) {
        EsEntity<Book> entity = new EsEntity<>(book.getId().toString(), book);
        esUtil.insertOrUpdateOne(EsUtil.INDEX_NAME, entity);
    }

    /**
     * 批量插入
     *
     * @param books books
     */
    @PutMapping("/many")
    public void putList(@RequestBody List<Book> books) {
        List<EsEntity> list = new ArrayList<>();
        books.forEach(item -> list.add(new EsEntity<>(item.getId().toString(), item)));
        esUtil.insertBatch(EsUtil.INDEX_NAME, list);
    }

    /**
     * 批量删除
     *
     * @param list list
     */
    @DeleteMapping("/deleteBatch")
    public void deleteBatch(List<Integer> list) {
        esUtil.deleteBatch(EsUtil.INDEX_NAME, list);
    }

    /**
     * delete by query 根据用户id删除数据
     *
     * @param userId userId
     */
    @DeleteMapping("/userId/{userId}")
    public void deleteByUserId(@PathVariable("userId") int userId) {
        esUtil.deleteByQuery(EsUtil.INDEX_NAME, new TermQueryBuilder("userId", userId));
    }


}