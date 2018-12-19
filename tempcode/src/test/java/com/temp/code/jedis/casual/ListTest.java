package com.temp.code.jedis.casual;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    @Test
    public void toArray() throws Exception {
        List<String> strings = Arrays.asList("abc", "kkk");
        String[] strings1 = toArray(strings, new String[0]);
        System.out.println(strings1);
    }

    public <T> T[] toArray(List<T> t, T[] original) {
        T[] a = Arrays.copyOf(original, t.size());
        T[] expect = t.toArray(a);
        return expect;
    }
}
