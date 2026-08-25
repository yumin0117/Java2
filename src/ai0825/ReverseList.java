package ai0825;

import java.util.Arrays;
import java.util.Collections;

public class ReverseList {
    public static void main(String[] args) {
        String[] newjeans = {"다니엘", "하니", "민지", "해린", "혜인"};
        System.out.println("원본: " + Arrays.toString(newjeans));
        Collections.reverse(Arrays.asList(newjeans));
        System.out.println("반전(역순): " + Arrays.toString(newjeans));
    }
}
