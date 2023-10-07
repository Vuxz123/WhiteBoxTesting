package com.ethnicth.sim.method.component;

import com.ethnicth.sim.method.Method;

import java.util.Map;

public interface Node {
    void simulateNode(Map<String, Object> stack);

    void setMethodParent(Method method);
}
