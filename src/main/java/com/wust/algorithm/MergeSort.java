package com.wust.algorithm;

public class MergeSort {
    public static void merge(int a[], int l1,int r1,int l2,int r2){
        int index = 0;
        int temp[] = new int[r2-l1+1];
        int i = l1, j = l2;
        while(i <= r1 && j <= r2){
            if(a[i] <= a[j]){
                temp[index++] = a[i++];
            }else{
                temp[index++] = a[j++];
            }
        }
        while(i <= r1) temp[index++] = a[i++];
        while(j <= r2) temp[index++] = a[j++];
        for(int k = 0; k < index;k++){
            a[l1+k] = temp[k];
        }
    }

    public static void mergeSort(int a[], int left, int right){
        if(left < right){
            int mid = (left + right)/2;
            mergeSort(a,left,mid);
            mergeSort(a,mid+1,right);
            merge(a,left,mid,mid+1,right);
        }
    }
    public static void main(String[] args) {
        int a[] = {3,2,1,6,5,4,4,6};
        //mergeSort(a,0,a.length-1);
        mergeSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void mergeSort(int a[]){
        int n = a.length;
        for(int step = 2; step / 2 <= n; step *= 2){
            for(int i = 0;i < n;i += step){
                int mid = (i + i +step -1) / 2;
                if(mid + 1 < n){
                    merge(a,i,mid,mid+1,Math.min(n-1,i+step-1));
                }
            }
        }
    }
}
