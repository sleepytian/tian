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



## 3. 实现接口 EmployeeDAO



## 4. 实现类 EmployeeDAOImp

