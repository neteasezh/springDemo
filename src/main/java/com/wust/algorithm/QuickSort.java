package com.wust.algorithm;

import java.util.Random;

public class QuickSort {
    public static int partition(int a[] ,int left, int right){
        int p = new Random().nextInt(right-left+1) + left;
        int t = a[p];
        a[p] = a[left];
        a[left] = t;
        int temp = a[left];
        while(left < right){
            while (left < right && a[right] > temp) right--;
            a[left] = a[right];
            while (left < right && a[left] <= temp) left++;
            a[right] = a[left];
        }
        a[left] = temp;
        return left;
    }

    public static void quickSort(int a[], int left, int right){
        if(left < right){
            int index = partition(a,left,right);
            quickSort(a,left,index-1);
            quickSort(a,index+1,right);
        }
    }

    public static void main(String[] args) {
        int a[] = {3,2,1,6,5,4};
        quickSort(a,0,a.length-1);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
