package test;

import java.util.Arrays;
import java.util.Collections;

public class ArraySortTest {
    public static void main(String[] args) {
//        注意这里是Integer，不是int
        Integer[] arr={9,8,7,6,5,4,3,2,1};
        Arrays.sort(arr, Collections.reverseOrder());
        for(int i:arr){
            System.out.println(i);
        }
    }
}
