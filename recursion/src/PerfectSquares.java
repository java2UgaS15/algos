//source: https://leetcode.com/problems/perfect-squares/

//Given a positive integer n, find the least number of perfect square numbers 
//(for example, 1, 4, 9, 16, ...) which sum to n.
//
//For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, 
//return 2 because 13 = 4 + 9.
//
//Credits:
//Special thanks to @jianchao.li.fighter for adding this problem and 
//creating all test cases.

public class Solution {
    public int numSquares(int n) {
        int m = (int)Math.sqrt(n);
        if (m * m == n) //n is a perfect square
           return 1;
           
        //Hard: find out LEAST number of perfect square numbers that sum to n.
        //There is no shortcut, only to check all possible sums of n.
        //But this approach results in time limit exceeded when n = 32.
        /*
        int minSum = 1;
        int sum;
        for (int i = 1; i <= n/2; i++) {
            sum = numSquares(i) + numSquares(n-i);
            if (sum < minSum)
               minSum = sum;
        }
        
        return minSum;
        */
        
        //an easy fix to the above approach: book-keeping num squares 
        //for previous values in an array.
        int[] minNumSquares = new int[n+1];
        for (int i = 1; i <= Math.sqrt(n); i++) {
            minNumSquares[i*i] = 1;
            
            //The following statement is an optional speedup.
            //Note that since n is not a perfect square,
            //(i * i +1 <= n) when i <= Math.sqrt(n). 
            minNumSquares[i*i+1] = 2;
        }
        
        int minSum; 
        int sum;
        for (int i = 2; i < n+1; i++) {
            if (minNumSquares[i] == 1 || minNumSquares[i] == 2)
               continue;
               
            //Calculate minNumSquares[i] when i is 0.
            minSum = n; //initialize minSum to be the worst case 1 + 1 + ... + 1
            for (int j = 1; j <= i/2; j++) {
                sum = minNumSquares[j] + minNumSquares[i-j];
                if (sum == 2) { 
                   //min number of integers to sum up to non-perfect-square n is 2.
                   minSum = 2;
                   break;
                }
                
                //sum > 2
                if (sum < minSum)
                   minSum = sum;
            }
            minNumSquares[i] = minSum;
        }
        
        return minNumSquares[n];
    }
}
