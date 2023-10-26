package com.ethnicthv.sim.runtime.method.component;

import java.util.HashMap;
import java.util.Map;

public interface MonoDirectionNodeBody {
    void call(HashMap<String, Object> stack, Map<String, Object> heap);
}
