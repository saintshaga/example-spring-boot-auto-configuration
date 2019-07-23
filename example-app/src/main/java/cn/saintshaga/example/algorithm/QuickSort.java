package cn.saintshaga.example.algorithm;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuickSort {
    public void quickSort(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return;
        }
        quickSort(nums, 0, nums.length-1);
    }
    private void quickSort(int[] nums, int st, int ed) {
        if(st >= ed) {
            return;
        }
        int num = nums[ed];
        int i = st, bigger = ed;
        while(i < bigger) {
            if(nums[i] <= num) {
                i++;
            } else {
                swap(nums, i, --bigger);
            }
        }
        swap(nums, bigger, ed);
        quickSort(nums, st, bigger-1);
        quickSort(nums, bigger+1, ed);
    }

    private void swap(int[] nums, int i, int j) {
        if(i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1, 1};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(nums);
        System.out.println(IntStream.of(nums).boxed().collect(Collectors.toList()));
    }


}
