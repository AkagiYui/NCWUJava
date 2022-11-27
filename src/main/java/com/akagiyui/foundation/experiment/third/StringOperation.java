package main.java.com.akagiyui.foundation.experiment.third;

import java.util.Arrays;

/*
已知字符串“Five miles away”

按要求执行以下操作：
（1）     统计字符串中字母e出现的次数。
（2）     提取字符串away。
（3）     将字符串复制到一个字符数组Char[]str 中。
（4）     将字符串中每个单词的第一个字母变成大写，输出到控制台。
（5）     实现该字符串的逆序输出
 */
public class StringOperation {
    public static void main(String[] args) {
        var str = "Five miles away";

        var count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'e') {
                count++;  // 统计字符串中字母e出现的次数
            }
        }
        System.out.println("e出现的次数：" + count);

        System.out.println("提取字符串away：" + str.substring(10));

        var chars = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chars[i] = str.charAt(i);
        }
        System.out.println("将字符串复制到一个字符数组Char[]str 中：" + Arrays.toString(chars));

        var words = str.split(" ");
        var result = "";
        for (var word : words) {
            result += word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
            // 将字符串中每个单词的第一个字母变成大写
        }
        System.out.println("将字符串中每个单词的第一个字母变成大写，输出到控制台：" + result);

        var reverseStr = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reverseStr += str.charAt(i);  // 实现该字符串的逆序输出
        }
        System.out.println("实现该字符串的逆序输出：" + reverseStr);
    }
}
