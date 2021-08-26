package src;

import java.util.*;

class Point{
    int x,y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "("+x+","+y+")";
    }
}
public class GrahamsScanConvexHull {

    static double angle(Point a, Point b){
        if(a.x==b.x&&a.y==b.y){
            return 0d;
        }
        double slope = (b.y-a.y+0d)/(b.x-a.x+0d);
        if(slope==0&&b.x<a.x){
            return 180d;
        }
        double ang =  Math.atan(slope)*180/Math.PI;
        if(ang<0){
            return ang+180;
        }
        return ang;
    }

    static double dist2(Point a, Point b){
        return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
    }

    static Point top2(Stack<Point> stack){
        Point a = stack.pop();
        Point ans = stack.peek();
        stack.push(a);
        return ans;
    }

    void runGrahamsScan(List<Point> points){
        Point bottomLeft = points.get(0);
        for(Point point : points){
            bottomLeft = (point.y<bottomLeft.y||(point.y==bottomLeft.y&&point.x<bottomLeft.x))?point:bottomLeft;
        }
        final Point bl = bottomLeft;
        Collections.sort(points, (o1, o2) -> {
            double angle1 = angle(bl,o1);
            double angle2 = angle(bl,o2);
            if(angle1!=angle2){
                return Double.compare(angle1,angle2);
            }
            return Double.compare(dist2(bl,o1),dist2(bl,o2));
        });
        System.out.println("Sorted Points : "+points);
        Stack<Point> stack = new Stack();
        stack.push(points.get(0));
        stack.push(points.get(1));
        for(int i=2;i<points.size();i++){
            Point top = stack.peek();
            Point top2 = top2(stack);
            Point point = points.get(i);
            System.out.println("Point is "+point+" , "+top2+"->"+top+" : "+angle(top2,top)+",  "+top+"->"+point+" : "+angle(top,point));
            if(angle(top2,top)<=angle(top,point)){
                System.out.println("Left Turn");
                stack.push(point);
            }
            else{
                System.out.println("Right Turn");
                while (angle(top2,top)>angle(top,point)){
                    stack.pop();
                    top = stack.peek();
                    top2 = top2(stack);
                }
                stack.push(point);
            }
        }
        System.out.println("Convex Hull is : "+stack);
    }


    static void test1(){
        Point points[] = {new Point(0,0),new Point(1,0),new Point(1,1),new Point(2,1),new Point(0,5), new Point(0,3)};
        new GrahamsScanConvexHull().runGrahamsScan(new ArrayList<Point>(Arrays.asList(points)));
    }
    public static void main(String[] args) {
        test1();
    }
}
