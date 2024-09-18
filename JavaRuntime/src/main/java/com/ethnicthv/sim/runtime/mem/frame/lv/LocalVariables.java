package com.ethnicthv.sim.runtime.mem.frame.lv;

import com.ethnicthv.sim.runtime.mem.frame.ILocalVariables;

public class LocalVariables implements ILocalVariables {
    Object[] localVariables;
    public LocalVariables() {
        this(4);
    }

    public LocalVariables(int stackSize) {
        this.localVariables = new Object[stackSize];
    }


    @Override
    public void store(int index, Object object) {
        localVariables[index] = object;
    }

    @Override
    public Object load(int index) {
        return localVariables[index];
    }

    @Override
    public void printDebug() {
        //print size
        System.out.println("\tSize: " + localVariables.length);
        for (int i = 0; i < localVariables.length; i++) {
            System.out.println("\t\t[" + i + "] = " + localVariables[i]);
        }
    }
}
