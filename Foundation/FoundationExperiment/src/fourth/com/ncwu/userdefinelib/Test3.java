package fourth.com.ncwu.userdefinelib;

import fourth.com.ncwu.mainapp.CStudent;

/*
Test3类完成学生类的测试，创建单个对象，对象数组，并测试学生类中的变量count，查看学生类对象的个数。
 */
public class Test3 {
    public static void main(String[] args) {
        CStudent stu1 = new CStudent("2018001", "张三", new CDate(1999, 1, 1));
        CStudent stu2 = new CStudent("2018002", "李四", new CDate(1999, 2, 2));
        CStudent stu3 = new CStudent("2018003", "王五", new CDate(1999, 3, 3));
        CStudent stu4 = new CStudent("2018004", "赵六", new CDate(1999, 4, 4));
        CStudent stu5 = new CStudent("2018005", "孙七", new CDate(1999, 5, 5));
        CStudent stu6 = new CStudent("2018006", "周八", new CDate(1999, 6, 6));
        CStudent stu7 = new CStudent("2018007", "吴九", new CDate(1999, 7, 7));
        CStudent stu8 = new CStudent("2018008", "郑十", new CDate(1999, 8, 8));
        CStudent stu9 = new CStudent("2018009", "钱十一", new CDate(1999, 9, 9));
        CStudent stu10 = new CStudent("2018010", "孙十二", new CDate(1999, 10, 10));
        CStudent[] stu = new CStudent[10];
        stu[0] = stu1;
        stu[1] = stu2;
        stu[2] = stu3;
        stu[3] = stu4;
        stu[4] = stu5;
        stu[5] = stu6;
        stu[6] = stu7;
        stu[7] = stu8;
        stu[8] = stu9;
        stu[9] = stu10;
        System.out.println("学生类对象的个数为：" + CStudent.getCount());
    }
}
