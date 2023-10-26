package com.ethnicthv.sim.runtime.mem.frame;

import com.ethnicthv.sim.runtime.exception.IllegalLocalVariablesIndex;

public interface ILocalVariables {
    Object aLoad(int index) throws IllegalLocalVariablesIndex;

    Object aLoad_0() throws IllegalLocalVariablesIndex;

    Object aLoad_1() throws IllegalLocalVariablesIndex;

    Object aLoad_2() throws IllegalLocalVariablesIndex;

    Object aLoad_3() throws IllegalLocalVariablesIndex;

    void aStore(int index, Object object) throws IllegalLocalVariablesIndex;

    void aStore_0(Object object) throws IllegalLocalVariablesIndex;

    void aStore_1(Object object) throws IllegalLocalVariablesIndex;

    void aStore_2(Object object) throws IllegalLocalVariablesIndex;

    void aStore_3(Object object) throws IllegalLocalVariablesIndex;
}
