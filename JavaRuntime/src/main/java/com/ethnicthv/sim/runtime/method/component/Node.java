package com.ethnicthv.sim.runtime.method.component;

import com.ethnicthv.sim.runtime.method.Method;

import java.util.Map;

public interface Node {
    void simulateNode(Map<String, Object> stack);

    void setMethodParent(Method method);
}
