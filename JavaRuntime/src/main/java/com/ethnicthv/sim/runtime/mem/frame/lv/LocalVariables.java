package com.ethnicthv.sim.runtime.mem.frame.lv;

import com.ethnicthv.sim.runtime.exception.IllegalLocalVariablesIndex;
import com.ethnicthv.sim.runtime.mem.frame.ILocalVariables;

import java.util.ArrayList;

public class LocalVariables extends ArrayList<Object> implements ILocalVariables {
    public LocalVariables() {
        super(4);
    }

    public LocalVariables(int stackSize) {
        super(stackSize);
    }

    public Object aLoad(int index) throws IllegalLocalVariablesIndex {
        if (index >= size()) throw new IllegalLocalVariablesIndex();
        return super.get(index);
    }

    @Override
    public Object aLoad_0() throws IllegalLocalVariablesIndex {
        return aLoad(0);
    }

    @Override
    public Object aLoad_1() throws IllegalLocalVariablesIndex {
        return aLoad(1);
    }

    @Override
    public Object aLoad_2() throws IllegalLocalVariablesIndex {
        return aLoad(2);
    }

    @Override
    public Object aLoad_3() throws IllegalLocalVariablesIndex {
        return aLoad(3);
    }

    public void aStore(int index, Object object) throws IllegalLocalVariablesIndex {
        if (index >= size()) throw new IllegalLocalVariablesIndex();
        super.set(index, object);
    }

    @Override
    public void aStore_0(Object object) throws IllegalLocalVariablesIndex {
        aStore(0, object);
    }

    @Override
    public void aStore_1(Object object) throws IllegalLocalVariablesIndex {
        aStore(1, object);
    }

    @Override
    public void aStore_2(Object object) throws IllegalLocalVariablesIndex {
        aStore(2, object);
    }

    @Override
    public void aStore_3(Object object) throws IllegalLocalVariablesIndex {
        aStore(3, object);
    }
}
