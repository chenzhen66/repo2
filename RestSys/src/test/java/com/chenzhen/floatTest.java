package com.chenzhen;

import com.chenzhen.utils.BaseCalculate;
import org.junit.jupiter.api.Test;

public class floatTest {
    @Test
    public void test1(){
        float q = 2.4f;
        int a = 6;
        float result = BaseCalculate.multiply(q,a);
        System.out.println(result);
    }
}
