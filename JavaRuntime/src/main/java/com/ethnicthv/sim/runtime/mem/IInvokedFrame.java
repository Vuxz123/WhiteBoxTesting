package com.ethnicthv.sim.runtime.mem;

import com.ethnicthv.sim.runtime.mem.frame.IReturnAddress;

public interface IInvokedFrame {
    IReturnAddress getReturnAddress();
}
