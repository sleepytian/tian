# JDBC DAO 详解

DAO 全称 data(base) access object,  在实际使用中, 这是一套用于对接数据库的类, 其中主要包括三种类:

-   **抽象类: BaseDAO**

    这个类用于规范所有 dao 类必须遵守的基本函数.

-   **接口类: <db>DAO**

    这个类用于规范实现对应数据库表的 dao 需要哪些函数, 比如, 对于数据库表 Employees, 则需要写明一个 EmployeeDAO 接口, 用于规范数据表 Employee 的 jdbc 操作.

-   **实现类: <db>DAOImp**

    `imp`表示"implement", 这个类继承了抽象类 `BaseDAO` 并且实现了接口 `<db>DAO`, 其中包括了对特定数据库表的所有可执行函数, 在程序进行 jdbc 操作时, 往往需要利用这个类的实例对象.

# dao 实现案例

接下来为了方便演示, 本篇中使用了尚硅谷数据库教学中使用的 mysql 源码作为数据演示, 对应的 .sql 文件可以在资源文件中找到.

我们以数据库 atguigudb 中的数据表 employees 作为目标, 实现这一套案例.

## 0. 文件结构

-   Employee

    首先要存在一个 Employee 类, 用于创建对象接收来自数据库的信息.

    我们一般在目录中创建包 `bean` 用于保存所有此类示例

-   BaseDAO

    BaseDAO 一般在包 DAO 的根目录下, 包 DAO 是与 包 bean 同级的

-   EmployeeDAO

    EmployeeDAO 一般在包 DAO 的根目录下, 包 DAO 是与 包 bean 同级的

-   EmployeeDAOImp

    EmployeeDAOImp 一般在 包 dao 下的 daoImp 包中.

下面是一个示意图:

```text
com.tian.demo
	|- bean
		- Employee.java
	|- dao
		|- daoImp
		-- BaseDao
		-- EmployeeDao
```

## 1. 实现数据表类 Employee

```java
package com.jdbc_demo.bean;

import java.util.Date;

public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private Date hire_date;

    /**
     * 空参数构造器 不建议使用
     */
    public Employee() {
    }

    /**
     * 全参数构造器
     * @param id 员工id
     * @param first_name 员工名
     * @param last_name 员工姓
     * @param email email
     * @param hire_date 雇佣时间
     */
    public Employee(int id, String first_name, String last_name, String email, Date hire_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.hire_date = hire_date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", hire_date=" + hire_date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }
}

```

## 2. 实现 BaseDAO

```java
package com.jdbc_demo.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Handler;

public abstract class BaseDao <T> {

    private Class<T> type;

    private QueryRunner queryRunner = new QueryRunner();

    public BaseDao () {
        Class clazz = this.getClass();

        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();

        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

        this.type = (Class<T>) actualTypeArguments[0];
    }

    /**
     * universal update method.
     * @param connection connection instance to mysql database
     * @param sql sql sentences
     * @param args args in sql string
     * @return the rows changed in this update
     */
    public int update (Connection connection, String sql, Object...args) {

        // 创建用于返回值的变量
        int update = 0;
        // queryRunner 执行 sql 操作
        try {
            update = queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回被修改的行数
        return update;
    }

    /**
     * 获取一个对象
     * @param connection connection
     * @param sql sql 语句
     * @param args args 参数列表
     * @return 返回 sql 语句查询的目标内容
     */
    public T getInstance(Connection connection, String sql, Object...args) {
        T t = null;
        try {
            t = queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回内容
        return t;
    }

    /**
     * 获取所有对象
     * @param connection connection
     * @param sql sql 语句
     * @param args args 参数列表
     * @return 以 List 的形式, 返回所有对象
     */
    public List<T> getAll (Connection connection, String sql, Object...args) {
        List<T> list = null;
        try {
            list = queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取特殊值的函数
     * @param connection connection
     * @param sql sql 语句
     * @param args sql 语句的 参数列表
     * @return 返回查询特殊值
     */
    public Object getValue (Connection connection, String sql, Object...args) {

        Object query = null;
        try {
            query = queryRunner.query(connection, sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query;
    }

}

```

## 3. 实现接口 EmployeeDAO



## 4. 实现类 EmployeeDAOImp

