package com.ethnicthv.sim.runtime.mem.frame;

public interface IOperationStack {
    void push(Object ref);
    Object pop();

    void printDebug();
}
