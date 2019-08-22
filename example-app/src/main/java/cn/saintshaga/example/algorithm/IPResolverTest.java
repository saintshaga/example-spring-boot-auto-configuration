package cn.saintshaga.example.algorithm;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class IPResolverTest {

    private IPResolver resolver;

    @Before
    public void initial() {
        resolver = new IPResolver();
    }

    @Test
    public void correctCases() {
        assertEquals(2896692481L, resolver.resolve("172.168.5.1"));
        assertEquals(2896692481L, resolver.resolve("   172 . 168 .5 . 1  "));
        assertEquals(2896692481L, resolver.resolve(" 172  . 168.5 . 1  "));
        assertEquals(0xffffffffL, resolver.resolve("255.255.255.255"));
        assertEquals(2896692481L, resolver.resolve("172 . 168 .5 . 1"));
        assertEquals(2896692481L, resolver.resolve(" 172.168.5.1 "));
        assertEquals(2896692481L, resolver.resolve(" 172 .168 .5 . 1"));
        assertEquals(2896692481L, resolver.resolve("0000000000000000000000000000000000000000000172.168.5.1"));
    }

    @Test
    public void invalidCases() {
        for(String ip : invalidIP()) {
            try {
                resolver.resolve(ip);
                assertTrue(false);
            } catch(IllegalArgumentException e) {
                assertTrue(true);
            }
        }
    }

    private List<String> invalidIP() {
        return Arrays.asList(
                null,  //null
                "", //empty
                " ",  //blank
                "...", //only points
                "123", //only numbers
                "1 2 3 4", //only numbers and spaces
                " . . . ", //only spaces and points
                "a2.3.2.2", //illegal character
                "1.-3.3.3", //illegal character
                "1.3.3.3u", //illegal character
                "17 2.1.1.1", //wrong spaces in first number
                "172.1.3 4.1", //wrong spaces in the middle
                "172.1.1.2 3", //wrong spaces in last number
                "172.1.1.2.3", //more than 4 numbers
                "172.1.1", //less than 4 numbers
                "00.0.0.0", //leading 0 for 0 in first number
                "0.0.0.00", //leading 0 for 0 in last number
                "01.1.0.0", //leading 0 for non-zero in first number
                "1.1.0.01", //leading 0 for non-zero in last number
                " .172.0.0.1", //leading spaces and leading points
                "143.3.2.1.", //ending with point
                "143.3.2.1 .", //ending space and point
                "256.2.2.2", //exceed number limit
                "2..3.4", //duplicate points
                "2.34..", //duplicate points
                "17 2.168.5.1",
                "256.23.4.1",
                ".1.23.4.1",
                ".23.4.1",
                "a.b.c.d",
                "a.b.1.2",
                "0000000000000000000000000000000000000000000172.168.5.1"
        );
    }
}
