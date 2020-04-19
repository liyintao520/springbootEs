import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName MyBill
 * @Description TODO
 * @Author liyintao
 * @Date 2020/4/5 13:14
 */
public class MyBill {
    //
    private static final Integer MY_GUANGFA = 109000;
    private static final Integer MY_MINSHENG = 48000;
    private static final Integer MY_ZHAOSHANG = 8000;
    private static final Integer MY_ZHIFUBAO = 69000;
    private static final Integer MY_WEIXIN = 72000;
    private static final Integer MY_PINGAN = 50000;
    private static final Integer MY_ZHONGXIN = 30000;

    //
    private static final Integer WIFE_GUANGFA = 33800;
    private static final Integer WIFE_XINGYE = 19000;

    private static Integer ZONGEDU = MY_GUANGFA + MY_MINSHENG + MY_ZHAOSHANG + MY_ZHIFUBAO + MY_WEIXIN + MY_PINGAN + MY_ZHONGXIN + WIFE_GUANGFA + WIFE_XINGYE;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        shouldReturn(33175,0,0,0,0, 0,0,0,0,0);
        totalArrears(47825,20099,8000,69000, 0,72000,50000,16100,16800,9000);
    }

    /**
     * 输入当前月份应还金额，求总应还数
     * @param myGf      我的广发--应还金额
     * @param myMs      我的民生--应还金额
     * @param myZs      我的招商--应还金额
     * @param myZfbJb     我的支付宝借呗--应还金额
     * @param myZfbHb     我的支付宝花呗--应还金额
     * @param myWx      我的微信--应还金额
     * @param myPa      我的平安信用卡--应还金额
     * @param myZx      我的中信信用卡--应还金额
     * @param wifeGf    妻子广发--应还金额
     * @param wifeXy    妻子兴业--应还金额
     * @return  求总应还
     */
    public static Integer shouldReturn (Integer myGf, Integer myMs, Integer myZs, Integer myZfbJb, Integer myZfbHb,
                                        Integer myWx, Integer myPa, Integer myZx, Integer wifeGf, Integer wifeXy) {
        Integer shouldReturn = myGf + myMs + myZs + myZfbJb + myZfbHb + myWx + myPa + myZx + wifeGf + wifeXy;
        System.out.println("当前时间：" + sdf.format(new Date()) + ", 我的【总应还】是：" + shouldReturn);
        return shouldReturn;
    }

    /**
     * 输入剩余额度，求总欠款
     * @param myGf      我的广发剩余额度
     * @param myMs      我的民生剩余额度
     * @param myZs      我的招商剩余额度
     * @param myZfbJb     我的支付宝借呗剩余额度
     * @param myZfbHb     我的支付宝花呗剩余额度
     * @param myWx      我的微信剩余额度
     * @param myPa      我的平安信用额度
     * @param myZx      我的中信信用额度
     * @param wifeGf    妻子广发剩余额度
     * @param wifeXy    妻子兴业剩余额度
     * @return  求总欠款
     */
    public static Integer totalArrears (Integer myGf, Integer myMs, Integer myZs, Integer myZfbJb, Integer myZfbHb,
            Integer myWx, Integer myPa, Integer myZx, Integer wifeGf, Integer wifeXy) {
        Integer totalArrears = ZONGEDU - (myGf + myMs + myZs + myZfbJb + myZfbHb + myWx + myPa + myZx + wifeGf + wifeXy);
        System.out.println("当前时间：" + sdf.format(new Date()) + ", 我的【总欠款】是：" + totalArrears);
        return totalArrears;
    }
}
