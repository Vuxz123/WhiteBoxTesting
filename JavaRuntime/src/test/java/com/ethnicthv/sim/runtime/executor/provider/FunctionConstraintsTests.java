package com.ethnicthv.sim.runtime.executor.provider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FunctionConstraintsTests {
    @DisplayName("Test su day du cua InstructionsEnum bang cach tinh tong")
    @Test
    public void testInstructionsEnum1() {
        FunctionConstraints.E[] enums = FunctionConstraints.E.indexedInstruction;
        double actualSum = 0;
        for (FunctionConstraints.E e : enums) {
            actualSum += e.form;
        }
        double expectedSum = (201D) / 2 * 202;
//        System.out.println("Actual: " + actualSum );
//        System.out.println("Expected: " + expectedSum);
        Assertions.assertEquals(expectedSum, actualSum);
    }

    @DisplayName("Test tinh dung dan cua 2 vi tri dau va cuoi")
    @Test
    public void testInstructionsEnum2() {
        FunctionConstraints.E[] enums = FunctionConstraints.E.indexedInstruction;
        int l = enums.length;

        int lastForm = enums[l - 1].form;
        int lastIndex = l - 1;
        Assertions.assertEquals(lastIndex, lastForm);

        int firstForm = enums[0].form;
        int firstIndex = 0;
        Assertions.assertEquals(firstIndex, firstForm);
    }

    @DisplayName("Test tinh dung dan cua thu tu trong InstructionsEnum")
    @Test
    public void testInstructionsEnum3() {
        FunctionConstraints.E[] enums = FunctionConstraints.E.indexedInstruction;
        boolean checkGap = true;
        int l = enums.length;
        for (int i = 1; i < l; i++) {
            if (enums[i].form - enums[i - 1].form < 0) {
                checkGap = false;
                break;
            }
        }
        Assertions.assertTrue(checkGap);
    }
}
