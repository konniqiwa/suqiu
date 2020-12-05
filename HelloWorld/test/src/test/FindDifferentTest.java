package test;

/**
 * 从一个连续的数组中找出缺少的元素
 */
public class FindDifferentTest {
    public static void main(String[] args) {
        /*int arr[] = {1,2,3,5};
        for (int i = 0; i < arr.length - 1; i++) {
            if ((arr[i+1] - arr[i]) != 1) {
                System.out.println(arr[i+1] - 1);
            }
        }*/

        /**
         * 冒泡排序
         */
        int  arry[] = {1, 8, 5, 2, 4, 9, 7, 10};
        for (int i = 0; i < arry.length - 1; i++) {
            int count=0;
            for (int j = i + 1; j < arry.length; j++) {
                if (arry[i] < arry[j]) {
                    count = arry[i];
                    arry[i] = arry[j];
                    arry[j] = count;
                }
            }
        }
        for (int i = 0; i < arry.length; i++) {
            System.out.print(arry[i]+" ");
        }
    }
}
