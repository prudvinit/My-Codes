//https://leetcode.com/problems/find-the-shortest-superstring/
//81/83 passed
//Traveling salesperson problem
package src.leetcode;

import java.util.*;

public class FindTheShortestSuperstring {

    private final static int MAX = Integer.MAX_VALUE;

    String q[];

    public int minAdditions(String a, String b){
        int ans = b.length();
        for(int j=1;j<=Math.min(a.length(),b.length());j++){
            if(a.substring(a.length()-j).equalsIgnoreCase(b.substring(0,j))){
                ans = Math.min(ans,b.length()-j);
            }
        }
        return ans;
    }

    public String shortestSuperstring(String[] words) {
        if(words.length==1)return words[0];
        int n = words.length;
        int MAX = Integer.MAX_VALUE;
        int graph[][] = new int[n][n];
        this.q = words;
        for(int i=0;i<n;i++){
            Arrays.fill(graph[i],MAX);
        }
        for(int i=0;i<n;i++){
            graph[i][i]=0;
            for(int j=i+1;j<n;j++){
                graph[i][j] = minAdditions(words[i],words[j]);
                graph[j][i] = minAdditions(words[j],words[i]);
            }
        }
        int ans = MAX;
        String finalAns = null;
        int parents[][] = null;
        int s=0,e = 0;
        for(int start=0;start<n;start++){
            for(int end=0;end<n;end++){
                if(start==end){
                    continue;
                }
                int dp[][] = new int[n][1<<n];
                int par[][] = new int[n][1<<n];
                for(int i=0;i<dp.length;i++){
                    Arrays.fill(dp[i],MAX);
                }
                for(int i=0;i<n;i++){
                    dp[i][0] = graph[start][i];
                }
                int min = runTravelingSalesman(graph, end, ((1<<n)-1)^(1<<start)^(1<<end),dp, par);
                System.out.println(start+" "+end+" "+min);
                if(min<=ans){
                    ans = min;
                    parents = par;
                    s = start;
                    e = end;
                    String build = buildString(n,s,e,parents);
                    if(finalAns==null){
                        finalAns = build;
                    }
                    if(build.length()<finalAns.length()){
                        finalAns = build;
                    }
                }
            }
        }
        return finalAns;
    }

    public String buildString(int n, int s, int e , int parents[][]){
        int mask = ((1<<n)-1)^(1<<s)^(1<<e);
        Stack<String> tans = new Stack();
        while (tans.size()<n-1){
            tans.add(q[e]);
            int next = parents[e][mask];
            mask = mask^(1<<next);
            e = next;
        }
        tans.add(q[s]);
        String finalAns = "";
        while(!tans.isEmpty() ){
            String ss = tans.pop();
            int m = minAdditions(finalAns,ss);
            finalAns = finalAns+ss.substring(ss.length()-m);
        }
        return finalAns;
    }

    public int runTravelingSalesman(int graph[][], int target, int through, int dp[][], int par[][]){
        if(dp[target][through]!=MAX){
            return dp[target][through];
        }
        int n = graph.length;
        int ans = MAX;
        for(int j=0;j<n;j++){
            if((through&(1<<j))>0){
                if(graph[j][target]!=MAX) {
                    int t = runTravelingSalesman(graph,j,through^(1<<j),dp,par);
                    if(graph[j][target]+t<ans){
                        ans = graph[j][target]+t;
                        par[target][through] = j;
                    }
                }
            }
        }
        dp[target][through] = ans;
        return ans;
    }


    public static void main(String[] args) {
//        System.out.println(new FindTheShortestSuperstring()
//                .shortestSuperstring(new String[]{"alex","loves","leetcode"}));
//        System.out.println(new FindTheShortestSuperstring()
//                .shortestSuperstring(new String[]{"abc","bcd"}));
//        System.out.println(new FindTheShortestSuperstring()
//                .shortestSuperstring(new String[]{"a","b"}));
//        System.out.println(new FindTheShortestSuperstring()
//                .shortestSuperstring(new String[]{"catg","ctaagt","gcta","ttca","atgcatc"}));
//        System.out.println(new FindTheShortestSuperstring()
//                .shortestSuperstring(new String[]{"gruuz","zjr","uuzj","rfgr"}));
//        System.out.println(new FindTheShortestSuperstring()
//                .shortestSuperstring(new String[]{"a"}));
        System.out.println(new FindTheShortestSuperstring()
                .shortestSuperstring(new String[]{"bzdrk","aps","sntxb","psntx"}));

    }
}
