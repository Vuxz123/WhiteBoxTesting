package com.ethnicthv.sim.runtime.method.component.ret;

import com.ethnicthv.sim.runtime.exception.ReturnTypeNotMatchException;
import com.ethnicthv.sim.runtime.method.Method;
import com.ethnicthv.sim.runtime.method.component.MonoDirectionNode;
import com.ethnicthv.sim.runtime.method.component.MonoDirectionNodeBody;
import com.ethnicthv.sim.runtime.method.component.Node;

import java.util.Map;

/**
 * a node represent a return instruction
 *  * <p><t> - this will end the simulation and return a object </t>
 */
public class ReturnNode extends MonoDirectionNode {

    public ReturnNode(String stackValueNameToReturn, Method method) {
        super(null);
        super.setBody((s, h) -> {
            try{
                if(s.containsKey(stackValueNameToReturn)) {
                    method.returnObject(s.get(stackValueNameToReturn));
                } else if(h.containsKey(stackValueNameToReturn)) {
                    method.returnObject(h.get(stackValueNameToReturn));
                }
            } catch (ReturnTypeNotMatchException e) {
                throw new RuntimeException(e);
            }
        });
        this.setMethodParent(method);
    }

    @Override
    public void setBody(MonoDirectionNodeBody body) {
        //Empty body to prevent adding body to this Node
    }

    @Override
    public void setNext(Node next) {
        //Empty body to prevent adding next node to this Node
    }

    @Override
    protected Map<String, Object> afterBody(Map<String, Object> stack, Map<String, Object> localStack) {
        stack.clear();
        return stack;
    }
}
