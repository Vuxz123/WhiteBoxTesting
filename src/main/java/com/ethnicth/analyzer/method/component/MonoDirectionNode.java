package com.ethnicth.analyzer.method.component;

import com.ethnicth.analyzer.method.Method;

import java.util.Map;

public abstract class MonoDirectionNode implements Node {
    private final MonoDirectionNode next;

    private Method method;

    private MonoDirectionNodeBody body = ((s, h) -> {});

    public MonoDirectionNode(MonoDirectionNode next) {
        this.next = next;
    }

    public MonoDirectionNode(MonoDirectionNode next, MonoDirectionNodeBody body) {
        this.next = next;
        this.body = body;
    }

    public MonoDirectionNode getNext() {
        return next;
    }

    public void setBody(MonoDirectionNodeBody body) {
        this.body = body;
    }

    protected void run(Map<String,Object> stack) {
        Map<String,Object> localStack = Map.copyOf(stack);
        assert (body != null);
        body.call(localStack, method.getHeap());
        if(next == null) return;
        next.run(stack);
    }

    @Override
    public void simulateNode(Map<String, Object> stack) {
        run(stack);
    }

    @Override
    public void setMethodParent(Method method) {
        this.method = method;
    }
}
