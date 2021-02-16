package com.google.common;

import com.google.common.annotations.GwtCompatible;
import com.google.common.primitives.UnsignedInts;
import junit.framework.TestCase;

import java.util.Arrays;

/**
 * Tests for UnsignedInts
 *
 * @author Louis Wasserman
 */
@GwtCompatible(emulated = true)
public class YuchenHuTest extends TestCase{
    private static final int[] testData = {
            -1,
            0,
            1,
            1245,
            1024 * 1024 * 1024 * 2,
            1024 * 1024 * 1024 * 4 - 1
    };

    private static final String[] expected = {
            "4294967295",
            "0",
            "1",
            "1245",
            "2147483648",
            "4294967295"
    };

    public void testToString() {
        for(int i = 0; i < testData.length; i ++) {
            assertEquals(UnsignedInts.toString(testData[i]), expected[i]);
        }

    }

    public void testSort() {
        testSort(new int[] {}, new int[] {});
        testSort(new int[] {-1, 1}, new int[] {1, -1});
        testSort(new int[] {-1, 0}, new int[] {0, -1});
        testSort(new int[] {1024 * 1024 * 1024 * 2, 1}, new int[]{1, 1024 * 1024 * 1024 * 2});
        testSort(new int[] {1024 * 1024 * 1024 * 4 - 2, -1}, new int[] {1024 * 1024 * 1024 * 4 - 2, -1});
    }

    static void testSort(int[] input, int[] expected) {
        input = Arrays.copyOf(input, input.length);
        UnsignedInts.sort(input);
        assertTrue(Arrays.equals(expected, input));
    }

}
