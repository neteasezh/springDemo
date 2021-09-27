package com.wust.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static ListNode solve (ListNode[] a) {
        // write code here
        if(a == null || a.length == 0){
            return null;
        }
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        List<List<Integer>> ls = new ArrayList<>(a.length);
        for (int i = 0; i < ls.size(); i++) {
            ls.add(new ArrayList<>());
        }
        for (int i = 0; i < a.length; i++) {
            ListNode node = a[i];
            while(node != null){
                ls.get(i).add(node.val);
            }
        }
        int max = 0;
        for (List<Integer> l : ls) {
            max = Math.max(l.size(),max);
        }
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < ls.size(); j++) {
                if(ls.get(j).size()-1 >= i){
                    List<Integer> ll = ls.get(i);
                    head.next = new ListNode(ll.get(i));
                    head = head.next;
                }
            }
        }
        return pre.next;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        System.out.println(getRes(n,l,r));
    }

    public static int getRes(int n, int l, int r) {
        List<Integer> ls = new ArrayList<>();
        ls.add(n);
        int index = -1;
        l = l -1;
        r = r - 1;
        while ((index = test(ls)) != -1) {
            int num = ls.get(index);
            int a = num / 2;
            int b = num % 2;
            int c = num / 2;
            insert(ls, index, a, b, c);
        }
        int count = 0;
        if(l >= ls.size()){
            return 0;
        }
        for (int i = l; i <= r; i++) {
            if(i >= ls.size()){
                break;
            }
            if(ls.get(i) ==  1){
                count++;
            }
        }
        return count;
    }
    public static int test(List<Integer> ls){
        for (int i = 0; i < ls.size(); i++) {
            if(ls.get(i) > 1){
                return i;
            }
        }
        return -1;
    }
    public static void insert(List<Integer> ls, int index,int a, int b, int c){
        ls.add(index,a);
        ls.add(index+1,b);
        ls.add(index + 2,c);
        ls.remove(index + 3);
    }
}
