package tian.connectionPool.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>
 *     使用数据库连接池技术 c3p0 创建一个 Jdbc_utils 类.
 * </p>
 */
public class Jdbc_utils_c3p0 {

    /**
     *  <p>
     *      调用 c3p0 配置文件创建 数据库连接池
     *  <p/>
     *
     *  <p>
     *      为什么要在类内声明 数据库连接池 对象?
     *      --- --- ---
     *      因为在一个项目中, 存在多个地方使用数据库连接池,
     *      如果把 创建数据库连接池的代码 放到函数中, 那么每次调用函数都会创建一个数据库连接池, 没有达到数据库连接池存在的目的.
     *      ---
     *      声明为 静态变量, 也是为了达成这个目的.
     *  </p>
     *
     */
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("intergalactoApp");

    // c3p0 数据库连接池技术获取连接
    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
}
