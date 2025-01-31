1.Goal: Solving same overlapping subproblems again and again in plain recursion

2.Tabulation: Bottom-Up, Memoization: Top-Down

3.Memoization: Tend to store value of subproblems in some map|table
  Map or Table ?
  Like in Fibonacci - f(2) - it has 1 parameter, so, we can use 1D array as dp array
  If 0 is a legit output value, use -1 as init value of dp array

4.
To convert recursion to memoization: 
STEP1. Declaring an array of size of subproblems, dp[subproblem_size] = {-1}
STEP2. whatever is answer to subproblem, store in dp[subproblem_idx] array.
STEP3. if subproblem is previously solved, then dp[subproblem_idx] != -1
       and simply return dp[subproblem_idx]

5. 
Tabulation: Bottom-Up
Try to go from base case to the required answer

6.
How to figure out if it's Recursion problem or not -> then we can memoize it -> then tabularize it -> then space optimize it
Step1: Try to represent the problem in terms of index
Step2: Do all possible stuff on that index according to the problem statement, which is allowed to do - jump 1, jump 2, etc
Step3: Ques says:
- find either Sum of all stuffs to count all ways
- find either min of all stuffs
- find either max of all stuffs
- find either best of all stuffs

7. Recursion Thumb Rule:
   First build the base case

8. Greedy(fetch max at current stage and move on for next max or min as in ques) vs Recursion(Try all possible ways)
Greey not works on Frog Jump to N stair case.
coz, initially you might took a better path/way, but you lost out on something significant in future

10, when 0 is a legit value, try init with 1000000,INT_MAX, INF, -INF, -1, etc.
IMP_NOTE: INT_MAX #include <bits/stdc++.h>

11. 
if there is anything f(n-1) and or f(n-2)
that can always be space optimized like done in fibonacci dp and frogjump dp