package src;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
 
/**
 *
 * @author prudvi.maddala@oracle.com
 * PROBLEM : https://www.codechef.com/NOV16/problems/URBANDEV/
 */
public class UrbanDevelopment{
 
    /**
     * @param args the command line arguments
     */
    
    static class Point{
        int ind, x,y,y2=-1;
        boolean h,v;
        boolean top,bottom;
        boolean start,end;
        public Point(int ind, int x, int y){
            this.ind = ind;
            this.x = x;
            this.y = y;
        }
        public Point(int ind, int x, int y,int y2){
            this.ind = ind;
            this.x = x;
            this.y = y;
            this.y2 = y2;
        }
        @Override
        public String toString(){
            return "("+ind+" "+x+" "+y+" "+(y2!=-1?y2+"":"")+")";
        }
        public void horizontal(){
            this.h = true;
            this.v = false;
        }
        public void vertical(){
            this.v = true;
            this.h = false;
        }
        public void setStart(){
            start = true;
            end = false;
        }
        public void setEnd(){
            start = false;
            end = true;
        }
        public void setTop(){
            top = true;
            bottom = false;
        }
        public void setBottom(){
            top = false;
            bottom = true;
        }
    }
    
    static class Node{
        int count;
        Node left, right;
    }
    
    static final int BITS = 20;
    
    static Node root;
    
    static void insert(int x){
        Node temp = root;
        for(int i=0;i<BITS;i++){
            if((x&(1<<BITS-i-1))==0){
                if(temp.left==null){
                    temp.left = new Node();
                }
                temp.left.count++;
                temp = temp.left;
                
            }
            else{
                if(temp.right==null){
                    temp.right = new Node();
                }
                temp.right.count++;
                temp = temp.right;
                
            }
        }
    }
    
    static boolean present(int x){
        Node temp = root;
        int c = Integer.MAX_VALUE;
        for(int i=0;i<BITS;i++){
            if((x&(1<<BITS-i-1))==0){
                c = Math.min(c,temp.left.count);
                temp = temp.left;
            }
            else{
                c = Math.min(c,temp.right.count);
                temp = temp.right;
            }
        }
        return c>=1;
    }
    
    static void remove(int x){
        Node temp = root;
        for(int i=0;i<BITS;i++){
            if((x&(1<<BITS-i-1))==0){
                temp.left.count--;
                temp = temp.left;
                
            }
            else{
                temp.right.count--;
                temp = temp.right;
                
            }
        }
    }
    
    static void insertDummy(int x){
        Node temp = root;
        for(int i=0;i<BITS;i++){
            if((x&(1<<BITS-i-1))==0){
                if(temp.left==null){
                    temp.left = new Node();
                }
                temp = temp.left;
                
            }
            else{
                if(temp.right==null){
                    temp.right = new Node();
                }
                temp = temp.right;
                
            }
        }
    }
    
    static int count(int x){
        Node temp = root;
        int ans = 0;
        for(int i=0;i<BITS;i++){
            if((x&(1<<BITS-i-1))==0){
                ans = ans + (temp.right!=null?temp.right.count:0);
                temp = temp.left;
                
            }
            else{
                temp = temp.right;
            }
        }
        return ans;
    }
    
    static int countRange(int r, int s){
        insertDummy(r);
        int ans1 = count(r);
        if(present(r)){
            ans1++;
        }
        insertDummy(s);
        int ans2 = count(s);
        if(present(s)){
            ans2++;
        }
        int fans = ans1-ans2+1;
        if(!present(s)){
            fans--;
        }
        return fans;
    }
    
