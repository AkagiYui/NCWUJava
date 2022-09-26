package experiment.fourth;

/*
1、 编写Book类，代表教材。要求如下：
（1）属性：名称（title）、页数（pageNum）
（2）页数不能多于600页，否则输出信息错误，并赋默认值600
（3）为属性提供赋值和取值方法
（4）info方法，控制台输出每本教材的名称和页数
 （5）编写测试类BookTest进行测试，为Book的属性赋初值。
 */
public class Book {
    private String title;
    private int pageNum;

    public Book(String title, int pageNum) {
        this.title = title;
        if (pageNum > 600) {
            System.out.println("页数不能多于600页，否则输出信息错误，并赋默认值600");
            this.pageNum = 600;
        } else {
            this.pageNum = pageNum;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum > 600) {
            System.out.println("页数不能多于600页，否则输出信息错误，并赋默认值600");
            this.pageNum = 600;
        } else {
            this.pageNum = pageNum;
        }
    }

    public void info() {
        System.out.println("教材名称：" + title + "，页数：" + pageNum);
    }
}
