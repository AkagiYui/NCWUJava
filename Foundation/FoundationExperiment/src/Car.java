public class Car {
    String owner;
    String brand;
    String color;
    Boolean isRunning;

    public Car(String owner, String brand, String color) {
        this.owner = owner;
        this.brand = brand;
        this.color = color;
        this.isRunning = false;
    }

    public void start() {
        if (this.isRunning) {
            System.out.println("车已经启动了");
        } else {
            this.isRunning = true;
            System.out.println("启动成功");
        }
    }

    public void stop() {
        if (this.isRunning) {
            this.isRunning = false;
            System.out.println("停车成功");
        } else {
            System.out.println("车已经停止了");
        }
    }

    public void go_forward() {
        if (this.isRunning) {
            System.out.println("前进");
        } else {
            System.out.println("请先启动车辆");
        }
    }

    public void go_backward() {
        if (this.isRunning) {
            System.out.println("后退");
        } else {
            System.out.println("请先启动车辆");
        }
    }
}
