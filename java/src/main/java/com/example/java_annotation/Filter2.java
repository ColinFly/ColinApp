package com.example.java_annotation;

/**
 * Created by colin on 16-1-15.
 */
@Table("employee")
public class Filter2 {
    @Column("id")
    private int id;
    @Column("name")
    private String name;
    @Column("leader")
    private String leader;

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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }
}
