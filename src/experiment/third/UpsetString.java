package experiment.third;
/*
接受用户输入的一个字符串，打乱该字符串并输出。

提示：遍历数组，使用随机数Random类，生成随机索引，交换数组的两个值
 */
public class UpsetString {
    public static void main(String[] args) {
        var scanner = new java.util.Scanner(System.in);
        var random = new java.util.Random();  // 随机数Random类
        System.out.print("请输入一个字符串：");
        var inputString = scanner.nextLine();
        var chars = inputString.toCharArray();  // 将字符串转换为字符数组
        for (int i = 0; i < chars.length; i++) {
            var randomIndex = random.nextInt(chars.length);  // 生成随机索引

            // 交换数组的两个值
            var temp = chars[i];
            chars[i] = chars[randomIndex];
            chars[randomIndex] = temp;
        }
        System.out.println(chars);
    }
}
