package tian.connectionPool.connection;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * <p>
 *      使用 druid 数据库连接池
 * </p>
 */
public class No03_Druid_demo {

    @Test
    public Connection testGetConnection() throws Exception {

        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        return dataSource.getConnection();
    }
}
