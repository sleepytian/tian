package tian.apache_dbutils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;
import tian.bean.Customer;
import tian.connectionPool.util.Jdbc_utils_druid;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Query Runner 是一个由 apache 提供的 jdbc 开源工具类库, 封装了针对于数据库的增删改查操作.
 */
public class QueryRunnerTest {

    /**
     * test
     * 测试一个添加数据的操作函数.
     */
    @Test
    public void testInsert() throws Exception {
        QueryRunner queryRunner = new QueryRunner();

        Connection connection = Jdbc_utils_druid.getConnection();
        String sql = "insert into customers (name, email, birth) values (?,?,?)";
        int insertCount = queryRunner.update(connection, sql, "demo", "234@456", "1897-12-21");

        System.out.println(insertCount + " records have been added.");

        connection.close();
    }

    /**
     * 测试查询函数
     * <p>
     * BeanHandler 是 ResultSetHandler 接口的一个实现类, 用于返回单个数据库数据.
     * </p>
     */
    @Test
    public void testQuery1() throws Exception {
        Connection connection = null;
        Customer customer = null;
        try {
            QueryRunner queryRunner = new QueryRunner();

            connection = Jdbc_utils_druid.getConnection();
            String sql = "select * from customers where id = ?";
            BeanHandler<Customer> customerBeanHandler = new BeanHandler<Customer>(Customer.class);
            customer = queryRunner.query(connection, sql, customerBeanHandler, 19);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        System.out.println(customer);
    }

    /**
     * 当有两个或者以上查询结果时, 使用 BeanListHandlers 收集查询结果
     */
    @Test
    public void testQuery2() throws Exception {
        Connection connection = null;
        List<Customer> list = null;
        try {
            QueryRunner queryRunner = new QueryRunner();

            connection = Jdbc_utils_druid.getConnection();
            String sql = "select * from customers";
            BeanListHandler<Customer> customerBeanListHandler = new BeanListHandler<>(Customer.class);
            list = queryRunner.query(connection, sql, customerBeanListHandler);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        System.out.println(list);
    }

    /**
     * 有查询特殊值的需求的时候, 可以使用 ScalarHandler 函数.
     */
    @Test
    public void testQuery3() throws Exception{
        Connection connection = null;
        Long query = null;
        try {
            QueryRunner queryRunner = new QueryRunner();

            connection = Jdbc_utils_druid.getConnection();
            String sql = "select count(*) from customers";

            ScalarHandler records = new ScalarHandler();
            query = (Long) queryRunner.query(connection, sql, records);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        System.out.println(query);
    }
}
