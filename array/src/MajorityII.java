//source: https://leetcode.com/problems/majority-element-ii/

//Given an integer array of size n, find all elements that appear more than floor(n/3)  times.
//The algorithm should run in linear time and in O(1) space.

//related: very similar to count sort, which is used by radix sort.
//hint: whenever there is tally/sort problem requiring O(n) time and O(1) space,
//      count sort and its variant is the first one to be considered. 

//key ideas: 
//1. Cut an integer into four group, each has 8-bit.
//2. Count the frequency of each group.
//3. (key!!) The difficult part is to carry one group's frequency to the next group. 
//    3.1 We maintain a list of suffixes that have enough frequencies. 
//          When we count frequencies of current group, 
//          we only count those elements with matched suffixes. 
//    3.2 If current group still has enough frequency, then we expand the suffix with 
//          current group.
//4. The idea can be generalized to find all elements of a given frequency.

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        LinkedList<Integer> lst = new LinkedList<Integer>();
        
        if (n < 1)
           return lst;
        
        //Next we concentrate on case n >= 1.
        lst.add(0); //Add a matched-suffix when shift = 0 (no shift happens), 
            //since mask = (1 << shift) -1 = 0 when shift = 0,
            //any integer and (&) mask is 0.
        List<Integer> lst2 = new LinkedList<Integer>();
        for (int shift = 0; shift < 32; shift += 8) //shift means bits shifted left
            if (shift == 0 || shift == 16)
               getSuffix(nums, shift, lst, lst2);
            else //shift == 8 || shift == 24
                 getSuffix(nums, shift, lst2, lst);
        
        return lst;
    }
    
    void getSuffix(int[] nums, int shift, List<Integer> lst, List<Integer> lst2) {
        int n = nums.length;
        int mask = (1 << shift) -1; //1 << shift equals 2^shift
        int suffix;
        int[] count = new int[256]; //values of 8-bit group range from 0 to 255.
        //for (int k = 0; k < lst.size(); k++) { 
             //WRONG: we remove elements from lst, and lst.size() 
             //is shrinked in each round. So k should always maintain 0.
        for (int k = 0; k < lst.size(); ) {
            suffix = lst.remove(k);
            for (int i = 0; i < n; i++)
                //The following statement is a key.
                //When we count frequencies of current group,
                //we only count those elements with matched suffixes. 
                //count the frequency of (shift/8) 8-bit group, 
                //whose last shift-bit equals suffix.
                if ((nums[i] & mask) == suffix)
                   count[(nums[i] >> shift) & 0xFF]++;
                   
            for (int j = 0; j < count.length; j++)
                if (count[j] > n/3)
                   lst2.add((j << shift) | suffix);
                   //If current group still has enough frequency, 
                   //then we expand the suffix with current group using 
                   //(j << shift) | suffix.
            
            //Efficiency consideration: there are at most two elements 
            //with frequency > n/3.
            //Prove that there are at most 2 elements with frequency >= floor(n/3) 
            //for an array of size n.
            //Here frequency is defined as the number of appearances.
            //Prove by contradiction.
            //(1) When n = 3*k for some integer k, then floor(n/3) = k. 
            //    Suppose there are three elements whose frequency > k,
            //    Then we would have > 3*k elements. 
            //    A contradiction to the size of n = 3*k.
            //(2) When n = 3*k+1 for some integer k, then floor(n/3) = k.
            //    If there were three elements whose frequency > k,
            //    Then we would have >= 3*(k+1) = 3*k + 3 > 3*k +1 elements.
            //(3) When n = 3*k+2 for some integer k, we can prove as Case (2).
            //
            //If we need to work with other frequency, then we need to take out
            //the following if-statement.  
            if (lst2.size() > 2) 
               break;
               
            clear(count);
        }
    }
    
    //Set each element of array count to be zero.
    void clear(int[] count) {
        for (int i = 0; i < count.length; i++)
            count[i] = 0;
    }
}
