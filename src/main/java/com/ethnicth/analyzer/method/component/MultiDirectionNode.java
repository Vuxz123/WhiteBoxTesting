package com.ethnicth.analyzer.method.component;

import com.ethnicth.analyzer.exception.IllegalDefaultOutputWayException;
import com.ethnicth.analyzer.exception.IllegalSelectedWayException;
import com.ethnicth.analyzer.method.Method;

import java.util.List;
import java.util.Map;

public abstract class MultiDirectionNode implements Node {
    private final Node[] next;

    private Method method;

    private MultiDirectionNodeBody body = ((s, h, selector) -> {});

    public MultiDirectionNode(Node... next) {
        this.next = next;
    }

    public MultiDirectionNode(MultiDirectionNodeBody body, Node... next) {
        this.next = next;
        this.body = body;
    }

    public Node[] getNext() {
        return next;
    }

    public void setBody(MultiDirectionNodeBody body) {
        this.body = body;
    }

    protected void run(Map<String,Object> stack) {
        Map<String,Object> localStack = Map.copyOf(stack);
        assert (body != null);
        DirectionSelector selector;
        try {
            selector = setupSelector();
            body.call(localStack, method.getHeap(),selector);
            if(next == null) return;
            next[selector.getSelectedWay()].simulateNode(stack);
        } catch (IllegalDefaultOutputWayException e) {
            throw new RuntimeException(e);
        }
    }

    protected DirectionSelector setupSelector() throws IllegalDefaultOutputWayException {
        return new DirectionSelector(next.length, 0);
    }

    @Override
    public void simulateNode(Map<String, Object> stack) {
        run(stack);
    }

    @Override
    public void setMethodParent(Method method) {
        this.method = method;
    }

    public static class DirectionSelector {
        private final int maxWay;

        private int selectedWay;

        public DirectionSelector(int maxWay, int defaultOutputWay) throws IllegalDefaultOutputWayException {
            this.maxWay = maxWay;
            if(assertWay(defaultOutputWay)) this.selectedWay = defaultOutputWay;
            else throw new IllegalDefaultOutputWayException(defaultOutputWay, maxWay);
        }

        public void selectWay(int i) throws IllegalSelectedWayException {
            if(assertWay(i)) selectedWay = i;
            else throw new IllegalSelectedWayException(i, maxWay);
        }

        public int getSelectedWay() {
            return selectedWay;
        }

        private boolean assertWay(int i) {
            return i >=0 && i < maxWay;
        }
    }
}
