package com.ccc.entity;

import java.io.Serializable;

/**
 * (Caigou)实体类
 *
 * @author makejava
 * @since 2021-08-19 21:51:39
 */
public class Caigou implements Serializable {
    private static final long serialVersionUID = 373144026763255392L;
    
    private Integer id;
    
    private String leixing;
    
    private String name;
    
    private String many;
    
    private String danwei;
    
    private String danjia;
    
    private String zongjia;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMany() {
        return many;
    }

    public void setMany(String many) {
        this.many = many;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getDanjia() {
        return danjia;
    }

    public void setDanjia(String danjia) {
        this.danjia = danjia;
    }

    public String getZongjia() {
        return zongjia;
    }

    public void setZongjia(String zongjia) {
        this.zongjia = zongjia;
    }

}