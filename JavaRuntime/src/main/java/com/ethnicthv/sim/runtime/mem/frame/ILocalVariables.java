package com.ethnicthv.sim.runtime.mem.frame;

public interface ILocalVariables {
    void store(int index, Object object);

    Object load(int index);

    void printDebug();
}
