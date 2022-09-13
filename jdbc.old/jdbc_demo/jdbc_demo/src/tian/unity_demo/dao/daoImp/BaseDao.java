package tian.unity_demo.dao.daoImp;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 这个抽象类作为 BaseDAO 的案例演示
 * 注意:
 * 这个案例中使用了 dbutils 工具
 */
public abstract class BaseDao<T> {

    // 创建一个内置的 queryRunner
    QueryRunner queryRunner = new QueryRunner();

    // 用于保存泛型类对象
    private Class<T> type = null;

    /**
     * 构造函数, 在这里要获取到 对应类的泛型类对象
     */
    public BaseDao() {
        // 获取当前类类型对象
        Class clazz = this.getClass();

        // 接下来获取当前类的泛型类对象并且保存在 BaseDao 的私有属性中.
        // 获取类对象
        // 1. 获取超类的泛型类对象并且转化为初始化类型(parameterizedType
        ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
        // 2. 通过初始化类型获取实际参数
        Type[] actualTypeArguments = type.getActualTypeArguments();
        // 3. 为类内属性赋值
        this.type = (Class<T>) actualTypeArguments[0];
    }

    /**
     * 通用的增删改操作
     *
     * @param connection connection
     * @param sql        sql
     * @param params     params
     * @return 返回收到影响的行数量
     */
    public int Update(Connection connection, String sql, Object... params) {
        int count = 0;
        // 利用 queryRunner 进行通用增删改操作
        try {
            count = queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回收到影响的行数量
        return count;
    }

    /**
     * 获取一个对象
     * @param connection connection
     * @param sql sql
     * @param params params
     * @return return
     */
    public T getBean(Connection connection, String sql, Object... params) {
        T t = null;
        try {
            // 创建对象保存接受信息
            t = type.getConstructor().newInstance();
            // 利用 queryRunner 获取信息
            t = queryRunner.query(connection, sql, new BeanHandler<T>(type), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回值
        return t;
    }

    /**
     * 获取多个对象
     * @param connection connection
     * @param sql sql
     * @param params params
     * @return list
     */
    public List<T> getBeanList(Connection connection, String sql, Object...params) {
        List<T> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回查询结果
        return list;
    }

    /**
     * 获取特殊值
     * @param connection connection
     * @param sql sql
     * @param params params
     * @return object
     */
    public Object getValue(Connection connection, String sql, Object...params) {
        Object query = null;
        try {
            query = queryRunner.query(connection, sql, new ScalarHandler<T>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

}
