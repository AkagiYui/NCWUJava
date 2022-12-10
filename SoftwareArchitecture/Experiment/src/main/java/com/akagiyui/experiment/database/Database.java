package com.akagiyui.experiment.database;

import com.akagiyui.experiment.equation.AddEquation;
import com.akagiyui.experiment.equation.Equation;
import com.akagiyui.experiment.equation.SubEquation;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/app";

    static final String USERNAME = "root";
    static final String PASSWORD = "";

    private Connection conn = null;

    @Getter
    private boolean connected = false;

    public Database() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER); // 加载类从而注册驱动
    }

    public boolean connect() {
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            DatabaseMetaData dbMeta = conn.getMetaData();
            System.out.println("当前用户：" + dbMeta.getUserName());
            System.out.println("驱动版本：" + dbMeta.getDriverVersion());

            connected = true;
        } catch (Exception se) {
            connected = false;
            se.printStackTrace();
        }
        return connected;
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
                connected = false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ArrayList<Equation> getEquations() {
        try {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM equation");
            var equations = new ArrayList<Equation>();
            var addEquationBuilder = new AddEquation.AddEquationBuilder();
            var subEquationBuilder = new SubEquation.SubEquationBuilder();
            while (rs.next()) {
                Equation equation = null;
                try {
                    var op = rs.getString("operator");
                    if ("+".equals(op)) {
                        equation = addEquationBuilder
                                .setOperand1(rs.getShort("operand1"))
                                .setOperand2(rs.getShort("operand2"))
                                .build();
                    } else if ("-".equals(op)) {
                        equation = subEquationBuilder
                                .setOperand1(rs.getShort("operand1"))
                                .setOperand2(rs.getShort("operand2"))
                                .build();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (equation != null) {
                    equations.add(equation);
                }
            }
            return equations;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    public boolean removeEquation(Equation equation) {
        try {
            var stmt = conn.createStatement();
            var sql = String.format("DELETE FROM equation WHERE operand1 = %d AND operand2 = %d AND operator = '%s'",
                    equation.getOperand1(), equation.getOperand2(), equation.getOperator().getOperator());
            return stmt.executeUpdate(sql) > 0;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }

    public boolean saveEquation(Equation equation) {
        if (!connected) {
            return false;
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = String.format("INSERT INTO equation (operand1, operand2, operator) VALUES (%d, %d, '%s')",
                    equation.getOperand1(), equation.getOperand2(), equation.getOperator().getOperator());
            stmt.executeUpdate(sql);
            stmt.close();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    // TODO 没有做主键
    public boolean updateOperand1(Equation equation, short operand1) {
        if (!connected) {
            return false;
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = String.format("UPDATE equation SET operand1 = %d WHERE operand1 = %d AND operand2 = %d AND operator = '%s'",
                    operand1, equation.getOperand1(), equation.getOperand2(), equation.getOperator().getOperator());
            stmt.executeUpdate(sql);
            stmt.close();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        var db = new Database();
        db.connect();

        db.saveEquation(new SubEquation((short)123, (short)234));

        var a = db.getEquations();
        for (var e : a) {
            System.out.println(e);
        }

        db.removeEquation(new AddEquation((short)1, (short)2));

        db.updateOperand1(new SubEquation((short)123, (short)234), (short)456);

        db.disconnect();
    }
}
