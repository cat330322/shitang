package com.ccc.entity;

import java.io.Serializable;

/**
 * (Emp)实体类
 *
 * @author makejava
 * @since 2021-08-16 23:29:44
 */
public class Emp implements Serializable {
    private static final long serialVersionUID = -78004959063552420L;
    
    private Integer id;
    
    private String name;
    
    private Integer age;
    
    private Object salary;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Object getSalary() {
        return salary;
    }

    public void setSalary(Object salary) {
        this.salary = salary;
    }

}