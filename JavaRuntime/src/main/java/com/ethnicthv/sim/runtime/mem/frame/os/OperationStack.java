package com.ethnicthv.sim.runtime.mem.frame.os;

import com.ethnicthv.sim.runtime.mem.frame.IOperationStack;

import java.util.Stack;

public class OperationStack extends Stack<Object> implements IOperationStack {
    @Override
    public Object push(Object item) {
        return super.push(item);
    }

    @Override
    public synchronized Object pop() {
        return super.pop();
    }
}
