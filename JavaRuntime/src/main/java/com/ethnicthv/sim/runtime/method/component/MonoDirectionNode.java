package com.ethnicthv.sim.runtime.method.component;

import com.ethnicthv.sim.runtime.method.Method;

import java.util.HashMap;
import java.util.Map;

public abstract class MonoDirectionNode implements Node {
    private Node next;

    protected Method method;

    private MonoDirectionNodeBody body = ((s, h) -> {});

    public MonoDirectionNode(Node next) {
        this.next = next;
    }

    public MonoDirectionNode(MonoDirectionNode next, MonoDirectionNodeBody body) {
        this(next);
        this.body = body;
    }

    public MonoDirectionNode() {
        this(null);
    }

    public Node getNext() {
        return next;
    }

    public void setBody(MonoDirectionNodeBody body) {
        this.body = body;
    }

    public void setNext(Node next) {
        this.next = next;
        this.next.setMethodParent(method);
    }

    protected void run(Map<String,Object> stack) {
        HashMap<String, Object> localStack = new HashMap<>(stack);
        MonoDirectionNodeBody b = body;
        Method m = method;
        assert (b != null && m != null);
        b.call(localStack, m.getHeap());
        Map<String, Object> nextStack = afterBody(stack, localStack);
        Node n = next;
        if (n == null || m.isReturned()) return;
        n.simulateNode(nextStack);
    }

    /**
     * a method that will be called after the body was called
     * @param stack the original stack before this body
     * @param localStack the stack after this body
     * @return the stack that will be usedin the next node
     */
    protected abstract Map<String,Object> afterBody(Map<String,Object> stack, Map<String,Object> localStack);

    @Override
    public void simulateNode(Map<String, Object> stack) {
        run(stack);
    }

    @Override
    public void setMethodParent(Method method) {
        this.method = method;
    }
}
