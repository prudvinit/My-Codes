import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
 
/**
 *
 * @author prudvi.maddala@oracle.com
 * LINK : http://www.spoj.com/problems/QTREE2/
 */
 
public class QTree2 {
 
    /**
     * @param args the command line arguments
     */
    static int n,graph[][];
    static boolean visited[];
    static int euler[],e;
    static int depth[],ind[],parent[],dist[];
    static void takeEulerTour(int u){       
        visited[u] = true;
        euler[++e] = u;
        if(ind[u]==0){
            ind[u] = e;
        }
        for(int i=1;i<=n;i++){
            if(graph[u][i]!=0&&!visited[i]){
                depth[i] = depth[u]+1;
                parent[i] = u;
                dist[i] = dist[u] + graph[u][i];
                takeEulerTour(i);
                euler[++e] = u;
            }
        }
    }
    
    static int sparse[][];
    
    static int log(int n){
        int ans = 0;
        while(n>1){
            n = n>>1;
            ans++;
        }
        return ans;
    }
    
    static int pow(int n){
        int ans = 1;
        while(n-->0){
            ans = ans<<1;
        }
        return ans;
    }
    
    
    
    static void buildSparseTable(){
        int s = euler.length;
        int c = log(s)+1;
        sparse = new int[s][c];
        for(int i=0;i<s;i++){
            sparse[i][0] = i;
        }
        for(int j=1;j<c;j++){
            for(int i=0; i + pow(j) -1 <n;i++){
                sparse[i][j] = depth[sparse[i][j-1]] <= depth[sparse[i+pow(j)-pow(j-1)][j-1]] ? sparse[i][j-1] : sparse[i+pow(j-1)][j-1];         
            }
        }
    }
    static int getLCA(int a, int b){
        a = ind[a];
        b = ind[b];
        if(a>b){
            int t = a;
            a = b;
            b = t;
        }
        int range = (b-a+1);
        int ln = log(range);
        int ans =  depth[sparse[a][ln]]<=depth[sparse[b-pow(ln)+1][ln]]?sparse[a][ln]:sparse[b-pow(ln)+1][ln];
        return euler[ans];
    }
    static int dist(int a, int b){
        return dist[a]+dist[b]-2*dist[getLCA(a,b)];
    }    
    static int kth(int a, int b, int k){
        int w = getLCA(a,b);
        int dist = depth[a]-depth[w];
        if(k==dist){
            return w;
        }
        int ans = w;
        if(k==1){
            return a;
        }
        if(k<dist){
            ans = a;
            int temp = 0;
            while(temp!=k){
                ans = parent[ans];
                temp++;
            }
        }
        if(k>dist){
            ans = b;
            int temp = 0;
            k = dist(a,b)-k;
            while(temp!=k){
                ans = parent[ans];
                temp++;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputReader ir = new InputReader();
        int t = ir.readInt(),a,b,c,k;
        while(t-->0){
            n = ir.readInt();
            graph = new int[n+1][n+1];
            for(int i=0;i<n-1;i++){
                a = ir.readInt();
                b = ir.readInt();
                c = ir.readInt();
                graph[a][b] = c;
                graph[b][a] = c;
            }
            visited = new boolean[n+1];
            euler = new int[2*n];
            depth = new int[2*n];
            ind = new int[n+1];
            parent = new int[n+1];
            dist = new int[n+1];
            e = 0;
            depth[1] = 0;
            takeEulerTour(1);
            buildSparseTable();
            String s = ir.read();
            while(!s.equals("DONE")){
                if(s.equals("DIST")){
                    a = ir.readInt();
                    b = ir.readInt();
                    System.out.println(dist(a,b));
                }
                if(s.equals("KTH")){
                    a = ir.readInt();
                    b = ir.readInt();
                    k = ir.readInt();
                    System.out.println(kth(a,b,k));
                }
                s = ir.read();
            }
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
