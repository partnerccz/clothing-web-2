package com.fruit.web.controller;

import java.util.LinkedList;

public class StackDemo {
    private LinkedList<String> linkedList=new LinkedList<>();

    /**
     * 添加元素到链表集合的第一个
     * @param name
     */
    public void push(String name) {
        linkedList.addFirst(name);
    }

    /**
     * 获取到出栈的第一个元素
     * @return
     */
    public String getTop() {
        return linkedList.getFirst();
    }

    /**
     * 删除第一个元素并获取到元素
     * @return
     */
    public String prop() {
        return linkedList.removeFirst();
    }

    /**
     * 获取到链表集合的大小
     * @return
     */
    public Integer getSize(){
        return linkedList.size();
    }

    /**
     * 判断集合是否为空
     * @return
     */
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public static void main(String[] args) {
        StackDemo sd1=new StackDemo();
        for (int i = 0; i < 5; i++) {
            sd1.linkedList.push("我是第"+i+"进栈的");
        }
        StackDemo sd2=new StackDemo();
        System.out.println("读取栈1的数据：");
        while (!sd1.isEmpty()) {
            String name=sd1.prop();
            sd2.push(name);
            System.out.println(name);
        }
        System.out.println("\n读取栈2的数据：");
        while (!sd2.isEmpty()){
            System.out.println(sd2.prop());
        }

    }
}
