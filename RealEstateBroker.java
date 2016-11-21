/*
 * PROBLEM : https://www.hackerrank.com/contests/womens-codesprint-2/challenges/real-estate-broker
 */

package realestatebroker;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author prudvi.maddala@oracle.com (In italics)
 */
public class RealEstateBroker {

    /**
     * @param args the command line arguments
     */
    static int n,m,a[],p[],x[],y[];
    static int graph[][];
    static int source,sink,maxFlow;
    static boolean augmentedPath;
    static void runBFS(){
        LinkedList<Integer> q = new LinkedList();
        boolean visited[] = new boolean[n+m+2];
        int parent[] = new int[n+m+2];
        q.addLast(0);
        int bottleNeck = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            int u = q.removeFirst();
            visited[u] = true;
            int start = 0,end = n+m+1;
            if(u==0){
                end = n;
            }
            else if(u==sink){
                start = n+1;
            }
            else if(u<=n){
                start = n+1;
            }
            else{
                end = n;
            }
            for(int i=start;i<=end;i++){
                if(!visited[i]&&graph[u][i]>0){
                    q.addLast(i);
                    visited[i] = true;
                    bottleNeck = Math.min(bottleNeck, graph[u][i]);
                    parent[i] = u;
                    if(i==sink){
                        break;
                    }
                }
            }
            if(u>n){
                int i = sink;
                if(!visited[i]&&graph[u][i]>0){
                    q.addLast(i);
                    visited[i] = true;
                    bottleNeck = Math.min(bottleNeck, graph[u][i]);
                    parent[i] = u;
                }
            }
            if(visited[sink]){
                break;
            }
        }
        if(visited[sink]){
            int temp = sink;
            while(temp!=source){
                int par = parent[temp];
                graph[par][temp]-=bottleNeck;
                graph[temp][par]+=bottleNeck;
                temp = par;                
            }
            maxFlow+=bottleNeck;
        }
        else{
            augmentedPath = false;
        }
    }
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);
        n = ir.readInt();
        m = ir.readInt();
        source = 0;
        sink = n+m+1;
        a = new int[n+1];
        p = new int[n+1];
        for(int i=1;i<=n;i++){
            a[i] = ir.readInt();
            p[i] = ir.readInt();
        }
        x = new int[m+1];
        y = new int[m+1];
        for(int i=1;i<=m;i++){
            x[i] = ir.readInt();
            y[i] = ir.readInt();
        }
        graph = new int[n+m+2][n+m+2];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(x[j]>=a[i]&&y[j]<=p[i]){
                    graph[i][n+j] = 1;
                }
            }
        }
        //Connect source
        for(int i=1;i<=n;i++){
            graph[0][i] = 1;
        }
        //Connect to sink
        for(int i=1;i<=m;i++){
            graph[n+i][n+m+1] = 1;
        }
        maxFlow = 0;
        augmentedPath = true;
        while(augmentedPath){
            runBFS();
        }
        pw.println(maxFlow);
        pw.flush();
        pw.close();
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
