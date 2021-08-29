//https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/submissions/

package src;

import java.util.HashMap;
import java.util.Map;

public class MinimumNumberOfWorkSessionsToFinishTask {
    //map to store the precomputed values
    Map<Integer,int[]> dp = new HashMap();

    public int[] solve(int mask, int tasks[], int sessionTime){
        //If mask is 0, then minimum number of sessions is 1 and last task is 0
        if(mask==0){
            return new int[]{1,0};
        }
        //Check if already computed
        if(dp.containsKey(mask)){
            return dp.get(mask);
        }
        int fans[] = {Integer.MAX_VALUE,Integer.MAX_VALUE};
        for(int i=0;i<tasks.length;i++){
            //Check answer for the state where ith task is not done
            if((mask&(1<<i))>0){
                int t[] = solve(mask^(1<<i),tasks,sessionTime);
                int temp[] = {0,0};
                //Check if ith task exceeds the session time
                if(t[1]+tasks[i]>sessionTime){
                    //Last task is tasks[i]
                    temp = new int[]{1+t[0],tasks[i]};
                }
                else{
                    //ith task doesn't exceed the session time
                    temp = new int[]{t[0],t[1]+tasks[i]};
                }
                //Update minimum
                if(temp[0]<fans[0]){
                    fans[0] = temp[0];
                    fans[1] = temp[1];
                }
                else if(temp[0]==fans[0]){
                    if(temp[1]<fans[1]){
                        fans[1] = temp[1];
                    }
                }
            }
        }
        dp.put(mask,fans);
        return fans;
    }

    public int minSessions(int[] tasks, int sessionTime) {
        return solve((1<<tasks.length)-1,tasks,sessionTime)[0];
    }

    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfWorkSessionsToFinishTask().minSessions(new int[]{1,2,3,4,5},15));

    }
}