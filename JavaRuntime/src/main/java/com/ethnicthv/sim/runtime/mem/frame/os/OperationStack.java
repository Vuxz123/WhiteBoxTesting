package com.ethnicthv.sim.runtime.mem.frame.os;

import com.ethnicthv.sim.runtime.mem.frame.IOperationStack;

import java.util.Stack;

public class OperationStack implements IOperationStack {
    private final Stack<Object> stack = new Stack<>();
    @Override
    public void push(Object item) {
        stack.push(item);
        System.out.println("Push, Stack Size: " + stack.size() + " : " + item);
    }

    @Override
    public synchronized Object pop() {
        System.out.println("Pop, Stack Size: " + (stack.size() - 1));
        return stack.pop();
    }

    @Override
    public void printDebug() {
        //print size
        System.out.println("\tSize: " + stack.size());
        for (int i = 0; i < stack.size(); i++) {
            System.out.println("\t\t[" + i + "] = " + stack.get(i));
        }
    }
}
