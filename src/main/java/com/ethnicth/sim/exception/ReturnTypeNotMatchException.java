package com.ethnicth.sim.exception;

public class ReturnTypeNotMatchException extends Exception{
    public ReturnTypeNotMatchException(Class expected, Class actualReturnType) {
        super("The return type is " + actualReturnType.getName() + " but require type " + expected.getName());
    }
}
