package com.demo;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;


@RunWith(value = Parameterized.class)
public class ParameterizedTest {

    //default value = 0
    @Parameter(value = 0)
    public int numberA;

    @Parameter(value = 1)
    public int numberB;

    @Parameter(value = 2)
    public int expected;

    @Parameters(name = "{index}: testAdd({0}+{1}) = {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1, 2},
                {2, 2, 4},
                {8, 2, 20},
                {4, 5, 9},
                {5, 5, 10}
        });
    }

    @Test
    public void test_addTwoNumbes() {
        assertEquals(expected, numberA + numberB);
    }
}