package com.akagiyui.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AkagiYui
 */
public class Server {
    private final static Map<String,Socket> map = new HashMap<>();
    private final static Map<Socket,ObjectOutputStream> sMap = new HashMap<>();
//     private final static Map<Socket,PrintWriter> sMap = new HashMap<>();
    public static void main(String[] args) throws Exception {
        System.out.println("服务器起来..........");
        //实例化对象
        ServerSocket ss = new ServerSocket(3333);
        while (true){
            //通过服务对象或Socket  阻塞
            Socket socket = ss.accept();
            //读 输入流
            InputStream is = socket.getInputStream();
            //反序列化
            ObjectInputStream ois = new ObjectInputStream(is);
            //写
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            PrintWriter pw = new PrintWriter(os);
            sMap.put(socket,oos);
//            sMap.put(socket,pw);
            //匿名类
            new Thread(()->{
                    try {
                        while (true){
                            Object obj = ois.readObject();
                            Msg msg = new Msg();
                            if(obj instanceof People){
                               People p = (People) obj;
                                System.out.println(p.getName()+"--发送了--"+p.getMsg());
                                if(!map.containsKey(p.getAccount())){
                                    map.put(p.getAccount(),socket);
                                }

                                if (map.containsKey(p.getfAccount())){
                                    Socket soc = map.get(p.getfAccount());
//                                    PrintWriter w = sMap.get(soc);
//                                    w.println(p.getMsg());
//                                    w.flush();
                                      ObjectOutputStream s = sMap.get(soc);
                                      msg.setName(p.getName());
                                      msg.setMsg(p.getMsg());
                                      s.writeObject(msg);
                                }else {
//                                    pw.println("好友不在线");
//                                    pw.flush();
                                    msg.setMsg("好友不在线");
                                    oos.writeObject(msg);
                                }
                            }else {
                                msg.setMsg("好友不在线");
                                oos.writeObject(msg);
                            }
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
            }).start();
        }
    }
}
