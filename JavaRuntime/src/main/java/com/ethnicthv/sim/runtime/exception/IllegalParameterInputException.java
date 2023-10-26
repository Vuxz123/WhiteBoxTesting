package com.ethnicthv.sim.runtime.exception;

public class IllegalParameterInputException extends Exception{
    public IllegalParameterInputException(String message, Class illegalParameterType) {
        super("The Method was illegally add an input of type: " + illegalParameterType + " : " + message);
    }
}
