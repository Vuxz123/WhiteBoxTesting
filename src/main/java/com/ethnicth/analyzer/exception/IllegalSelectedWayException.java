package com.ethnicth.analyzer.exception;

public class IllegalSelectedWayException extends Exception{
    public IllegalSelectedWayException(int defaultOutputWay, int maxWay) {
        super("The Default OutputWayException is " + defaultOutputWay + ", out of bound " + maxWay);
    }
}
