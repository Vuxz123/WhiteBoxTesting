package com.ethnicthv.sim.runtime.executor;

import com.ethnicthv.sim.runtime.mem.IFrame;
import javassist.bytecode.ClassFile;

public interface Executor {
    void execute(ClassFile classFile, IFrame frame, byte... index) throws Throwable;
}
