package test;

import java.util.Scanner;

public class HelloCase {
    public static void main(String[] args) {
        String s = "HelloWorld";
        //大小写转换
        System.out.println(caseStr(s));
        //替换字符串
        System.out.println(replaceStr(s));
    }

    /**
     * 大小写转换
     * @param s
     * @return
     */
    public static String caseStr(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i<s.length();i++) {
            stringBuffer.append(Character.toUpperCase(s.charAt(i)));
        }
        return stringBuffer.toString();
    }

    /**
        替换字符
     */
    public static String replaceStr(String s) {
        return s.replaceAll("o","*");
    }

}
