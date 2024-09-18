package com.ethnicthv.sim.runtime.mem.frame.ra;

import com.ethnicthv.sim.runtime.mem.frame.IReturnAddress;

public class ReturnAddress implements IReturnAddress {
    private final Object returnRef;

    public ReturnAddress(Object returnRef) {
        this.returnRef = returnRef;
    }

    public Object getReturnRef() {
        return returnRef;
    }
}
