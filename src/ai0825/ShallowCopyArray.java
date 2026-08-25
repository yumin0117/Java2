package ai0825;

import java.util.Arrays;

public class ShallowCopyArray {
//    얕은 복사(메모리에 할당된 동일한 배열객체)
    public static void main(String[] args) {
        String[] foodArr = {"김치찌개", "삼겹살", "김치전", "피자"};
        String[] newArr = foodArr;

        foodArr[1] = "소고기";
        newArr[2] = "떡꼬치";

        System.out.println("원본배열: " + Arrays.toString(foodArr));
        System.out.println("복제배열: " + Arrays.toString(newArr));
    }
}
