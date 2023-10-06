package com.ethnicth.analyzer.exception;

public class IllegalDefaultOutputWayException extends Exception{
    public IllegalDefaultOutputWayException(int defaultOutputWay, int maxWay) {
        super("The Default OutputWayException is " + defaultOutputWay + ", out of bound " + maxWay);
    }
}
