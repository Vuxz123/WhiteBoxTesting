package com.ethnicthv.sim.runtime.executor.s.util;

import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class ExtractorUtil {
    public static Object[] extractBoostrapArguments(int[] arguments, ClassFile classFile, Class<?> clazz) {
        int le = arguments.length;
        try {
            switch (le) {
                case 3:
                    return extractBoostrapArgumentsC3(arguments, classFile, clazz);
                default:
                    throw new RuntimeException("Unsupported bootstrap arguments length: " + le);
            }
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object[] extractBoostrapArgumentsC3(int[] arguments, ClassFile classFile, Class<?> clazz) throws NoSuchMethodException, IllegalAccessException {
        Object[] objects = new Object[3];
        ConstPool constPool = classFile.getConstPool();
        objects[0] = extractMethodType(arguments[0], clazz, constPool);
        objects[1] = extractMethodHandleInfo(arguments[1], clazz, constPool);
        objects[2] = extractMethodType(arguments[2], clazz, constPool);
        return objects;
    }

    private static MethodType extractMethodType(int argument, Class<?> clazz, ConstPool constPool) {
        return MethodType.fromMethodDescriptorString(
                constPool.getUtf8Info(constPool.getMethodTypeInfo(argument)),
                clazz.getClassLoader()
        );
    }

    private static MethodHandle extractMethodHandleInfo(int arguments, Class<?> clazz, ConstPool constPool) throws NoSuchMethodException, IllegalAccessException {
        int methodRefIndex = constPool.getMethodHandleIndex(arguments);
        System.out.println(methodRefIndex);
        int methodNameAndTypeIndex = constPool.getMethodrefNameAndType(methodRefIndex);
        System.out.println(methodNameAndTypeIndex);
        String methodName = constPool.getUtf8Info(constPool.getNameAndTypeName(methodNameAndTypeIndex));
        String methodDescriptor = constPool.getUtf8Info(constPool.getNameAndTypeDescriptor(methodNameAndTypeIndex));
        return MethodHandles.privateLookupIn(clazz, MethodHandles.lookup())
                .findStatic(
                        clazz,
                        methodName,
                        MethodType
                                .fromMethodDescriptorString(
                                        methodDescriptor,
                                        clazz.getClassLoader()
                                )
                );
    }
}
