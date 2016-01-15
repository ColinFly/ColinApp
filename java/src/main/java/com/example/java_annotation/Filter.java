package com.example.java_annotation;

/**
 * Created by colin on 16-1-15.
 * bean与数据库建立映射
 */
@Table("user")
public class Filter {
    @Column("id")
    private int id;
    @Column("name")
    private String name;
    @Column("age")
    private int age;
    @Column("city")
    private String city;
    @Column("email")
    private String email;
    @Column("mobile")
    private String mobile;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
