package com.ethnicthv.sim.runtime.executor.s;

import com.ethnicthv.sim.runtime.executor.Execution;
import com.ethnicthv.sim.runtime.executor.VirtualInterpreter;
import com.ethnicthv.sim.runtime.executor.provider.FunctionConstraints;
import com.ethnicthv.sim.runtime.mem.frame.IOperationStack;

public class UtilInstrExecutors {
    public Execution DUP = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.dup,
            (classFile, frame, index) -> {
                IOperationStack operationStack = frame.getOperationStack();
                Object ref = operationStack.pop();
                operationStack.push(ref);
                operationStack.push(ref);
            });
}
