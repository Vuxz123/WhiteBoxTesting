package com.ethnicth.analyzer.method.component;

import com.ethnicth.analyzer.method.Method;

import java.util.Map;

public interface Node {
    void simulateNode(Map<String, Object> stack);

    void setMethodParent(Method method);
}
