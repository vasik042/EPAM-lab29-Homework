package com.epam.hw2.beans;

public class BeanB {
    public String name;
    public int value;

    public BeanB(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private void init (){
        System.out.println("Inside BeanB init method");
    }

    private void otherInit (){
        System.out.println("Inside BeanB init method (Changed by BeanFactoryPostProcessor)");
    }

    private void destroy (){
        System.out.println("Inside BeanB destroy method");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
