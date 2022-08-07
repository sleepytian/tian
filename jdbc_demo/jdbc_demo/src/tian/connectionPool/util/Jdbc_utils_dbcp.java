package tian.connectionPool.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 使用配置文件获取 connection, 可以参考 connection.No2_dbcp_demo.java 文件查看具体内容
 */
public class Jdbc_utils_dbcp {

    private static BasicDataSource dataSource;

    /**
     *  使用静态代码块实现 在创建 utils 类的时候创建数据库连接池
     */
    static {
        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    public static Connection getConnection() throws Exception {

        Connection connection = dataSource.getConnection();

        return connection;
    }
}
