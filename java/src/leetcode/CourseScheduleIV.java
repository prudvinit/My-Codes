package src.leetcode;

import java.util.*;

public class CourseScheduleIV {
    int time = 0;
    void topo(ArrayList<Integer> graph[], int v, boolean visited[], Stack<int[]> top){
        visited[v] = true;
        for(int child : graph[v]){
            if(!visited[child]){
                topo(graph,child,visited,top);
            }
        }
        top.add(new int[]{v,time++});
    }
    ArrayList<Integer>[] graph(int arr[][],  int n){
        ArrayList<Integer> ans[] = new ArrayList[n];
        for(int i=0;i<n;i++){
            ans[i] = new ArrayList();
        }
        for(int e[] : arr){
            ans[e[1]].add(e[0]);
        }
        return ans;

    }


    public List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {
        ArrayList<Integer> graph[] = graph(prerequisites,numCourses);
        boolean visited[] = new boolean[numCourses];
        Stack<int[]> stack = new Stack();
        for(int i=0;i<numCourses;i++){
            time = 0;
            if(!visited[i]){
                topo(graph,i,visited,stack);
            }
        }
        int ind=0;
        int t[][] = new int[numCourses][2];
        int order[] = new int[numCourses];
        int o=0;
        while(!stack.isEmpty()){
            t[ind++] = stack.pop();
            order[t[ind-1][0]] = o++;
        }
        System.out.println(Arrays.toString(t));
        System.out.println(Arrays.toString(order));
        List<Boolean> ans = new ArrayList();
        for(int[] q : queries){
            ans.add(!(t[order[q[0]]][0]<t[order[q[1]]][0]));
        }
        return ans;
    }

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        ArrayList<Integer> graph[] = graph(prerequisites,numCourses);
        List<Boolean> list = new ArrayList();
        for(int q[] : queries){
            boolean visited[] = new boolean[numCourses];
            Queue<Integer> queue = new LinkedList();
            queue.add(q[0]);
            while (queue.isEmpty()){
                int e = queue.remove();
                visited[e] = true;
                for(int c : graph[e]){
                    if(!visited[c]){
                        queue.add(c);
                    }
                }
            }
            list.add(visited[q[1]]);
        }
        return list;
    }

    public static void main(String[] args) {

    }
}