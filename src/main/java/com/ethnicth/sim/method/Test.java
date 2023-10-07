package com.ethnicth.sim.method;

public class Test {
    public int a;
    public int b;

    public Test(int a, int b) {
        this.a = a;
        this.b = b;
        doSomething();
    }

    private void doSomething2() {
        if(a > b) {
            System.out.println("a>b");
            int c = a + b;
            System.out.println(c);
        }
        else {
            System.out.println("a<=b");
        }
    }

    public int doSomething() {
        return Math.max(a, b);
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
