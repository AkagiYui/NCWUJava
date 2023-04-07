package com.akagiyui.web.kenkoweb.exercise;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "SQLServlet", value = "/exercise/sql.do")
public class SQLServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<button onclick=\"history.back()\">返回</button>");

        var sql = request.getParameter("sql");
        var db = Database.getInstance();
        var connection = db.getConnection();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            var resultSet = statement.executeUpdate(sql);
            out.println("<p>执行成功，影响了" + resultSet + "行</p>");
        } catch (SQLException e) {
            out.println("<p>" + e.getSQLState() + "</p>");
            out.println("<p>" + e.getMessage() + "</p>");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.println("</body>");
        out.println("<script src=\"../static/addHomeButton.js\"></script>\n");
        out.println("</html>");
    }
}
