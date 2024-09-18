package com.ethnicthv.sim.runtime.executor.s;

import com.ethnicthv.sim.runtime.executor.Execution;
import com.ethnicthv.sim.runtime.executor.VirtualInterpreter;
import com.ethnicthv.sim.runtime.executor.provider.FunctionConstraints;
import com.ethnicthv.sim.runtime.executor.s.util.ExtractorUtil;
import com.ethnicthv.sim.runtime.mem.frame.IOperationStack;
import javassist.bytecode.BootstrapMethodsAttribute;
import javassist.bytecode.ConstPool;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;

public class ObjectInstExecutors {
    public Execution ACONST_NULL = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.aconst_null,
            (classFile, frame, index) -> frame.getOperationStack().push(null));
    public Execution ALOAD = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.aload,
            ((classFile, frame, index) -> {
                Object ref = frame.getLocalVariables().load(index[0]);
                frame.getOperationStack().push(ref);
            })
    );
    public Execution ARETURN = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.areturn,
            ((classFile, frame, index) -> {
                Object ref = frame.getOperationStack().pop();
                frame.setReturnAddress(ref);
            })
    );
    public Execution ASTORE = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.astore,
            ((classFile, frame, index) -> {
                Object ref = frame.getOperationStack().pop();
                frame.getLocalVariables().store(index[0], ref);
            })
    );
    public Execution ATHROW = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.athrow,
            ((classFile, frame, index) -> {
                throw (Throwable) frame.getOperationStack().pop();
            })
    );
    public Execution CHECKCAST; //TODO: checkcast

    public Execution GETFIELD = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.getfield,
            ((classFile, frame, index) -> {
                IOperationStack stack = frame.getOperationStack();
                Object ref = stack.pop();
                int i = (((int) index[0]) << 8) | index[1];
                ConstPool pool = classFile.getConstPool();
                Object value = Class.forName(pool.getFieldrefClassName(i)).getField(pool.getFieldrefName(i)).get(ref);
                System.out.println(value);
                stack.push(value);
                //TODO: call a field of this Object Ref
            })
    );
    public Execution PUTFIELD = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.putfield,
            ((classFile, frame, index) -> {
                Object value = frame.getOperationStack().pop();
                Object ref = frame.getOperationStack().pop();
                //TODO: put a field of this Object Ref
            })
    );
    public Execution GETSTATIC = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.getstatic,
            ((classFile, frame, index) -> {
                int i = (((int) index[0]) << 8) | index[1];
                ConstPool constPool = classFile.getConstPool();
                frame.getOperationStack().push(
                        Class.forName(constPool.getFieldrefClassName(i))
                                .getField(constPool.getFieldrefName(i))
                                .get(null)
                );
                //TODO: call a static field
            })
    );
    public Execution PUTSTATIC = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.putstatic,
            ((classFile, frame, index) -> {
                //TODO: put a static field
            })
    );
    public Execution INSTANCEOF = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.iinstanceof,
            ((classFile, frame, index) -> {
                Object ref = frame.getOperationStack().pop();
                int i = (((int) index[0]) << 8) | index[1];
                //TODO: check type of this Object Ref
            })
    );
    public Execution INVOKEDYNAMIC = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.invokedynamic,
            ((classFile, frame, index) -> {
                int i = (((int) index[0]) << 8) | index[1];
                ConstPool constPool = classFile.getConstPool();
                Class<?> clazz = Class.forName(classFile.getName());
                String interfaceName = constPool.getInvokeDynamicType(i);
                {
                    interfaceName = interfaceName.substring(interfaceName.indexOf(")") + 2, interfaceName.length() - 1).replace("/", ".");
                }
                Class<?> clazz2 = Class.forName(interfaceName);
                int callSiteIndex = constPool.getInvokeDynamicBootstrap(i);
                int nameAndTypeIndex = constPool.getInvokeDynamicNameAndType(i);
                BootstrapMethodsAttribute bootstrapMethodsAttribute = ((BootstrapMethodsAttribute) classFile.getAttribute("BootstrapMethods"));
                BootstrapMethodsAttribute.BootstrapMethod bootstrapMethod = bootstrapMethodsAttribute.getMethods()[callSiteIndex];
                MethodHandles.Lookup caller = MethodHandles.privateLookupIn(clazz, MethodHandles.lookup());
                Object[] arguments = ExtractorUtil.extractBoostrapArguments(bootstrapMethod.arguments, classFile, clazz);
                LambdaMetafactory.metafactory(
                        caller,
                        constPool.getUtf8Info(constPool.getNameAndTypeName(nameAndTypeIndex)),
                        MethodType.methodType(clazz2),
                        (MethodType) arguments[0],
                        (MethodHandle) arguments[1],
                        (MethodType) arguments[2]
                );
                //TODO: invoke a method of this Object Ref
            })
    );

    public Execution INVOKEINTERFACE = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.invokeinterface,
            ((classFile, frame, index) -> {
                Object ref = frame.getOperationStack().pop();
                //TODO: invoke an interface method of this Object Ref
            })
    );
    public Execution INVOKESTATIC = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.invokestatic,
            ((classFile, frame, index) -> {
                //TODO: invoke an static method
            })
    );
    public Execution INVOKESPECIAL = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.invokespecial,
            ((classFile, frame, index) -> {
                Object ref = frame.getOperationStack().pop();
                int i = (((int) index[0]) << 8) | index[1];

                //TODO: invoke an instance method (special handling for superclass, private and instance initialization method invocations) of this Object Ref
            })
    );
    public Execution INVOKEVIRTUAL = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.invokevirtual,
            ((classFile, frame, index) -> {
                Object ref = frame.getOperationStack().pop();
                //TODO: invoke an instance method of this Object Ref
            })
    );
    public Execution MONITORENTER = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.monitorenter,
            ((classFile, frame, index) -> {
                //TODO: change the monitor state of the object to monitored;
            })
    );
    public Execution MONITOREXIT = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.monitorexit,
            ((classFile, frame, index) -> {
                //TODO: change the monitor state of the object to un-monitored;
            })
    );
    public Execution NEW = VirtualInterpreter.REGISTRY.registerExecution(
            FunctionConstraints.E.nnew,
            ((classFile, frame, index) -> {
                int i = (((int) index[0]) << 8) | index[1];
                Constructor<?> constructor = Class.forName(classFile.getName()).getConstructors()[0];
                Object[] params = new Object[constructor.getParameterCount()];
                frame.getOperationStack().push(constructor.newInstance(params));
                //optimize the coed above
                //Class.forName(classFile.getConstPool().getClassInfo(i)).getConstructors()[0].newInstance(new Object[Class.forName(classFile.getConstPool().getClassInfo(i)).getConstructors()[0].getParameterCount()]);

                //TODO: create a new object of a class
            })
    );
}
