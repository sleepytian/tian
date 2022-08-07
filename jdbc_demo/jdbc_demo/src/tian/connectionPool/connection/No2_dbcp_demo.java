package tian.connectionPool.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <p>
 *      这个 demo 用于测试 dbcp 数据库连接池
 *      --- ---
 *      使用 dbcp 数据库连接池需要导入三个 外部包:
 *          * commons-dbcp
 *          * commons-logging
 *          * commons-pool
 *      三个都可以找到 apache 的官方下载地址, 这里就不一一列举了.
 * </p>
 */
public class No2_dbcp_demo {

    // 测试 dbcp 数据库连接池技术  -->  不推荐的硬编码方式
    public Connection testGetConnection () throws SQLException {
        // 创建 dbcp 数据库连接池
        BasicDataSource source = new BasicDataSource();

        // 设置基本信息
        source.setUsername("root");
        source.setPassword("root");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setDriverClassName("com.mysql.jdbc.Driver");

        // 设置数据库连接池管理属性, 一些常用属性已经以 txt 的形式保存在该 .java 文件 的同目录下


        // 获取和打印连接
        Connection connection = source.getConnection();
        return connection;
    }

    @Test
    // 方法二: 使用配置文件
    public Connection testGetConnection1() throws Exception{

        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");

        Properties properties = new Properties();
        properties.load(inputStream);

        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        return connection;
    }
}
