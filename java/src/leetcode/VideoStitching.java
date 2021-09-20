package src.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class VideoStitching {
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        for(int[] clip : clips) {
            System.out.print(Arrays.toString(clip) + " ");
        }
        int n = clips.length;
        int dp[] = new int[n];
        int MAX = 100000;
        for(int i=n-1;i>=0;i--){
            dp[i] = MAX;
            if(clips[i][1]>=time){
                dp[i] = 1;
            }
            for(int j=i+1;j<n;j++){
                if(clips[j][0]<=clips[i][1]&&dp[j]!=MAX){
                    dp[i] = 1+Math.min(dp[i],dp[j]);
                }
            }
        }
        int ans = MAX;
        for(int i=0;i<n;i++){
            if(clips[i][0]==0){
                ans = Math.min(ans,dp[i]);
            }
        }
        return ans!=MAX?ans:-1;
    }

    public static void main(String[] args) {
        System.out.println(new VideoStitching().videoStitching(new int[][]{{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}},10));
        System.out.println(new VideoStitching().videoStitching(new int[][]{{0,1},{1,2}},10));
        System.out.println(new VideoStitching().videoStitching(new int[][]{{0,4},{2,8}},5));
        System.out.println(new VideoStitching().videoStitching(new int[][]{{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}},5));
    }
}
