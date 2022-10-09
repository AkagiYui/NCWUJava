package experiment.fifth;

import java.io.*;
import java.util.Random;

public class Utils {
    public static String generateVerifyCode(){
        // 长度为5，由4位大写或者小写字母和一位数字组成，同一个字母可重复
        var str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        var random = new Random();
        var number_index = random.nextInt(5);
        var sb = new StringBuilder();
        for (var i = 0; i < 5; i++) {
            if (i == number_index) {
                sb.append(random.nextInt(10));
            } else {
                sb.append(str.charAt(random.nextInt(str.length())));
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean saveObject(Object obj, String filename) {
        try {
            var file = new FileOutputStream(filename);
            var out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static <T> T loadObject(String filename) {
        try {
            var file = new FileInputStream(filename);
            var in = new ObjectInputStream(file);
            //noinspection unchecked
            var obj = (T) in.readObject();
            in.close();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
