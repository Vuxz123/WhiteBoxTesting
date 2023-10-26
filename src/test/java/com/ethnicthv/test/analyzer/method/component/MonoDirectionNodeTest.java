package com.ethnicthv.test.analyzer.method.component;

import com.ethnicthv.sim.runtime.exception.IllegalParameterInputException;
import com.ethnicthv.sim.runtime.exception.MissingParameterInputException;
import com.ethnicthv.sim.runtime.method.Method;
import com.ethnicthv.sim.runtime.method.component.MonoDirectionNode;
import com.ethnicthv.sim.runtime.method.component.Node;
import com.ethnicthv.sim.runtime.method.component.ret.ReturnNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MonoDirectionNodeTest {

    @Test
    public void testMonoDirectionNodeTest() {
        try {
            MonoDirectionNode node1 = new MonoDirectionNodeImpl_();
            MonoDirectionNode node2 = new MonoDirectionNodeImpl_();

            MonoDirectionNode block1Node1 = new MonoDirectionNodeImpl_();

            Map<String, Object> heap = new HashMap<>();
            heap.put("a", 10);
            heap.put("b", 20);

            Method method = new Method(
                    new ArrayList<>(),
                    Integer.class,
                    heap,
                    node1,
                    new HashMap<>()
            );

            node1.setNext(block1Node1);

            node1.setBody(((s, h) -> {
                int a = (int) h.get("a");
                int b = (int) h.get("b");
                int c = a + b;
                s.put("c", c);
            }));

            block1Node1.setBody((s, h) -> {
                int a = (int) h.get("a");
                int b = (int) h.get("b");
                s.put("d", a - b);
            });

            block1Node1.setNext(node2);

            node2.setBody((s,h) -> {
                int b = (int) h.get("b");
                int c = (int) s.get("c");
                var a = s.get("d");
                if(a == null) System.out.println("d is null");
                if(c > b) {
                    System.out.println("True");
                    Assertions.assertTrue(true);
                }
                else {
                    System.out.print("False");
                    Assertions.fail();
                }
            });
            node2.setNext(new ReturnNode("c", method));
            var ret = method.simulateMethod();
            System.out.println("Return: " + ret);
            Assertions.assertEquals(30, ret);
        } catch (MissingParameterInputException | IllegalParameterInputException e) {
            throw new RuntimeException(e);
        }
    }

    private static class MonoDirectionNodeImpl_ extends MonoDirectionNode {
        public MonoDirectionNodeImpl_(Node next) {
            super(next);
        }

        public MonoDirectionNodeImpl_() {
        }

        @Override
        protected Map<String,Object> afterBody(Map<String, Object> stack, Map<String, Object> localStack) {
            return localStack;
        }
    }
}
