package com.temp.code.jedis.casual;

import org.junit.Test;

import java.util.*;

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

    @Test
    public void treeMapTest() throws Exception {

        //找到绝对小于一个数的最大数
        TreeMap<Long,String> tree = new TreeMap<>();
        tree.put(10L,"a");
        tree.put(99L,"a");
        tree.put(100L,"b");
        tree.put(150L,"b");
        tree.put(200L,"x");
        Map.Entry<Long, String> longStringEntry = tree.lowerEntry( 10+1L);
        Map.Entry<Long, String> longStringEntry2 = tree.lowerEntry( 99L);
        System.out.println(longStringEntry.getValue()+"--"+longStringEntry.getKey());
        System.out.println(longStringEntry2.getValue()+"---"+longStringEntry2.getKey());
    }
}
