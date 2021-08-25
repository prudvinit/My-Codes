/*
 * PROBLEM : http://codeforces.com/contest/740/problem/D
 */

package src;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author prudvi.maddala@oracle.com
 */
public class AlyonaAndTree {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Edge> tree[];
    static class Edge{
        int to;
        long weight;
        Edge(int to, long weight){
            this.to = to;
            this.weight = weight;
        }
    }
    static ArrayList<Long> bin;
    static ArrayList<Integer> path;
    
    static void runDFS(int v, long w){
        bin.add(bin.isEmpty()?w:w+bin.get(bin.size()-1));
        path.add(v);
        for(Edge e : tree[v]){
            runDFS(e.to,e.weight);
        }
        if(bin.size()>0){
            long key = bin.get(bin.size()-1)-a[v];
            int ind = Collections.binarySearch(bin, key);
            if(ind<0){
                ind = -ind-1;
            }
            int from = v;
            ans[parent[from]] +=1;
            int to = path.get(ind);
            ans[parent[to]]-=1;
            bin.remove(bin.size()-1);
            path.remove(path.size()-1);
        }
        
    }
    static void runDFSAgainAndFindAns(int v){
        for(Edge e : tree[v]){
            runDFSAgainAndFindAns(e.to);
            ans[v] = ans[v] + ans[e.to];
        }
    }
    static int a[],ans[],parent[];
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        int n = ir.readInt();
        tree = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            tree[i] = new ArrayList();
        }
        a = ir.readIntArray1(n);
        parent = new int[n+1];
        for(int i=2;i<=n;i++){
            int par = ir.readInt();
            parent[i] = par;
            int w = ir.readInt();
            tree[par].add(new Edge(i,w));
        }
        bin = new ArrayList();
        bin.add(1l);
        
        path = new ArrayList();
        path.add(1);
        ans = new int[n+1];
        runDFS(1,0);
        runDFSAgainAndFindAns(1);
        for(int i=1;i<=n;i++){
            System.out.print(ans[i]+" ");
        }
        System.out.println("");
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
