package cn.saintshaga.example.algorithm;

import java.util.ArrayList;
import java.util.List;

public class IPResolver {
    /**
     * Instruction about unclear requirements:
     * The returned value is {@code long} instead of {@code int} because for {@code int} type, 0xffffffff is actually
     * displayed as -1 in decimal.
     * If 4294967295 is expected to be displayed for ip "255.255.255.255", {@code long} type should be returned.
     * If -1 is expected to be displayed for ip "255.255.255.255", {@code int} type should be returned. If so, replacing
     * all {@code long} and {@code Long} into {@code int} and {@code Integer} will satisfy the requirement.
     *
     * Unit tests: see IPResolverTest for running
     * Valid cases:
     *     <pre>
     *         assertEquals(2896692481L, resolver.resolve("172.168.5.1"));
     *         assertEquals(0L, resolver.resolve("0.0.0.0"));
     *         assertEquals(2896692481L, resolver.resolve(" 172  . 168 . 5 . 1 "));
     *         assertEquals(0xffffffffL, resolver.resolve("255.255.255.255"));
     *     </pre>
     *
     * Invalid cases:
     *     <pre>
     *         null,  //null
     *         "", //empty
     *         " ",  //blank
     *         "...", //only points
     *         "123", //only numbers
     *         "1 2 3 4", //only numbers and spaces
     *         " . . . ", //only spaces and points
     *         "a2.3.2.2", //illegal character
     *         "1.-3.3.3", //illegal character
     *         "1.3.3.3u", //illegal character
     *         "17 2.1.1.1", //wrong spaces in first number
     *         "172.1.3 4.1", //wrong spaces in the middle
     *         "172.1.1.2 3", //wrong spaces in last number
     *         "172.1.1.2.3", //more than 4 numbers
     *         "172.1.1", //less than 4 numbers
     *         "00.0.0.0", //leading 0 for 0 in first number
     *         "0.0.0.00", //leading 0 for 0 in last number
     *         "01.1.0.0", //leading 0 for non-zero in first number
     *         "1.1.0.01", //leading 0 for non-zero in last number
     *         " .172.0.0.1", //leading spaces and leading points
     *         "143.3.2.1.", //ending with point
     *         "143.3.2.1 .", //ending space and point
     *         "256.2.2.2", //exceed number limit
     *         "2..3.4", //duplicate points
     *         "2.3.4.." //duplicate points
     *     </pre>
     */
    public long resolve(String ipAddress) {
        if(ipAddress == null || ipAddress.length() == 0) {
            invalidIPException(ipAddress);
        }
        List<Long> numbers = new ArrayList<>(4);
        int i = 0;
        StringBuilder builder = new StringBuilder(3);
        boolean endOfNumber = false;
        int pointCount = 0;
        while(i < ipAddress.length()) {
            if(builder.length() == 0 && ipAddress.charAt(i) == ' ') {
                //ignore leading spaces of each number
            } else if(!endOfNumber && isDigit(ipAddress.charAt(i))) {
                builder.append(ipAddress.charAt(i));
            } else if(ipAddress.charAt(i) == ' ') {
                endOfNumber = true;
            } else if(ipAddress.charAt(i) == '.' && builder.length() > 0 && builder.length() <= 3) {
                pointCount++;
                numbers.add(digitStrToVal(ipAddress, builder.toString()));
                builder.delete(0, builder.length());
                endOfNumber = false;
            } else {
                invalidIPException(ipAddress);
            }
            i++;
        }
        if(builder.length() == 0 || builder.length() > 3) {
            invalidIPException(ipAddress);
        }
        numbers.add(digitStrToVal(ipAddress, builder.toString()));
        if(numbers.size() != 4 || pointCount != 3) {
            invalidIPException(ipAddress);
        }
        long result  = 0L;
        for(long number : numbers) {
            result = (result << 8) | number;
        }
        return result;
    }

    private long digitStrToVal(String ipAddress, String str) {
        long value = Long.parseLong(str);
        if(value > 255 || (str.length() > 1 && str.charAt(0) == '0')) {
            invalidIPException(ipAddress);
        }
        return value;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void invalidIPException(String ipAddress) {
        throw new IllegalArgumentException("Invalid IP Address:" + ipAddress);
    }

    public static void main(String[] args) {
        IPResolver resolver = new IPResolver();
        System.out.println(resolver.resolve("172.168.5.1"));
    }
}
