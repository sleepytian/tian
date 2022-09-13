package tian.unity_demo.bean;

import java.sql.Date;

/**
 * 这个函数是用于接受数据库表中 Employee 的类
 * 为了省时间所以只做了一部分数据列在类属性中.
 */
public class Employee {
    private int emp_id;
    private String last_name;
    private String email;
    private Date hire_date;

    public Employee() {
    }

    public Employee(int emp_id, String last_name, String email, Date hire_date) {
        this.emp_id = emp_id;
        this.last_name = last_name;
        this.email = email;
        this.hire_date = hire_date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id=" + emp_id +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", hire_date=" + hire_date +
                '}';
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
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
