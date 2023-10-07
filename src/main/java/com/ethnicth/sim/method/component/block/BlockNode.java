package com.ethnicth.sim.method.component.block;

import com.ethnicth.sim.method.component.MonoDirectionNode;
import com.ethnicth.sim.method.component.MonoDirectionNodeBody;
import com.ethnicth.sim.method.component.Node;

import java.util.Map;

/**
 * a node represent a code block
 * <p><t> - this will un-stack all the memory allocated in the Block</t>
 */
public class BlockNode extends MonoDirectionNode {
    private final Node nodeBody;

    public BlockNode(Node nodeBody) {
        this.nodeBody = nodeBody;
    }

    @Override
    public void setBody(MonoDirectionNodeBody body) {
        super.setBody(((stack, heap) -> {
            nodeBody.setMethodParent(this.method);
            nodeBody.simulateNode(stack);
        }));
    }

    @Override
    protected Map<String, Object> afterBody(Map<String, Object> stack, Map<String, Object> localStack) {
        return stack;
    }
}
