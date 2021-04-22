package entity;

import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;
import com.thoughtworks.xstream.converters.basic.BigIntegerConverter;
import com.thoughtworks.xstream.converters.basic.ShortConverter;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.util.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类 @Zero
 */
public class CommonUtil {

    private static final String REG_PHONE = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$";

    private static final String REG_MOBILE = "^((\\+86)|(86))?(1)\\d{10}$";

    private static final String REG_QQ = "^[1-9]\\d{4,10}$";

    private static final String REG_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    private static final String REG_ZIP = "^[1-9]\\d{5}$";

    private static final String REG_IP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final String REG_URL = "^(http|https|ftp):\\/\\/(([A-Z0-9][A-Z0-9_-]*)(\\.[A-Z0-9][A-Z0-9_-]*)+)(:(\\d+))?\\/?/i";

    private static final String REG_CHINESE = "^[\\u0391-\\uFFE5]+$";

    private static final String REG_MONEY = "[\\-\\+]?\\d+(\\.\\d+)?$";

    private CommonUtil() {
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 对象
     * @return 为空返回true，不为空返回false
     */
    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            String string = obj.toString();
            if (string.trim().replaceAll("\\s", "").equals("")) {
                return true;
            }
        }
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            if (collection.isEmpty()) {
                return true;
            }
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为null，如果传进去的对象为null，则返回true，传进去的对象都不为null，返回false
     *
     * @param objects 可变参数
     * @return 为空返回true，不为空返回false
     */
    public static boolean isNull(Object... objects) {
        if (null == objects || objects.length == 0) {
            return true;
        }
        for (Object obj : objects) {
            if (isNull(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是不为空
     *
     * @param obj 对象
     * @return 为空返回false，不为空返回true
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 判断对象不为null，如果传进去的对象为null，则返回false，传进去的对象都不为null，返回true
     *
     * @param objects 可变参数
     * @return 为空返回false，不为空返回true
     */
    public static boolean isNotNull(Object... objects) {
        return !isNull(objects);
    }

    /**
     * 如果值是NULL 返回 ""空字符串 否则返回自身
     *
     * @param value
     * @return
     */
    public static String toString(String value) {
        return ((value == null) ? "" : value);
    }

    /**
     * 判断是否为货币格式
     *
     * @param value 要判断的值
     * @return 是返回true，否则返回false
     */
    public static boolean isMoney(String value) {
        Assert.notNull(value, "货币不能为空");
        if (isNull(value)) {
            return false;
        }
        return Pattern.matches(REG_MONEY, value);
    }

    /**
     * 判断是否为电话号码
     *
     * @param value 要判断的值
     * @return 是返回true，否则返回false
     */
    public static boolean isPhone(String value) {
        Assert.notNull(value, "电话号码不能为空");
        if (isNull(value)) {
            return false;
        }
        return Pattern.matches(REG_PHONE, value);
    }

    /**
     * 判断是否为手机号码
     *
     * @param value 要判断的值
     * @return 是返回true，否则返回false
     */
    public static boolean isMobile(String value) {
        Assert.notNull(value, "手机号码不能为空");
        if (isNull(value)) {
            return false;
        }
        return Pattern.matches(REG_MOBILE, value);
    }

    /**
     * 判断是否为email
     *
     * @param value 要判断的值
     * @return 是返回true，否则返回false
     */
    public static boolean isEmail(String value) {
        Assert.notNull(value, "邮箱不能为空");
        if (isNull(value)) {
            return false;
        }
        return Pattern.matches(REG_EMAIL, value);
    }

    /**
     * 判断是否为QQ
     *
     * @param value 要判断的值
     * @return 是返回true，否则返回false
     */
    public static boolean isQQ(String value) {
        Assert.notNull(value, "QQ不能为空");
        if (isNull(value)) {
            return false;
        }
        return Pattern.matches(REG_QQ, value);
    }

    /**
     * 判断是否为Zip格式
     *
     * @param value 要判断的值
     * @return 是返回true，否则返回false
     */
    public static boolean isZip(String value) {
        Assert.notNull(value, "Zip不能为空");
        if (isNull(value)) {
            return false;
        }
        return Pattern.matches(REG_ZIP, value);
    }

    /**
     * 判断是否为email
     *
     * @param value 要判断的值
     * @return 是返回true，否则返回false
     */
    public static boolean isIP(String value) {
        Assert.notNull(value, "IP不能为空");
        if (isNull(value)) {
            return false;
        }
        return Pattern.matches(REG_IP, value);
    }

    /**
     * 判断是否为url
     *
     * @param value 要判断的值
     * @return 是返回true，否则返回false
     */
    public static boolean isURL(String value) {
        Assert.notNull(value, "URL不能为空");
        if (isNull(value)) {
            return false;
        }
        return Pattern.matches(REG_URL, value);
    }

    /**
     * 判断是否为中文格式
     *
     * @param value 要判断的值
     * @return 是返回true，否则返回false
     */
    public static boolean isChinese(String value) {
        Assert.notNull(value, "字符串不能为空");
        if (isNull(value)) {
            return false;
        }
        return Pattern.matches(REG_CHINESE, value);
    }

    /**
     * 验证是否为合法身份证
     *
     * @param cardId 身份证id
     * @return 符合规则返回true，不符合返回false
     */
    public static boolean isIdCard(String cardId) {
        Assert.notNull(cardId, "身份证号码不能为空");
        if (isNull(cardId)) {
            return false;
        }
        cardId = cardId.toUpperCase();
        if (!(Pattern.matches("^\\d{17}(\\d|X)$", cardId) || Pattern.matches("\\d{15}$", cardId))) {
            return false;
        }
        int provinceCode = Integer.parseInt(cardId.substring(0, 2));
        if (provinceCode < 11 || provinceCode > 91) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为日期格式字符串
     *
     * @param value 要判断的日期格式字符串
     * @return 是日期格式返回true，否则返回false
     */
    public static boolean isDate(String value) {
        Assert.notNull(value, "日期格式字符串不能为空");
        if (isNull(value)) {
            return false;
        }
        try {
            new SimpleDateFormat().parse(value);
            return true;
        } catch (ParseException e) {

        }
        return false;
    }


    /**
     * javabean驼峰格式转下划线格式 如：testName test_name
     *
     * @param javeBeanStr
     * @return
     */
    public static String convertJavaBeanStrToUnderLine(String javeBeanStr) {
        StringBuffer buf = new StringBuffer();
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(javeBeanStr);
        while (m.find()) {
            m.appendReplacement(buf, "_" + m.group(0));
        }
        m.appendTail(buf);
        return buf.toString().toLowerCase();
    }

    /**
     * 下划线格式转javabean驼峰格式 如： test_name testName
     *
     * @param underLineStr
     * @return
     */
    public static String convertUnderLineStrToJavaBean(String underLineStr) {
        StringBuffer buf = new StringBuffer(underLineStr);
        Matcher mc = Pattern.compile("_").matcher(underLineStr);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            // 如果'_'为最后的字符,则直接退出循环,解决db2中'rownumber_'字符串数组越界问题
            if (position == underLineStr.length()) {
                break;
            }
            buf.replace(position - 1, position + 1, buf.substring(position, position + 1).toUpperCase());
        }
        return buf.toString();
    }

}
