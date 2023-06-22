package com.akagiyui.demo;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonDemo {

    //python解析器
    public static void pythonMethod() {
        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile("python文件的绝对地址");
        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyobj = pyFunction.__call__(new PyString("hello"), new PyString("java"));
        System.out.println(pyobj);
    }

    //runtime
    public static void runtimeMethod() {
        try {
            String[] args = new String[]{"python3", "-u C:\\Users\\jbb\\Desktop\\demo1.py", "10", "29"};
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "UTF-8"));
            System.out.println(in.readLine() + "===========");
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        pythonMethod();
    }

}
