/*
 * PROBLEM : https://www.hackerrank.com/contests/sears-dots-arrows/challenges/simple-paths
 */

package simplepaths;

import java.io.*;
import java.util.*;

/**
 *
 * @author prudvi.maddala@oracle.com
 */
 
public class SimplePaths{

    /**
     * @param args the command line arguments
     */
    
    static int N,M,C,x[],y[];
    static ArrayList<Edge> graph[];
    
    static class Edge{
        int v,c;
        Edge(int v, int c){
            this.v = v;
            this.c = c;
        }
    }
    
    static int GROUP = -1,group[];
    static void dfs(int u){
        visited[u] = true;
        group[u] = GROUP;
        for(Edge e : graph[u]){
            if(!visited[e.v]){
                dfs(e.v);
            }
        }
    }
    
    static void dfs2(int u, int excluded){
        visited[u] = true;
        group[u] = GROUP;
        for(Edge e : graph[u]){
            if(!visited[e.v]&&e.c!=excluded){
                dfs2(e.v,excluded);
            }
        }
    }
    static boolean visited[];
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        N = ir.readInt();
        M = ir.readInt();
        C = ir.readInt();
        graph = new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList();
        }
        for(int i=0;i<M;i++){
            int u = ir.readInt();
            int v = ir.readInt();
            int c = ir.readInt();
            graph[u].add(new Edge(v,c));
            graph[v].add(new Edge(u,c));
        }
        int Q = ir.readInt();
        x = new int[Q];
        y = new int[Q];
        for(int i=0;i<Q;i++){
            x[i] = ir.readInt();
            y[i] = ir.readInt();
        }
        group = new int[N+1];
        visited = new boolean[N+1];
        for(int i=1;i<=N;i++){
            if(!visited[i]){
                GROUP = i;
                dfs(i);
            }
        }
        int ans[] = new int[Q];
        Arrays.fill(ans, 0);
        for(int i=0;i<Q;i++){
            if(group[x[i]]!=group[y[i]]){
                ans[i] = -1;
            }
        }
        
        for(int i=1;i<=C;i++){
            group = new int[N+1];
            visited = new boolean[N+1];
            for(int j=1;j<=N;j++){
                if(!visited[j]){
                    GROUP = j;
                    dfs2(j,i);
                }
            }
            for(int j=0;j<Q;j++){
                if(ans[j]!=-1&&(group[x[j]]!=group[y[j]])){
                    ans[j]++;
                }
            }
        }
        for(int i=0;i<Q;i++){
            System.out.println(ans[i]==-1?0:ans[i]);
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
