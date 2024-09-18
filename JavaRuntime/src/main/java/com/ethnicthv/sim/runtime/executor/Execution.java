package com.ethnicthv.sim.runtime.executor;

import com.ethnicthv.sim.runtime.executor.provider.FunctionConstraints;

public class Execution {
    public final FunctionConstraints.E OPCODE;

    public final Executor EXECUTOR;

    public Execution(FunctionConstraints.E opcode, Executor executor) {
        OPCODE = opcode;
        EXECUTOR = executor;
    }
}
