package com.example.reflect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by colin on 15-12-17.
 */
public class A extends Object implements ActionListener {
    //私有属性
    private int gender=2;
    //公有属性
    public String name;
    //无参构造
    public A(){}
    //有参构造
    public A(int id,String name){}
    //普通方法
    public int normalMethod(int id,String name){return 0;}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
