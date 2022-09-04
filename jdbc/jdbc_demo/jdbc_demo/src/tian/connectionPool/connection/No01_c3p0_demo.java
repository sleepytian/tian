package tian.connectionPool.connection;


import org.junit.jupiter.api.Test;
import com.mchange.v2.c3p0.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>
 * this is code for test functions of c3p0 connection pool.
 * <p>
 * 注意:
 *      在使用 c3p0 的时候, 需要导入两个包, 这两个包都在 c3p0 解压文件夹的 lib 目录中 保存.
 * <p>
 *
 */
public class No01_c3p0_demo {

    /**
     * 硬编码的方式获取数据库连接池, 这种方法不是很推荐.
     */
    @Test
    public void testGetConnection() throws Exception {

        // 获取 c3p0 数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        // 设置链接数据库需要的信息, 包括驱动类路径, jdbc 的 url, 用户名, 和密码.
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("root");
        cpds.setPassword("root");

        // 设置相关参数 对数据库连接池做管理, 数据库连接池的配置属性可以在官方文档中查看

        // 设置初始 数据库连接池 中连接数
        cpds.setInitialPoolSize(10);

        // 获取一个连接对象
        Connection connection = cpds.getConnection();
        System.out.println(connection);

    }

    // 方式二: 使用配置文件 使用 c3p0 数据库连接池
    // 这种方式需要配置一个 c3p0 的配置文件 c3p0-config.xml, 你可以在官方文档中找到这种方式, 在本工程文件中, 已经把这个文件保存到了 src 根目录下.
    //
    @Test
    public void testGetConnection1() throws SQLException {
        // 调用 c3p0 配置文件
        ComboPooledDataSource cpds = new ComboPooledDataSource("intergalactoApp");

        Connection connection = cpds.getConnection();
        System.out.println(connection);
    }


}
