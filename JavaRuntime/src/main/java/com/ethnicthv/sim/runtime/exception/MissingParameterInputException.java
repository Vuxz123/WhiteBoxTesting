package com.ethnicthv.sim.runtime.exception;

import java.util.List;

public class MissingParameterInputException extends Exception{
    public MissingParameterInputException(String message, List<Class> parameterMissing) {
        super("The Method was missed parameters with these type: " + parameterMissing + " : " + message);
    }
}
