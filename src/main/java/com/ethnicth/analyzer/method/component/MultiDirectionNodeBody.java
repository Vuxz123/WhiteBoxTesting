package com.ethnicth.analyzer.method.component;

import java.util.Map;

public interface MultiDirectionNodeBody {
    void call(Map<String, Object> stack, Map<String, Object> heap, MultiDirectionNode.DirectionSelector directionSelector);
}
