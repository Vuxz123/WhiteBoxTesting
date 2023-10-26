package com.ethnicthv.sim.runtime.mem.frame;

public interface IOperationStack {
    Object push(Object ref);

    Object pop();
}
