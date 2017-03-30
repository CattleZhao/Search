package com.scorpion.pojo;

import java.util.Date;

public class New {
    private Integer id;

    private String date;

    private String category;

    private String source;

    private Integer count;

    private Integer flag;

    private Integer oreitation;

    private String topic;

    private Float oreitationvalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getOreitation() {
        return oreitation;
    }

    public void setOreitation(Integer oreitation) {
        this.oreitation = oreitation;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public Float getOreitationvalue() {
        return oreitationvalue;
    }

    public void setOreitationvalue(Float oreitationvalue) {
        this.oreitationvalue = oreitationvalue;
    }
}