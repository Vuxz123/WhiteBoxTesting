package com.ethnicthv.sim.runtime.executor.s;

import com.ethnicthv.sim.runtime.executor.Execution;
import com.ethnicthv.sim.runtime.executor.VirtualInterpreter;
import com.ethnicthv.sim.runtime.executor.provider.FunctionConstraints;

public class IntegerInstExecutors {
    public Execution I2B = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.i2b,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value = (int) os.pop();
                os.push((byte) value);
            }
    );
    public Execution I2C = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.i2c,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value = (int) os.pop();
                os.push((char) value);
            }
    );
    public Execution I2D = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.i2d,
            (classFile, frame, index) -> {
                int value = (int) frame.getOperationStack().pop();
                frame.getOperationStack().push((double) value);
            }
    );
    public Execution I2f = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.i2f,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value = (int) os.pop();
                os.push((float) value);
            }
    );
    public Execution I2L = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.i2l,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value = (int) os.pop();
                os.push((long) value);
            }
    );
    public Execution I2S = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.i2s,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value = (int) os.pop();
                os.push((short) value);
            }
    );
    public Execution IADD = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.iadd,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 + value2);
            }
    );
    public Execution IAND = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.iand,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 & value2);
            }
    );
    public Execution IDIV = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.idiv,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 / value2);
            }
    );
    public Execution ILOAD = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.iload,
            ((classFile, frame, index) -> {
                frame.getOperationStack().push(frame.getLocalVariables().load(index[0]));
            })
    );
    public Execution IMUL = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.imul,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 * value2);
            }
    );
    public Execution INEG = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.ineg,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value = (int) os.pop();
                os.push(-value);
            }
    );
    public Execution IOR = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.ior,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 | value2);
            }
    );
    public Execution IREM = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.irem,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 % value2);
            }
    );
    public Execution IRETURN = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.ireturn,
            (classFile, frame, index) -> {
                frame.setReturnAddress(frame.getOperationStack().pop());
            }
    );
    public Execution ISHL = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.ishl,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 << value2);
            }
    );
    public Execution ISHR = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.ishr,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 >> value2);
            }
    );
    public Execution ISTORE = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.istore,
            ((classFile, frame, index) -> {
                frame.getLocalVariables().store(index[0], frame.getOperationStack().pop());
            })
    );
    public Execution ISUB = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.isub,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 - value2);
            }
    );
    public Execution IUSHR = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.iushr,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 >>> value2);
            }
    );
    public Execution IXOR = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.ixor,
            (classFile, frame, index) -> {
                var os = frame.getOperationStack();
                int value1 = (int) os.pop();
                int value2 = (int) os.pop();
                os.push(value1 ^ value2);
            }
    );
}
