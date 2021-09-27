package com.wust.algorithm;

public class Solution {
    public static boolean hasPath (char[][] matrix, String word) {
        // write code here
        boolean inq[][] = new boolean[matrix.length][matrix[0].length];
        char[] chars = word.toCharArray();
        for(int i = 0; i < matrix.length;i++){
            for(int j = 0;j < matrix[0].length;j++){
                if(dfs(matrix,chars,inq,i,j,0)){
                    return true;
                }
            }
        }

        return false;
    }
    public static boolean dfs(char[][] matrix,char[] chars,boolean[][] inq,int i ,int j,int index){
        if(i >= matrix.length || i < 0 || j >= matrix[0].length || j < 0 || inq[i][j] || matrix[i][j] != chars[index]){
            return false;
        }
        if(index == chars.length -1){
            return true;
        }
        inq[i][j] = true;
        if(dfs(matrix,chars,inq,i+1,j,index+1) || dfs(matrix,chars,inq,i-1,j,index+1)
                || dfs(matrix,chars,inq,i,j+1,index+1) || dfs(matrix,chars,inq,i,j-1,index+1)){
            return true;
        }
        inq[i][j] = false;

        return false;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'a','b','c','e'},
                {'s','f','c','s'},
                {'a','d','e','e'}
        };
        System.out.println(hasPath(matrix,"abcced"));
    }
}