    static int comp(Point p, Point q){
        if(p.h&&q.h){
            if(p.x==q.x){
                if(p.start&&q.start){
                    return q.y-p.y;
                }
                if(p.end&&q.end){
                    return q.y-p.y;
                }
                if(p.start){
                    return -1;
                }
                if(q.start){
                    return 1;
                }
                return q.y-p.y;
            }
            return p.x-q.x;
        }
        if(p.v){
            if(q.v){
                if(p.x==q.x){
                    return q.y-p.y;
                }
                return p.x-q.x;
            }
            if(p.x==q.x){                
                if(q.start){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            return p.x-q.x;
        }
        if(q.v){
            if(p.x==q.x){
                if(p.start){
                    return -1;
                }
                else{
                    return 1;
                }
            }
            
            return p.x-q.x;
        }
        return p.x-p.x;
    }
    
    static HashMap<String,Integer> hm,hm2;
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);
        int N = ir.readInt(),x1,y1,x2,y2;
        ArrayList<Point> p = new ArrayList();
        ArrayList<Point> q = new ArrayList();
        int ans[] = new int[N];
        int ind = 0;
        hm = new HashMap();
        hm2 = new HashMap();
        for(int i=0;i<N;i++){
            x1 = ir.readInt();
            y1 = ir.readInt();
            x2 = ir.readInt();
            y2 = ir.readInt();
            if(x1==x2){
                Point t = new Point(i,x1,Math.max(y1,y2),Math.min(y1, y2));
                t.vertical();
                p.add(t);
            }
            else{
                Point t = new Point(i,Math.min(x1, x2),y1);
                t.horizontal();
                t.setStart();
                hm.put(t.x+" "+t.y,0);
                p.add(t);
                t = new Point(i,Math.max(x1, x2),y2);
                t.horizontal();
                t.setEnd();
                p.add(t);
                hm.put(t.x+" "+t.y,1);
            }
            int tt = x1;
            x1 = y1;
            y1 = tt;
            tt = x2;
            x2 = y2;
            y2 = tt;
            if(x1==x2){
                Point t = new Point(i,x1,Math.max(y1,y2),Math.min(y1, y2));
                t.vertical();
                q.add(t);
            }
            else{
                Point t = new Point(i,Math.min(x1, x2),y1);
                t.horizontal();
                t.setStart();
                hm2.put(t.x+" "+t.y,0);
                q.add(t);
                t = new Point(i,Math.max(x1, x2),y2);
                t.horizontal();
                t.setEnd();
                q.add(t);
                hm2.put(t.x+" "+t.y,0);
            }            
        }
        Collections.sort(p,new Comparator<Point>(){
 
            @Override
            public int compare(Point o1, Point o2) {
                return comp(o1,o2);
            }
            
        });
        root = new Node();
        long cameras = 0;
        int s=0;
        for(int i=0;i<p.size();i++){
            Point temp = p.get(i);
            if(temp.h){
                if(temp.start){
                    insert(temp.y);
                }
                else{
                    remove(temp.y);
                }
            }
            else {
                int fans = countRange(temp.y2,temp.y);
                if(hm.containsKey(temp.x+" "+temp.y)){
                    fans--;
                }
                if(hm.containsKey(temp.x+" "+temp.y2)){
                    fans--;
                }
                ans[temp.ind] = fans;
                cameras = cameras + 1l*ans[temp.ind];
            }
        }
        Collections.sort(q,new Comparator<Point>(){
 
            @Override
            public int compare(Point o1, Point o2) {
                return comp(o1, o2);
            }
            
        });
        root = new Node();
        for(int i=0;i<q.size();i++){
            Point temp = q.get(i);
            if(temp.h){
                if(temp.start){
                    insert(temp.y);
                }
                else{
                    remove(temp.y);
                }
            }
            else{
                int fans = countRange(temp.y2,temp.y);
                if(hm2.containsKey(temp.x+" "+temp.y)){
                    fans--;
                }
                if(hm2.containsKey(temp.x+" "+temp.y2)){
                    fans--;
                }
                ans[temp.ind] = fans;
            }
        }
//        System.out.println(cameras);
        pw.println(cameras);
        for(int i=0;i<N;i++){
//            System.out.print(ans[i]+" ");
            pw.print(ans[i]+" ");
        }
//        System.out.println("");
        pw.println();
        pw.flush();
        pw.close();
    }
    final static class InputReader {
        //IO code, courtesy of ashok rajpurohit @ashok1113
        byte[] buffer = new byte[8192];
        int offset = 0;
        int bufferSize = 0;
        final InputStream in = System.in;
 
        public int readInt() throws IOException {
            int number = 0;
            int s = 1;
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }
            if (bufferSize == -1) {
                throw new IOException("No new bytes");
            }
            for (; buffer[offset] < 0x30 || buffer[offset] == '-'; ++offset) {
                if (buffer[offset] == '-') {
                    s = -1;
                }
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (; offset < bufferSize && buffer[offset] > 0x2f; ++offset) {
                number = (number << 3) + (number << 1) + buffer[offset] - 0x30;
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            ++offset;
            return number * s;
        }
 
        public int[] readIntArray(int n) throws IOException {
            int[] ar = new int[n];
            for (int i = 0; i < n; i++) {
                ar[i] = readInt();
            }
 
            return ar;
        }
 
        public int[] readIntArray1(int n) throws IOException {
            int[] ar = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                ar[i] = readInt();
            }
 
            return ar;
        }
 
        public long readLong() throws IOException {
            long res = 0;
            int s = 1;
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }
            for (; buffer[offset] < 0x30 || buffer[offset] == '-'; ++offset) {
                if (buffer[offset] == '-') {
                    s = -1;
                }
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (; offset < bufferSize && buffer[offset] > 0x2f; ++offset) {
                res = (res << 3) + (res << 1) + buffer[offset] - 0x30;
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            ++offset;
            if (s == -1) {
                res = -res;
            }
            return res;
        }
 
        public long[] readLongArray(int n) throws IOException {
            long[] ar = new long[n];
 
            for (int i = 0; i < n; i++) {
                ar[i] = readLong();
            }
 
            return ar;
        }
 
        public String read() throws IOException {
            StringBuilder sb = new StringBuilder();
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }
 
            if (bufferSize == -1 || bufferSize == 0) {
                throw new IOException("No new bytes");
            }
 
            for (;
                    buffer[offset] == ' ' || buffer[offset] == '\t' || buffer[offset]
                    == '\n' || buffer[offset] == '\r'; ++offset) {
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (; offset < bufferSize; ++offset) {
                if (buffer[offset] == ' ' || buffer[offset] == '\t'
                        || buffer[offset] == '\n' || buffer[offset] == '\r') {
                    break;
                }
                if (Character.isValidCodePoint(buffer[offset])) {
                    sb.appendCodePoint(buffer[offset]);
                }
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            return sb.toString();
        }
 
        public String read(int n) throws IOException {
            StringBuilder sb = new StringBuilder(n);
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }
 
            if (bufferSize == -1 || bufferSize == 0) {
                throw new IOException("No new bytes");
            }
 
            for (;
                    buffer[offset] == ' ' || buffer[offset] == '\t' || buffer[offset]
                    == '\n' || buffer[offset] == '\r'; ++offset) {
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (int i = 0; offset < bufferSize && i < n; ++offset) {
                if (buffer[offset] == ' ' || buffer[offset] == '\t'
                        || buffer[offset] == '\n' || buffer[offset] == '\r') {
                    break;
                }
                if (Character.isValidCodePoint(buffer[offset])) {
                    sb.appendCodePoint(buffer[offset]);
                }
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            return sb.toString();
        }
    }
}  
