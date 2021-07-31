package com.epam.hw2.beans;

public class BeanD {
    public String name;
    public int value;

    public BeanD(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private void init (){
        System.out.println("Inside BeanD init method");
    }

    private void destroy (){
        System.out.println("Inside BeanD destroy method");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
