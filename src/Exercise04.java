public class Exercise04 {
    // 编写程序，把10个整数1、2、3……10赋值给某个整型数组，然后用int型指针输出该数组元素的值

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i + 1;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }
}
