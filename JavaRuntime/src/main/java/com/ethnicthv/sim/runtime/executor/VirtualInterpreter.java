package com.ethnicthv.sim.runtime.executor;

import com.ethnicthv.sim.runtime.executor.provider.FunctionConstraints;
import com.ethnicthv.sim.runtime.executor.s.IntegerInstExecutors;
import com.ethnicthv.sim.runtime.executor.s.ObjectInstExecutors;
import com.ethnicthv.sim.runtime.executor.s.UtilInstrExecutors;

public class VirtualInterpreter {
    public static final ObjectInstExecutors OINSTR;
    public static final IntegerInstExecutors IINSTR;
    public static final UtilInstrExecutors UINSTR;
    public static final Registry REGISTRY;

    static {
        REGISTRY = new Registry();
        OINSTR = new ObjectInstExecutors();
        IINSTR = new IntegerInstExecutors();
        UINSTR = new UtilInstrExecutors();
    }

    public static Registry getRegistry() {
        return REGISTRY;
    }

    public static class Registry {
        public Execution[] executions;

        private Registry() {
            executions = new Execution[FunctionConstraints.E.values().length];
            System.out.println(this.executions.length);
        }

        public Execution registerExecution(FunctionConstraints.E opcode, Executor executor) {
            Execution execution = new Execution(opcode, executor);
            this.executions[opcode.getOpcode()] = execution;
            return execution;
        }
    }
}
