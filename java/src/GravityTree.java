package src;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author prudvi.maddala@oracle.com
 * problem link https://www.hackerrank.com/contests/w23/challenges/gravity-1
 */
public class GravityTree {

    static ArrayList<Integer> tree[];
    static int n,in[],out[],t=0,T[],ind = 1;
    
    static void runDFS(int u,int dist){
        T[ind++] = dist;
        in[u] = ++t;
        for(int x : tree[u]){
            runDFS(x,dist+1);
        }
        out[u] = t;
    }
    
    static long segment2Tree[],segmentTree[],lazy[];
    
    static void buildSegmentTree(int start, int end, int current){
        if(start==end){
            segmentTree[current] = T[start];
            segment2Tree[current] = T[start]*T[start];
            return;
        }
        int mid = (start+end)>>1;
        buildSegmentTree(start,mid,2*current+1);
        buildSegmentTree(mid+1,end,2*current+2);
        segmentTree[current] = segmentTree[2*current+1] + segmentTree[2*current+2];
        segment2Tree[current] = segment2Tree[2*current+1] + segment2Tree[2*current+2];
    }
    
    static void updateTree(int uStart, int uEnd, int update, int start, int end, int current){
        
        if(lazy[current]!=0){
            segment2Tree[current] += (2*lazy[current]*segmentTree[current] + (end-start+1)*lazy[current]*lazy[current]);
            segmentTree[current] += (end-start+1)*lazy[current];
            if(start!=end){
                lazy[2*current+1] += lazy[current];
                lazy[2*current+2] += lazy[current];
            }
            lazy[current] = 0;
        }
        if(uStart>end||uEnd<start){
            return;
        }
        if(uStart<=start&&uEnd>=end){
            segment2Tree[current] += (2*update*segmentTree[current] + (end-start+1)*update*update);
            segmentTree[current] += (end-start+1)*update;
            if(start!=end){
                lazy[2*current+1] += update;
                lazy[2*current+2] += update;
            }
            return;
        }
        int mid = (start+end)>>1;
        updateTree(uStart,uEnd,update,start,mid,2*current+1);
        updateTree(uStart,uEnd,update,mid+1,end,2*current+2);
        segmentTree[current] = segmentTree[2*current+1] + segmentTree[2*current+2];
        segment2Tree[current] = segment2Tree[2*current+1] + segment2Tree[2*current+2];
        
    }
    
    static long getSum(int qStart, int qEnd, int start, int end, int current){
        
        if(lazy[current]!=0){
            segment2Tree[current] += (2*lazy[current]*segmentTree[current] + (end-start+1)*lazy[current]*lazy[current]);
            segmentTree[current] += ((end-start+1)*lazy[current]);
            if(start!=end){
                lazy[2*current+1] += lazy[current];
                lazy[2*current+2] += lazy[current];
            }
            lazy[current] = 0;
        }
        if(qStart>end||qEnd<start){
            return 0;
        }
        if(qStart<=start&&qEnd>=end){
            return segment2Tree[current];
        }
        
        int mid = (start+end)>>1;
        long a = getSum(qStart,qEnd,start,mid,2*current+1);
        long b = getSum(qStart,qEnd,mid+1,end,2*current+2);
        return a+b;
    }    
    
    static long ans[];
    static ArrayList<Q> queries[];
    
    static void runDFSAndFindTheAnswerDude(int v){
        if(queries[v]!=null){
            for(Q q : queries[v]){
                int ind = q.ind;
                int s = q.v;
                long sum = getSum(in[s],out[s],1,n,0);
                ans[ind] = sum;
            }
        }
        for(int x : tree[v]){
            updateTree(in[1],out[1],1,1,n,0);
            updateTree(in[x],out[x],-2,1,n,0);
            runDFSAndFindTheAnswerDude(x);
            updateTree(in[1],out[1],-1,1,n,0);
            updateTree(in[x],out[x],2,1,n,0);
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputReader ir = new InputReader();
        n = ir.readInt();
        tree = new ArrayList[n+1];
        in = new int[n+1];
        out = new int[n+1];
        for(int i=0;i<=n;i++){
            tree[i] = new ArrayList();
        }
        for(int i=2;i<=n;i++){
            tree[ir.readInt()].add(i);
        }
        T = new int[n+1];
        runDFS(1,0);        
        segmentTree = new long[(int)Math.pow(2,Math.ceil(Math.log(n)/Math.log(2))+1)-1];
        segment2Tree = new long[(int)Math.pow(2,Math.ceil(Math.log(n)/Math.log(2))+1)-1];
        lazy = new long[(int)Math.pow(2,Math.ceil(Math.log(n)/Math.log(2))+1)-1];
        
        int q = ir.readInt();
        
        queries = new ArrayList[n+1];
        ans = new long[q];
        for(int i=0;i<q;i++){
            int U = ir.readInt(), V = ir.readInt();
            if(queries[U]==null){
                queries[U] = new ArrayList();
            }
            queries[U].add(new Q(i,V));            
        }        
        buildSegmentTree(1,n,0);
        runDFSAndFindTheAnswerDude(1);
        for(int i=0;i<q;i++){
            System.out.println(ans[i]);
        }
    }

    static class Q{
        int ind,v;
        Q(int ind, int v){
            this.ind = ind;
            this.v = v;
        }
        @Override
        public String toString(){
            return "ind "+ind+" switch "+v;
        }
    }
    
    final static class InputReader {

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
