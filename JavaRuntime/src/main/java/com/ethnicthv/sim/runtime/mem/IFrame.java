package com.ethnicthv.sim.runtime.mem;

import com.ethnicthv.sim.runtime.mem.frame.ILocalVariables;
import com.ethnicthv.sim.runtime.mem.frame.IOperationStack;

public interface IFrame {
    ILocalVariables getLocalVariables();

    IOperationStack getOperationStack();

    void setReturnAddress(Object ref);

    void printDebug();
}
