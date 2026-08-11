package io.herald.springboot.testPackage;

import org.springframework.data.domain.Vector;

import java.util.*;

public class CollectionFrameWork {
    public static void main(String[] args) {
        // List => ArrayList, LinkedList and Vector

//        List<Integer> intList = new Vector<>();
//        intList .add(1);

        Set<Integer> set = new TreeSet<>();
        set.add(1);
//        Set -> HashSet,LinkedHashSet abd Tree SET = No duplicate data

//        Map -> HashMap, LinkedHashMap and Tree Map

        Map<Integer,String> map = new HashMap<>();
        map.put(1,"Apple");
        map.put(2,"Banana");

        System.out.println(map);

    }
}
