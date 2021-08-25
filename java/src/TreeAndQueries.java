/*
 * Written by prudvi.maddala@oracle.com
 * problem : http://codeforces.com/problemset/problem/375/D
 * Application of MOs square decomposition
 */
package src;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author PrudhviRaj
 */
public class TreeAndQueries {

    /**
     * @param args the command line arguments
     */
    
    static int n,m,in[],out[],t=0,c[],C[],ind=0;
    static ArrayList<Integer> tree[];
    
    static void dfs(int u){
        visited[u] = true;
        c[++ind] = C[u];
        in[u] = ++t;
        for(int x : tree[u]){
            if(!visited[x])
                dfs(x);
        }
        out[u] = t;
    }
    
    static boolean visited[];
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputReader ir = new InputReader();
        n = ir.readInt();
        m = ir.readInt();
        C = ir.readIntArray1(n);
        c = new int[n+1];
        
        in = new int[n+1];
        out = new int[n+1];
        tree = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            tree[i] = new ArrayList();
        }
        for(int i=1;i<n;i++){
            int a = ir.readInt();
            int b = ir.readInt();
            tree[a].add(b);
            tree[b].add(a);
        }
        visited = new boolean[n+1];
        dfs(1);
        Q q[] = new Q[m];
        for(int i=0;i<m;i++){
            int u = ir.readInt();
            q[i] = new Q(i,in[u],out[u],ir.readInt());
        }
        final int buckets = (int)Math.sqrt(n);        
        Arrays.sort(q, new Comparator<Q>(){
            @Override
            public int compare(Q o1, Q o2) {
                int b1 = o1.L/buckets;
                int b2 = o2.L/buckets;
                if(b1==b2){
                    return o1.R-o2.R;
                }
                return b1-b2;
            }    
            
        });
        int MAX = 100001;
        int count[] = new int[MAX];
        int ccount[] = new int[MAX];
        int preL = q[0].L,preR = q[0].R;
        preL = 1;
        preR = 1;
        count[c[1]]++;
        ccount[count[c[1]]]++;
        for(int i=0;i<m;i++){
            while(preL<q[i].L){
                if(count[c[preL]]>0){
                    ccount[count[c[preL]]]--;
                }
                count[c[preL]]--;
                preL++;
            }
            while(preL>q[i].L){
                preL--;
                count[c[preL]]++;                
                if(count[c[preL]]>0){
                    ccount[count[c[preL]]]++;
                }
            }
            while(preR<q[i].R){
                preR++;
                count[c[preR]]++;                
                if(count[c[preR]]>0){
                    ccount[count[c[preR]]]++;
                }
            }
            while(preR>q[i].R){
                if(count[c[preR]]>0){
                    ccount[count[c[preR]]]--;
                }
                count[c[preR]]--;
                preR--;
            }
            q[i].ans = ccount[q[i].k];
        }
        int ans[] = new int[m];
        for(int i=0;i<m;i++){
            int ind = q[i].ind;
            int tans = q[i].ans;
            ans[ind] = tans;
        }
        for(int i=0;i<m;i++){
            System.out.println(ans[i]);
        }        
    }
    
    static class Q{
        int ind,L,k,R;
        int ans=0;
        Q(int ind, int L, int R, int k){
            this.ind = ind;
            this.L = L;
            this.k = k;
            this.R = R;            
        }
        @Override
        public String toString(){
            return "{ind "+ind+" Left "+L+" Right "+R+" k "+k+"}";
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
