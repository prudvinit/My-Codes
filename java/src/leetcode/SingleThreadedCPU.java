package src.leetcode;

import java.util.*;

public class SingleThreadedCPU {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        Integer ind[] = new Integer[n];
        int ans[] = new int[n];
        int index=0;
        Arrays.setAll(ind,i->i);
        //Sort the indices
        Arrays.sort(ind,(a,b)->{
            //If start times are same, sort by length
            if(tasks[a][0]==tasks[b][0]){
                return tasks[a][1]-tasks[b][1];
            }
            //If start times are not same, sort by start times
            return tasks[a][0]-tasks[b][0];
        });
        //PriorityQueue to store the times
        PriorityQueue<Integer> pq = new PriorityQueue((Comparator<Integer>) (o1, o2) -> {
            if(tasks[o1][1]!=tasks[o2][1]){
                return tasks[o1][1]-tasks[o2][1];
            }
            return o1-o2;
        });
        //First one is our first answer
        ans[index++] = ind[0];
        //End time for first task
        int time = tasks[ind[0]][0]+tasks[ind[0]][1];
        int i=1;
        while (i<ind.length||!pq.isEmpty()) {
            //If pq is empty, set timeto next element start
            if(pq.isEmpty()&&time<tasks[ind[i]][0]){
                time = tasks[ind[i]][0];
            }
            //Add all the elements which were enqueued befor time
            while (i < ind.length && tasks[ind[i]][0] <= time) {
                pq.add(ind[i]);
                i++;
            }
            int top = pq.remove();
            //Update the current time
            time = time+tasks[top][1];
            ans[index++] = top;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{1,2},{2,4},{3,2},{4,1}})));

        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{7,10},{7,12},{7,5},{7,4},{7,2}})));

        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{16,9},{37,4},{45,5},{33,6},{21,10}})));

        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{19,20},{34,12},{16,14},{38,7},{43,2}})));

        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{19,13},{16,9},{21,10},{32,25},{37,4},{49,24},{2,15},{38,41},{37,34},{33,6},{45,4},{18,18},{46,39},{12,24}})));

        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{19,13},{16,9},{21,10},{32,25},{37,4}})));



    }
}

class Solution22 {
    public int[] getOrder(int[][] tasks) {

        Task[] input = new Task[tasks.length];
        for(int i = 0 ; i<tasks.length;i++)
        {
            Task t = new Task(i,tasks[i][0],tasks[i][1]);
            input[i]=t;
        }
        Comparator<Task> myComp1 = new Comparator<Task>(){
            public int compare(Task a, Task b)
            {
                if(a.startTime<b.startTime)return -1;
                else if(a.startTime>b.startTime)return 1;
                else return a.index-b.index;
            }
        };
        Arrays.sort(input,myComp1);
        Comparator<Task> myComp2 = new Comparator<Task>(){
            public int compare(Task a, Task b)
            {
                if(a.duration==b.duration)
                    return a.index-b.index;
                return a.duration-b.duration;
            }
        };
        for(int i=0;i<tasks.length;i++){
            System.out.print(input[i]+" ");
        }

        PriorityQueue<Task> pq = new PriorityQueue<>(myComp2);
        int len = tasks.length;
        int currIdx = 0;
        int trg = 0;
        int processTime = 0;
        int[] results = new int[len];
        while(currIdx<len)
        {
            System.out.println("Processing for time "+processTime);
            if(pq.isEmpty() && processTime< input[currIdx].startTime)
                processTime = input[currIdx].startTime;
            while(currIdx<len && input[currIdx].startTime<=processTime)
            {
                System.out.println("Adding "+input[currIdx].toString()+" to queue");
                pq.offer(input[currIdx++]);
            }
            Task t = pq.poll();
            if(t!=null)
            {
                results[trg]=t.index;
                processTime+=t.duration;
                trg++;
            }
        }
        while(!pq.isEmpty())
        {
            Task t = pq.poll();
            results[trg]=t.index;
            processTime+=t.duration;
            trg++;
        }
        return results;
    }

    public class Task {
        int index;
        int startTime;
        int duration;
        Task(int index,int startTime,int duration)
        {
            this.index=index;
            this.startTime = startTime;
            this.duration = duration;
        }

        public String toString(){
            return "["+startTime+" "+duration+" "+index+"]";
        }
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{1,2},{2,4},{3,2},{4,1}})));
//
//        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{7,10},{7,12},{7,5},{7,4},{7,2}})));
//
//        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{16,9},{37,4},{45,5},{33,6},{21,10}})));

//        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{19,20},{34,12},{16,14},{38,7},{43,2}})));

//        System.out.println(Arrays.toString(new SingleThreadedCPU().getOrder(new int[][]{{19,13},{16,9},{21,10},{32,25},{37,4},{49,24},{2,15},{38,41},{37,34},{33,6},{45,4},{18,18},{46,39},{12,24}})));

        System.out.println(Arrays.toString(new Solution22().getOrder(new int[][]{{19,13},{16,9},{21,10},{32,25},{37,4}})));




}
}
