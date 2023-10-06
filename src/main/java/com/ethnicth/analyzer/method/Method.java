package com.ethnicth.analyzer.method;

import com.ethnicth.analyzer.exception.IllegalParameterInputException;
import com.ethnicth.analyzer.exception.MissingParameterInputException;
import com.ethnicth.analyzer.method.component.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Method is a represent of a method that is made for the use in WBT running simulator
 */
public class Method {
    private final Map<String, Object> stack = new HashMap<>();
    private final Map<String, Object> heap;
    private final Node startNode;
    private final Class returnType;
    private final List<Class> parameterType;

    protected Object returnObject;

    public Method(List<Class> parameterType, Class returnType, Map<String, Object> heap, Node startNode, Map<String, Object> parameters) throws MissingParameterInputException, IllegalParameterInputException {
        this.heap = heap;
        this.startNode = startNode;
        this.parameterType = parameterType;
        this.returnType = returnType;
        setupStack(parameters);
    }

    public Object simulateMethod() {
        startNode.setMethodParent(this);
        startNode.simulateNode(stack);
        return this.returnObject;
    }

    public Map<String, Object> getHeap() {
        return heap;
    }

    private void setupStack(Map<String, Object> parameters) throws IllegalParameterInputException, MissingParameterInputException {
        assertParameters(parameters);
        stack.putAll(parameters);
    }

    private void assertParameters(Map<String, Object> parameters) throws IllegalParameterInputException, MissingParameterInputException {
        List<Class> temp = new java.util.ArrayList<>(List.copyOf(parameterType));
        for(Object o : parameters.values()) {
            Class cls = o.getClass();
            if(temp.remove(cls)) return;
            throw new IllegalParameterInputException("", cls);
        }
        if(!temp.isEmpty()) throw new MissingParameterInputException("", temp);
    }
}
