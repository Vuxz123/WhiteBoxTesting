package com.ethnicthv.sim.runtime.mem.frame;

import com.ethnicthv.sim.runtime.mem.frame.lv.LocalVariables;
import com.ethnicthv.sim.runtime.mem.frame.os.OperationStack;

public class Frame {
    private final IOperationStack operationStack;
    private final ILocalVariables localVariables;

    public Frame(int stackSize) {
        this.operationStack = new OperationStack();
        this.localVariables = new LocalVariables(stackSize);
    }

    public IOperationStack getOperationStack() {
        return operationStack;
    }

    public ILocalVariables getLocalVariables() {
        return localVariables;
    }


}
