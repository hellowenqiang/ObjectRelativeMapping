package com.cwq.objectrelativemapping;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2016/10/29.
 */
@DatabaseTable(tableName = "t_user")
public class User {
    //指定当前成员变量对应的字段值是自增的
  @DatabaseField(generatedId = true)
    private int id;
    //指定当前成员变量和数据库表中字段的对应关系
    @DatabaseField(columnName = "userName")
    private String name;
    @DatabaseField
    private int age;
    @DatabaseField
    private  String addr;

   public User(){

   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                '}';
    }

    public User(int id, String name, int age, String addr) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.addr = addr;

    }
}




