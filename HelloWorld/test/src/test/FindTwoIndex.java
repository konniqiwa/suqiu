package test;

import java.util.ArrayList;
import java.util.List;


/**
 * 已知一个int数组和一个target，找处数组两个元素相加之后等于target对应的位置
 * 如输入int numbers[] = {2,11,7,13,15}，target=9;
 * 输出[0, 2]
 */
public class FindTwoIndex {
    public static void main(String[] args) {
        int numbers[] = {2,11,7,13,15};
        int target = 15;
        System.out.println(twoNum(numbers,target));
    }

    /**
     * 若相加等于target，则加入list并返回
     * @param numbers
     * @param target
     * @return
     */
    public static List<Integer> twoNum(int[] numbers, int target) {

        List<Integer> list = new ArrayList<>();

        for (int i=0; i<numbers.length-1; i++) {
            for(int j = i + 1;j<numbers.length;j++) {
                if (numbers[i]+numbers[j] == target) {
                    list.add(i);
                    list.add(j);
                }
            }
        }

        return list;
    }
}
