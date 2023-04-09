package src.google2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestCycle {

    public int longestCycle(int[] edges) {
        int ans = -1;
        int time = 1;
        int times[] = new int[edges.length];
        Arrays.fill(times, -1);
        for (int i = 0; i < edges.length; i++) {
//            System.out.println("DFSing " + i + " " + times[i]);
            if (times[i] == -1) {
                times[i] = time++;
                int next = edges[i];
                while (next!=-1&&times[next] == -1) {
//                    System.out.println("next " + next);
                    times[next] = time++;
                    next = edges[next];
                }
                if(next!=-1&&times[next]>=times[i])
                    ans = Math.max(ans, time - times[next]);
//                System.out.println(ans);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LongestCycle().longestCycle(new int[]{-1,4,-1,2,0,4}));
        System.out.println(new LongestCycle().longestCycle(new int[]{3,3,4,2,3}));
        System.out.println(new LongestCycle().longestCycle(new int[]{2,-1,3,1}));
    }
}