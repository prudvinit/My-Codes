package src.leetcode;

import java.util.*;

public class ParallelCourses2 {

    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int graph[][] = new int[n+1][n+1];
        int indegree[] = new int[n+1];
        boolean visited[] = new boolean[n+1];
        int childCount[] = new int[n+1];
        int parentCount[] = new int[n+1];
        int lvl[] = new int[n+1];
        for(int[] rel : relations){
            graph[rel[0]][rel[1]] =  1;
            indegree[rel[1]]++;
            childCount[rel[1]]++;
            parentCount[rel[0]]++;
        }
        Queue<Integer> queue = new LinkedList();
        for(int i=1;i<n;i++){
            if(indegree[i]==0) {
                queue.add(i);
            }
        }
        List<Integer> topoList = new ArrayList();
        Set<Integer> loners = new HashSet();
        while (!queue.isEmpty()){
            int e = queue.remove();
            visited[e] = true;
            topoList.add(e);
            System.out.println("Node "+e+" "+Arrays.toString(graph[e]));
            if(!(childCount[e]==0&&parentCount[e]==0)) {
//                topoList.add(e);
//                visited[e] = true;
                for (int i=0;i<graph[e].length;i++){
                    int child = i;
//                    System.out.println("Ch is "+child);
                    if (graph[e][child] != 0 && !visited[child]) {
                        System.out.println("Child "+child+" ");
                        lvl[child] = lvl[e]+1;
//                        visited[child] = true;
                        indegree[child]--;
                        if (indegree[child] == 0) {
                            System.out.println("Indegree is 0");
                            queue.add(child);
                        }
                    }
                }
            }
            else{
                loners.add(e);
            }
        }
        System.out.println("Loners "+loners);
        int topo[] = topoList.stream().mapToInt(x->x).toArray();
        int level[] = new int[topo.length];
        for(int i=0;i<level.length;i++){
            level[i] = lvl[topo[i]];
        }
        System.out.println("Topo  : "+Arrays.toString(topo));
        System.out.println("Level : "+Arrays.toString(level));
        int semister = 1;
        int courses = 1;
        int l = loners.size();
        System.out.println("Loners "+l);
        for(int i=1;i<topo.length;i++){
            System.out.println("Course is "+topo);
            if(loners.contains(topo[i])){
                continue;
            }
            if(courses==k){
                courses=1;
                semister++;
            }
            else{
                if(level[i]==level[i-1]){
                    courses++;
                }
                else{
                    courses = 1;
                    int space = k-courses;
                    System.out.println("Space left for loners "+space);
                    l-=Math.min(l,space);
                    semister++;
                    System.out.println(semister);
                }
            }
        }
        int t = l/k + (l%k!=0?1:0);
        return (!topoList.isEmpty()?semister:0) + t;

    }

    public static void main(String[] args) {
        System.out.println(new ParallelCourses2().minNumberOfSemesters(13,new int[][]{{12,8},{2,4},{3,7},{6,8},{11,8},{9,4},{9,7},{12,4},{11,4},{6,4},{1,4},{10,7},{10,4},{1,7},{1,8},{2,7},{8,4},{10,8},{12,7},{5,4},{3,4},{11,7},{7,4},{13,4},{9,8},{13,8}},9));
//        System.out.println(new ParallelCourses2().minNumberOfSemesters(5,new int[][]{{2,1},{3,1},{4,1},{1,5}},2));
//        System.out.println(new ParallelCourses2().minNumberOfSemesters(4,new int[][]{{2,1},{3,1},{1,4}},2));

    }
}