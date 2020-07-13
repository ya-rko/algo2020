package algo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import algo.hash.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class HashMapTest {
    private final HashMap<String, Integer> map = new HashMap<>();

    @BeforeEach
    void init() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
    }


    @Test
    void getValues() {
        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertEquals(3, map.get("three"));
        assertEquals(4, map.get("four"));
        assertEquals(5, map.get("five"));
        assertEquals(6, map.get("six"));
        assertEquals(7, map.get("seven"));
        assertEquals(8, map.get("eight"));
        assertEquals(9, map.get("nine"));
    }


    @Test
    void keyExistsChangeValue() {
        map.put("one", 111);
        assertNotEquals(1, map.get("one"));
        assertEquals(111, map.get("one"));
    }


    @Test
    void removeEntries() {
        map.remove("three");
        map.remove("nine");
        assertNull( map.get("three"));
        assertNull( map.get("nine"));
    }
}
