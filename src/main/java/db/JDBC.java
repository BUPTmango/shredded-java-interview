package db;

import java.sql.*;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/8 3:18 下午
 */
public class JDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://100.233.63" +
                ".121:3306/cupid?useUnicode=true&characterEncoding=UTF-8&useSSL=false", "root", "AC7cAC75234998");
        // 3. 创建statement对象
        String sql = "select * from post where post_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 1);
        // 4. 向数据库发送sql
        ResultSet resultSet = statement.executeQuery();
        // 5. 处理结果集
        while(resultSet.next()){
            System.out.println(resultSet.getString("title"));
            System.out.println(resultSet.getString("author"));
        }
        // 6. 关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
