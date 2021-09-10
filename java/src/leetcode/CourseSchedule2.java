//https://leetcode.com/problems/course-schedule-ii/
//This problem is application of  topological sort  + loop in a directed graph
package src.leetcode;

import java.util.ArrayList;
import java.util.Stack;

public class CourseSchedule2 {

    //Variable to mark if a cycle is found
    private boolean cycle = false;

    public void dfs(ArrayList<Integer> graph[], int node, boolean visited[], boolean tvisited[], Stack<Integer> finished){
        //Mark the node as visited and tvisited
        visited[node] = true;
        tvisited[node] = true;
        if(cycle){
            return;
        }
        //Loop through each child
        for(int child : graph[node]){
            if(tvisited[child]){
                //If child is found in tvisited, there is a cycle at this node. So, topological sort is not applicable
                cycle = true;
                return;
            }
            //DFS on child if not visited
            if(!visited[child]){
                dfs(graph,child,visited,tvisited,finished);
            }
        }
        //Finally unmark tvisited on this node. So that this node can be reached via another path, which is not a loop
        tvisited[node] = false;
        //Once all the children are explored, add this node to stack. This is the topological order
        finished.push(node);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer> graph[] = new ArrayList[numCourses];
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList();
        }
        for(int edge[]:prerequisites){
            graph[edge[1]].add(edge[0]);
        }
        Stack<Integer> finished = new Stack();
        boolean visited[] = new boolean[numCourses];
        boolean tvisited[] = new boolean[numCourses];
        for(int i=0;i<numCourses;i++){
            if(!visited[i]){
                dfs(graph,i,visited,tvisited,finished);
            }
            //If cycle found already, no need to DFS
            if(cycle){
                break;
            }
        }
        // Convert stack to int
        int ans[] = new int[finished.size()];
        //If cycle found, return empty array
        if(cycle){
            return new int[0];
        }
        int ind = 0;
        while(!finished.isEmpty()){
            ans[ind++] = finished.pop();
        }
        return ans;
    }
}