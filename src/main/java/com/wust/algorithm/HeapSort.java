package com.wust.algorithm;

import java.util.Arrays;

public class HeapSort {
    private static int[] nums = {3,1,5,2,6,9};
    public static int[] sortArray(int[] nums) {
        return new int[0];
    }

    public static void downAdjust(int[] nums,int low,int high){
        int i = low , j = 2 * low + 1;
        while (j < high){
            if(j + 1 < high && nums[j] < nums[j+1]){
                j = j + 1;
            }
            if(nums[i] < nums[j]){
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
                i = j;
                j = i * 2 + 1;
            }else {
                break;
            }
        }
    }
    static void createHeap(int[] nums){
        for(int i = nums.length/2;i >= 0;i--){
            downAdjust(nums,i,nums.length);
        }
    }
    static void heapSort(int[] nums){
        createHeap(nums);
        for(int i = nums.length - 1;i > 0;i--){
            int t = nums[i];
            nums[i] = nums[0];
            nums[0] = t;
            downAdjust(nums,0,i);
        }
    }

    public static void main(String[] args) {
        heapSort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
