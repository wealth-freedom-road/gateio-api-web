package io.github.road.gateio.tookit;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BigDecimalUtil
 *
 * @author HaiBo 2017年5月31日 上午11:14:37
 */
public class BigDecimalUtil {

    /**
     * 费率计算方式：0 四舍五入
     */
    public static final String RATECALCULATION_HALFUP = "0";
    /**
     * 费率计算方式：1 向上取整
     */
    public static final String RATECALCULATION_UP = "1";

    /**
     * 四舍五入
     *
     * @param decimal
     * @return
     */
    public static BigDecimal roundingHalfUp(BigDecimal decimal) {
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }



    /**
     * 始终对非零舍弃部分前面的数字加1
     *
     * @param decimal
     * @return
     */
    public static BigDecimal roundingUp(BigDecimal decimal) {
        return decimal.setScale(2, BigDecimal.ROUND_UP);
    }

    /**
     * 以分为单位格式化
     *
     * @param decimal
     * @return
     */
    public static String toPennyFormat(BigDecimal decimal) {
        if (decimal == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        BigDecimal penny = decimal.multiply(new BigDecimal(100).setScale(0, BigDecimal.ROUND_HALF_UP));
        return new DecimalFormat("#0").format(penny);
    }

    /**
     * 根据费率计算方式获取费率
     *
     * @param decimal
     * @param rateCalculation
     * @return
     */
    public static BigDecimal getRate(BigDecimal decimal, String rateCalculation) {
        //四舍五入
        if (RATECALCULATION_HALFUP.equals(rateCalculation)) {
            return BigDecimalUtil.roundingHalfUp(decimal);
            //非零必进
        } else if (RATECALCULATION_UP.equals(rateCalculation)) {
            return BigDecimalUtil.roundingUp(decimal);
        }
        return BigDecimalUtil.roundingUp(decimal);
    }

    /**
     * 分转换为元.
     *
     * @param fen 分
     * @return 元
     */
    public static BigDecimal fromFenToYuan(final String fen) {
        String yuan = "";
        final int MULTIPLIER = 100;
        Pattern pattern = Pattern.compile("^[1-9][0-9]*{1}");
        Matcher matcher = pattern.matcher(fen);
        if (matcher.matches()) {
            yuan = new BigDecimal(fen).divide(new BigDecimal(MULTIPLIER)).setScale(2).toString();
        } else {
            System.out.println("参数格式不正确!");
        }

        return new BigDecimal(yuan);

    }

    /**
     * 转为人民币计价 小数点后两位 四舍五入
     *
     * @param usdt
     * @return
     */
    public static BigDecimal toCny(BigDecimal usdt) {
        final BigDecimal bigDecimal = new BigDecimal("6.6");
        final BigDecimal multiply = usdt.multiply(bigDecimal);
        return roundingHalfUp(multiply);
    }

    /**
     * 转为人民币计价，保留三位
     *
     * @param usdt
     * @return
     */
    public static BigDecimal toCnyAll(BigDecimal usdt) {
        final BigDecimal bigDecimal = new BigDecimal("6.6");
        final BigDecimal multiply = usdt.multiply(bigDecimal);
        return multiply.setScale(3, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 转为人民币计价
     *
     * @param usdt
     * @return
     */
    public static BigDecimal toCnyAll(String usdt) {
        final BigDecimal bigDecimal = new BigDecimal(usdt);
        return toCnyAll(bigDecimal);
    }

    /**
     * 转为人民币计价 小数点后两位 四舍五入
     *
     * @param usdt
     * @return
     */
    public static BigDecimal toCny(String usdt) {
        final BigDecimal bigDecimal = new BigDecimal(usdt);
        return toCny(bigDecimal);
    }


}
