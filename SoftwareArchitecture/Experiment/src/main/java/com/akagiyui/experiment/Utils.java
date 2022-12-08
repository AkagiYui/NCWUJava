package com.akagiyui.experiment;

import java.io.*;

public class Utils {
    /**
     * 保存对象到文件
     * @param obj 欲保存的对象
     * @param filename 文件名
     * @return 是否保存成功
     */
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

    /**
     * 从文件中读取对象
     * @param filename 文件名
     * @return 读取的对象
     * @param <T> 对象类型
     */
    public static <T> T loadObjectOrNull(String filename) {
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
