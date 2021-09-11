package src.leetcode;

import java.util.*;

public class SkylineProblem {

    static class Point{
        int height,pos;
        int start, end;
        boolean isStart;
        Point(int pos, int height, int start, int end){
            this.pos = pos;
            this.height = height;
            this.start = start;
            this.end = end;
            isStart = pos==start;
        }

        @Override
        public String toString(){
            return "("+pos+","+height+","+isStart+")";
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Point> points = new ArrayList();
        for(int[] building : buildings){
            points.add(new Point(building[0],building[2],building[0],building[1]));
            points.add(new Point(building[1],building[2],building[0],building[1]));
        }
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //If there are overlapping x coordinates
                if(o1.pos==o2.pos){
                    if(o1.isStart==o2.isStart){
                        //Both are start of building, then select building with higher height
                        if(o1.isStart){
                            return o2.height-o1.height;
                        }
                        //If both are end of building, select the building with lower height
                        else{
                            return o1.height-o2.height;
                        }
                    }
                    //Starting build always takes precedence
                    else{
                        if(o1.isStart){
                            return -1;
                        }
                        return 1;
                    }
                }
                //Building with lower x takes precedence
                return o1.pos-o2.pos;
            }
        });
        System.out.println("Sorted "+points);
        //Create a new max priority queue to store heights
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        pq.add(0);
        int prev = 0;
        ArrayList<Point> ans = new ArrayList();
        for(Point p : points){
            //Check if start of new building is found
            if(p.isStart) {
                //Add this building height to PQ
                pq.add(p.height);
                //Add this building to answer, if adding this building changed the max
                if (pq.peek() > prev) {
                    ans.add(p);
                }
            }
            else{
                //Remove this building from PQ
                pq.remove((Integer)p.height);
                //If removing this height results in max height change, add it to answer
                if(pq.peek()<prev){
                    ans.add(new Point(p.pos,pq.peek(),0,0));
                }
            }
            prev = pq.peek();
        }
        //Convert answer to array list
        ArrayList<List<Integer>> fans = new ArrayList();
        for(Point p : ans){
            fans.add(Arrays.asList(p.pos,p.height));
        }
        return fans;
    }

    static void test1(){
        int arr[][] = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        System.out.println(new SkylineProblem().getSkyline(arr));
    }

    static void test2(){
        int arr[][] = {{0,2,3},{2,5,3}};
        System.out.println(new SkylineProblem().getSkyline(arr));
    }

    static void test3(){
        int arr[][] = {{0,5,7},{5,10,7},{5,10,12},{10,15,7},{15,20,7},{15,20,12},{20,25,7}};
        System.out.println(new SkylineProblem().getSkyline(arr));
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }
}
