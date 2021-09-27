package com.wust.algorithm;

import java.util.Arrays;

public class SerialSum {
    public static int getMaxLongestSerialSum(int[] arr){
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int ans = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = Math.max(arr[i],dp[i-1] + arr[i]);
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }

    public static int LIS(int[] arr){
        int[] dp = new int[arr.length];
        Arrays.fill(arr,1);
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[i] >= arr[j]){
                    dp[i] = dp[j] + 1;
                }
            }
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }

    public static int LCS(String s1, String s2){
        int dp[][] = new int[s1.length()+1][s2.length()+1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static int LHS(String str){
        int len = str.length();
        int dp[][] = new int[len][len];
        int ans = 1;
        for(int i = 0;i < len;i++){
            dp[i][i] = 1;
            if(i < len - 1 && str.charAt(i) == str.charAt(i+1)){
                dp[i][i+1] = 1;
                ans = 2;
            }
        }
        for(int l = 3;l <= len;l++){
            for(int i = 0;i + l -1 < len;i++){
                int j = i + l - 1;
                if(str.charAt(i) == str.charAt(j) && dp[i+1][j-1] == 1){
                    dp[i][j] = 1;
                    ans = l;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(getMaxLongestSerialSum(new int[]{-2,11,-4,13,-5,-2}));
        System.out.println(LIS(new int[]{1,2,3,-1,-2,7,9}));
        System.out.println(LCS("sadstory","adminsorry"));
        System.out.println(LHS("patzjujztaccbcc"));
    }
}
