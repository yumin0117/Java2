package ai0825;

import java.util.Arrays;
import java.util.Collections;

public class SortArray {
    public static void main(String[] args) {
        Integer[] numArr = {77, 33, 11, 99, 22, 55, 88};
//        Arrays.sort(numArr, Collections.reverseOrder());  //내림차순
        Arrays.sort(numArr);    //오름차순
        for (int data : numArr){
            System.out.print(data + " ");
        }
        System.out.print("\n");

        String[] nameArr = {"김유민", "도형준", "강석현", "유재화", "장영서"};
//        Arrays.sort(nameArr); //오름차순
        Arrays.sort(nameArr, Collections.reverseOrder());   //내림차순
        for (String name : nameArr){
            System.out.print(name + " ");
        }
    }
}
