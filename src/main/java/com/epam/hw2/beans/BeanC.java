package com.epam.hw2.beans;

public class BeanC {
    public String name;
    public int value;

    public BeanC(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private void init (){
        System.out.println("Inside BeanC init method");
    }

    private void destroy (){
        System.out.println("Inside BeanC destroy method");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
