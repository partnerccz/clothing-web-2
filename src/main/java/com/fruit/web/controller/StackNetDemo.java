package com.fruit.web.controller;

import java.util.Stack;

public class StackNetDemo {
    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();
    public void push(Integer node){
        while (!stack2.isEmpty()){
            stack1.add(stack2.pop());
        }
    }
    public int pop() {
        while (!stack1.isEmpty()){
            stack2.add(stack1.pop());
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        StackNetDemo stackNetDemo=new StackNetDemo();
        for (int i = 0; i < 5; i++) {
            stackNetDemo.push(i+1);
        }
        while (!stackNetDemo.stack2.isEmpty()){
            System.out.println(stackNetDemo.stack2.pop());
        }
    }
}
