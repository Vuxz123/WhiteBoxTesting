package com.ethnicthv.sim.runtime.mem.frame;

import com.ethnicthv.sim.runtime.mem.IFrame;
import com.ethnicthv.sim.runtime.mem.IInvokedFrame;
import com.ethnicthv.sim.runtime.mem.frame.lv.LocalVariables;
import com.ethnicthv.sim.runtime.mem.frame.os.OperationStack;
import com.ethnicthv.sim.runtime.mem.frame.ra.ReturnAddress;

public class Frame implements IFrame, IInvokedFrame {
    private final IOperationStack operationStack;
    private final ILocalVariables localVariables;
    private IReturnAddress returnAddress;

    public Frame(int stackSize) {
        this.operationStack = new OperationStack();
        this.localVariables = new LocalVariables(stackSize);
    }

    public IOperationStack getOperationStack() {
        return operationStack;
    }

    public ILocalVariables getLocalVariables() {
        return localVariables;
    }


    public IReturnAddress getReturnAddress() {
        return returnAddress;
    }

    /**
     * Set The Return Ref Address
     * <br>Note: The ReturnRef can only set once
     *
     * @param ref the ref of the return
     */
    public void setReturnAddress(Object ref) {
        if (this.returnAddress != null) return;
        this.returnAddress = new ReturnAddress(ref);
    }

    @Override
    public void printDebug() {
        System.out.println("Frame: ");
        System.out.println("\tOperationStack: ");
        operationStack.printDebug();
        System.out.println("\tLocalVariables: ");
        localVariables.printDebug();
    }
}
