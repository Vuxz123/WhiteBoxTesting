package com.ethnicthv;

import com.ethnicthv.sim.runtime.executor.Execution;
import com.ethnicthv.sim.runtime.executor.Executor;
import com.ethnicthv.sim.runtime.executor.provider.FunctionConstraints;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Function;

public class Test {
    public static void test2() {
        String name = "Bard";
        String greeting = "Hello, " + name + "!"; // Uses invokedynamic for concatenation
        System.out.println(greeting); // Output: Hello, Bard!
    }

    public static void test3() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(void.class, String.class);
        MethodHandle printlnMH = lookup.findStatic(System.class, "out", methodType);

        printlnMH.invokeExact("Hello from MethodHandle!"); // Output: Hello from MethodHandle!
    }

    public static void test4() {
        MyInterface myLambda = () -> System.out.println("Hello from lambda!");
        myLambda.doSomething(); // Output: Hello from lambda!
    }

    public void test() throws Throwable {
        Execution a = new Execution(FunctionConstraints.E.aconst_null, (classFile, frame, index) -> {
        });
        Executor b = a.EXECUTOR;
        b.execute(null, null, (byte) 0);
        Function<String, String> lambda = (string) -> string;
        lambda.apply("test");
    }

    interface MyInterface {
        void doSomething();
    }
}
