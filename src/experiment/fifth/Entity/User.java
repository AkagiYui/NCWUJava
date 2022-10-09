package experiment.fifth.Entity;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String id;
    private String phone;

    public boolean isLegal() {
        if (this.username.length() < 3 || this.username.length() > 15) {
            System.out.println("用户名长度必须3-15位之间");
            return false;
        }
        // 	只能是字母加数字的组合，但是不能是纯数字
        if (this.username.matches("[0-9]+")) {
            System.out.println("用户名不能是纯数字");
            return false;
        }
        if (!this.username.matches("[a-zA-Z0-9]+")) {
            System.out.println("用户名只能是字母加数字的组合");
            return false;
        }

        if (this.id.length() != 18) {
            System.out.println("身份证号必须是18位");
            return false;
        }
        if (this.id.charAt(0) == '0') {
            System.out.println("身份证号不能以0开头");
            return false;
        }
        if (!this.id.matches("[0-9]+[0-9Xx]")) {
            System.out.println("身份证号前17位只能是数字，最后一位只能是数字或X");
            return false;
        }

        if (this.phone.length() != 11) {
            System.out.println("手机号必须是11位");
            return false;
        }
        if (this.phone.charAt(0) == '0') {
            System.out.println("手机号不能以0开头");
            return false;
        }
        if (!this.phone.matches("[0-9]+")) {
            System.out.println("手机号只能是数字");
            return false;
        }
        return true;
    }

    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username) {
        if (username.length() < 3 || username.length() > 15) {
            System.out.println("用户名长度必须3-15位之间");
            return false;
        }
        // 	只能是字母加数字的组合，但是不能是纯数字
        if (username.matches("[0-9]+")) {
            System.out.println("用户名不能是纯数字");
            return false;
        }
        if (!username.matches("[a-zA-Z0-9]+")) {
            System.out.println("用户名只能是字母加数字的组合");
            return false;
        }
        this.username = username;
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public boolean setId(String id) {
        if (id.length() != 18) {
            System.out.println("身份证号必须是18位");
            return false;
        }
        if (id.charAt(0) == '0') {
            System.out.println("身份证号不能以0开头");
            return false;
        }
        if (!id.matches("[0-9]+[0-9Xx]")) {
            System.out.println("身份证号前17位只能是数字，最后一位只能是数字或X");
            return false;
        }
        this.id = id;
        return true;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.length() != 11) {
            System.out.println("手机号必须是11位");
            return;
        }
        if (phone.charAt(0) == '0') {
            System.out.println("手机号不能以0开头");
            return;
        }
        if (!phone.matches("[0-9]+")) {
            System.out.println("手机号只能是数字");
            return;
        }
        this.phone = phone;
    }
}
