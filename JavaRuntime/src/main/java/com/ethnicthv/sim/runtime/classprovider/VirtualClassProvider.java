package com.ethnicthv.sim.runtime.classprovider;

import com.ethnicthv.sim.runtime.executor.VirtualInterpreter;
import com.ethnicthv.sim.runtime.mem.frame.Frame;
import javassist.bytecode.*;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.EmptyStackException;

public class VirtualClassProvider {
    public static void main(String[] args) {
        String classFilePath = "JavaRunTime/target/classes/com/ethnicthv/Test.class";
        try (DataInputStream dis = new DataInputStream(new FileInputStream(classFilePath))) {
            ClassFile classFile = new ClassFile(dis);
            ConstPool constPool = classFile.getConstPool();
            constPool.print();

            for (MethodInfo methodObj : classFile.getMethods()) {
                System.out.println("\n");
                System.out.println(methodObj.getName());
                Frame frame = new Frame(methodObj.getCodeAttribute().getMaxLocals());
                CodeIterator iterator = methodObj.getCodeAttribute().iterator();
                while (iterator.hasNext()) {
                    int a = iterator.next();
//                    System.out.println(a);
                    int opcode = iterator.byteAt(a);
                    System.out.println("opcode:" + opcode);
                    System.out.println("m:" + Mnemonic.OPCODE[opcode]);
                    if (VirtualInterpreter.REGISTRY.executions[opcode] != null) {
                        try {
                            VirtualInterpreter.getRegistry().executions[opcode].EXECUTOR.execute(classFile, frame, (byte) iterator.byteAt(a + 1), (byte) iterator.byteAt(a + 2));
                            //frame.printDebug(); TODO: add config for debug
                        } catch (EmptyStackException ignored) {
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
