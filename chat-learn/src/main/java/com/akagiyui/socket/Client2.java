package com.akagiyui.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author AkagiYui
 */
public class Client2 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("客户2端起来.........");
        //实例化Socket对象
        Socket socket = new Socket("127.0.0.1",3333);
        OutputStream os = socket.getOutputStream();
        //序列化
        ObjectOutputStream oos = new ObjectOutputStream(os);
        new Thread(()-> {
                try {
                    while (true){
                        String msg = sc.next();
                        People p = new People("小王",msg,"888","666");
                        oos.writeObject(p);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }).start();

        new Thread(()->{
            try {
                //写 输出流
//获取读的流
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
//                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while (true){
//                    System.out.println("\t\t\t\t\t\t"+br.readLine());
                        Object obj = ois.readObject();
                        if(obj instanceof Msg){
                            Msg m = (Msg) obj;
                            System.out.println("\t\t\t\t\t\t"+m.getMsg());
                        }
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
