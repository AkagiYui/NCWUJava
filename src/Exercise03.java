public class Exercise03 {
    // 求自幂数
    // 如果在一个固定的进制中，一个n位自然数等于自身各个数位上数字的n次幂之和，则称此数为自幂数。
    // 例如：在十进制中，153是一个三位数，各个数位的3次幂之和为1^3+5^3+3^3=153，所以153是十进制中的自幂数。

    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {
            int sum = 0;
            int temp = i;
            while (temp > 0) {
                int digit = temp % 10;
                sum += Math.pow(digit, 3);
                temp /= 10;
            }
            if (sum == i) {
                System.out.println(i);
            }
        }
    }
}
