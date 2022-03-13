package com.darren.demo.spring.jdbc;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {

    public static void main(String[] args) {
        DBUtil util = new DBUtil();
        util.add();
    }

    public void add() {
        DBUtil util = new DBUtil();
        Connection conn = util.openConnection();
        String sql = "insert into UserTable (id,username,password) values (4,'bigtom','666')";
        try {
            // 设置事务手动提交
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            // 提交事务
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // 如果失败，回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 关闭
            util.closeConnection(conn);
        }

    }

    /**
     * 建立连接
     *
     * @return
     */
    public Connection openConnection() {
        Properties prop = new Properties();
        String driver = null;
        String url = null;
        String username = null;
        String password = null;
        try {
            // 将配置文件加载进内存
            prop.load(Resources.getResourceAsStream("properties/db.properties"));
            // 设置Connection参数
            driver = prop.getProperty("db.driver");
            url = prop.getProperty("db.url");
            username = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
            // 加载驱动程序
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}