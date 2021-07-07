package cn.bootx.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * BigDecimal相关的工具类
 *
 * @author network
 */
public class BigDecimalUtil {

    /**
     * 价钱保留几位小数
     */
    public static final int CURRENCY_DECIMAL_PLACES = 2;

    private BigDecimalUtil() {

    }

    /**
     * 加法
     *
     * @param first    加数
     * @param lastArgs 加数
     * @return 结果
     */
    public static BigDecimal add(BigDecimal first, BigDecimal... lastArgs) {

        int argsLength = lastArgs.length;
        BigDecimal result = new BigDecimal("0.00");
        if (first != null) {
            result = result.add(first);
        }
        for (int i = 0; i < argsLength; i++) {
            lastArgs[i] = lastArgs[i] == null ? new BigDecimal("0.00") : lastArgs[i];
            result = result.add(lastArgs[i]);
        }
        result = result.setScale(CURRENCY_DECIMAL_PLACES, BigDecimal.ROUND_UP);
        return result;
    }

    /**
     * 减法
     *
     * @param first    被减数
     * @param lastArgs 减数
     * @return 结果
     */
    public static BigDecimal subtract(BigDecimal first, BigDecimal... lastArgs) {

        int argsLength = lastArgs.length;
        BigDecimal result = new BigDecimal("0.00");
        if (first != null) {
            result = result.add(first);
        }
        for (int i = 0; i < argsLength; i++) {
            lastArgs[i] = lastArgs[i] == null ? new BigDecimal("0.00") : lastArgs[i];
            result = result.subtract(lastArgs[i]);
        }
        result = result.setScale(CURRENCY_DECIMAL_PLACES, BigDecimal.ROUND_UP);
        return result;
    }

    /**
     * 乘法
     *
     * @param first    乘数
     * @param lastArgs 乘数
     * @return 结果
     */
    public static BigDecimal multiply(BigDecimal first, BigDecimal... lastArgs) {

        int argsLength = lastArgs.length;
        BigDecimal result = new BigDecimal("0.00");
        if (first != null) {
            result = result.add(first);
        }
        for (int i = 0; i < argsLength; i++) {
            if (result.compareTo(new BigDecimal("0.00")) == 0) {
                return result;
            }
            lastArgs[i] = lastArgs[i] == null ? new BigDecimal("0.00") : lastArgs[i];
            result = result.multiply(lastArgs[i]);
        }
        result = result.setScale(CURRENCY_DECIMAL_PLACES, BigDecimal.ROUND_UP);
        return result;
    }

    /**
     * 除法
     *
     * @param first    被除数
     * @param lastArgs 除数
     * @return 结果
     */
    public static BigDecimal divide(BigDecimal first, BigDecimal... lastArgs) {

        int argsLength = lastArgs.length;
        BigDecimal result = new BigDecimal("0.00");
        if (first != null) {
            result = result.add(first);
        }
        for (int i = 0; i < argsLength; i++) {
            if (result.compareTo(new BigDecimal("0.00")) == 0) {
                return result;
            }
            lastArgs[i] = lastArgs[i] == null ? new BigDecimal("1.00") : lastArgs[i];
            result = result.divide(lastArgs[i], CURRENCY_DECIMAL_PLACES,
                    BigDecimal.ROUND_UP);
        }
        result = result.setScale(CURRENCY_DECIMAL_PLACES, BigDecimal.ROUND_UP);
        return result;
    }

    /**
     * 比较大小
     *
     * @param first 数字1
     * @param last  数字2
     * @return first gt last =1 / first eq last = 0 / first lt last = -1
     */
    public static int compareTo(BigDecimal first, BigDecimal last) {
        BigDecimal newFirst = BigDecimal.ZERO;
        BigDecimal newLast = BigDecimal.ZERO;
        if (first != null) {
            newFirst = first;
        }
        if (last != null) {
            newLast = last;
        }
        return newFirst.compareTo(newLast);
    }

    /**
     * 获取两位小数的Zero
     *
     * @return 两位小数的Zero
     */
    public static BigDecimal getZero() {

        BigDecimal result = new BigDecimal("0.00");
        result = result.setScale(CURRENCY_DECIMAL_PLACES, BigDecimal.ROUND_UP);
        return result;
    }

    /**
     * 字符串转BigDecimal两位小数
     *
     * @param first 要转换的字符串
     * @return BigDecimal两位小数
     */
    public static BigDecimal convertStringToBigDecimal(String first) {

        return BigDecimal.valueOf(Double.parseDouble(first)).setScale(CURRENCY_DECIMAL_PLACES,
                BigDecimal.ROUND_UP);
    }

    /**
     * BigDecimal转字符串
     *
     * @param first 要转换的bigDecimal
     * @return 转换后的字符串
     */
    public static String toString(BigDecimal first) {

        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        if (first == null) {
            return currency.format(new BigDecimal("0.00"));
        } else {
            return currency.format(first);
        }
    }
}
