package src;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author pmaddala
 */
public class AkashAndGcd {

    /**
     * @param args the command line arguments
     */
    
    
    static int PHI[];
    static long F[];
    static final long MOD = 1000000000+7;
    //Function to calculate euler's totient function
    public static int phi(int n){
        int t = n;
        if(PHI[n]!=0){
            return PHI[n];
        }
        long ans = n;
        if(n%2==0){
            ans = ans/2;
            while(n%2==0){
                n/=2;
            }
        }
        int e = (int)Math.sqrt(n);
        for(int i=3;i<=e&&n>1;i+=2){
            if(n%i==0){
                ans = (1l * ans * (i-1l)) / i;
                while(n%i==0){
                    n/=i;
                }
            }
        }
        if(n>1){
            ans = (1l * ans * (n-1l)) / n;
        }
        PHI[t] = (int)ans;
        return (int)ans;
    }
    
    //Function to calculate /Sigma(gcd(X,i) i from 1 to X
    public static long f(int x){
        int t = x;
        if(F[x]!=0){
            return F[x];
        }
        long ans = 0;
        int e = (int) Math.sqrt(x);
        for(int i=1;i<=e;i++){
            if(x%i==0){
                ans = (ans + (1l*i*phi(x/i))%MOD)%MOD;
                if(i!=x/i){
                    ans = (ans + (1l*(x/i) * phi(i))%MOD)%MOD;
                }
            }
        }
        F[t] = ans;
        return ans;
    }
    
    static void init(){
        PHI = new int[5*1000000 + 1];
        F = new long[5*1000000 + 1];
    }
    
    //Fenwick trees 
    static void updateFenwickTree(long tree[],int index, long update){
        int x = index;
        while(x<tree.length){
            tree[x] = (tree[x]+update)%MOD;
            x = x+ (x&-x);
        }
    }    
    static long getSumFenwickTree(long tree[], int index){
        long ans = 0;
        int x = index;
        
        while(x>=1){
            ans = (ans + tree[x])%MOD;
            x = x - (x&-x);
        }
        return ans;
    }
    
    static int A[];
    public static void main(String[] args) throws IOException {
        
        init();
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);
        int N = ir.readInt();
        A = new int[N+1];
        long fenwick[] = new long[N+1];
        for(int i=1;i<=N;i++){
            A[i] = ir.readInt();
            updateFenwickTree(fenwick, i, f(A[i]));
        }
        int Q = ir.readInt();
        while(Q-->0){
            if(ir.read().charAt(0)=='C'){
                int L = ir.readInt();
                int R = ir.readInt();
                pw.println((MOD+getSumFenwickTree(fenwick, R)-getSumFenwickTree(fenwick, L-1))%MOD);
            }
            else{
                int pos = ir.readInt();
                int val = ir.readInt();
                long newF = f(val);
                long oldF = (MOD+getSumFenwickTree(fenwick, pos)-getSumFenwickTree(fenwick, pos-1))%MOD;
                long del = (MOD+newF - oldF)%MOD;
//                A[pos] = val;
                updateFenwickTree(fenwick, pos, del);
            }
        }
        pw.flush();
        pw.close();
    }
    final static class InputReader {
        //This IO class belongs to Ashok Rajpurohit (@ashok1113)
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
