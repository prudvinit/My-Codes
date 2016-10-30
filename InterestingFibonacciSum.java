/*
 * PROBLEM : https://www.hackerrank.com/contests/walmart-codesprint-algo/challenges/fibonacci-sum-1
 */

package interestingfibonaccisum;

import java.io.*;

/**
 *
 * @author prudvi.maddala@oracle.com
 */
public class InterestingFibonacciSum{

    /**
     * @param args the command line arguments
     * cute code written by prudvi.maddala@oracle.com
     */
    
    static final long mod = 1000000007;
    
    //2  methods copied from http://codereview.stackexchange.com/questions/51864/calculate-fibonacci-in-olog-n
    public static long fib(long n) {
        if (n <= 1) return n;

        long[][] result = {{1, 0}, {0, 1}}; // identity matrix.
        long[][] fiboM = {{1, 1}, {1, 0}};

        while (n > 0) {
            if (n%2 == 1) {
                multMatrix(result, fiboM);
            }
            n = n / 2;
            multMatrix(fiboM, fiboM);
        }

        return result[1][0];
    }

    private static void multMatrix(long[][] m, long [][] n) {
        long a = ((m[0][0] * n[0][0])%mod +  (m[0][1] * n[1][0])%mod)%mod;
        long b = ((m[0][0] * n[0][1])%mod +  (m[0][1] * n[1][1])%mod)%mod;
        long c = ((m[1][0] * n[0][0])%mod +  (m[1][1] * n[0][1])%mod)%mod;
        long d = ((m[1][0] * n[0][1])%mod +  (m[1][1] * n[1][1])%mod)%mod;
        m[0][0] = a;
        m[0][1] = b;
        m[1][0] = c;
        m[1][1] = d;
    }
        
    static long fastFib(long n){
        if(n>=0){
            return fib(n);
        }
        n = -n;
        long ans = fib(n);
        if(n%2==0){
            ans = -ans;
        }
        return ans;
    }
        
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);
        int q = ir.readInt(),n,a[];
        long ans = 0;
        long pref[],F,F1,PREF,t,t1,ffib,ffibM1,ffibP1;
        while(q-->0){
            n = ir.readInt();
            a = ir.readIntArray1(n);
            ans = 0;
            PREF = 0;
            F = 0;
            F1 = 0;
            for(int i=1;i<=n;i++){
                PREF += a[i];
                F = (F+fastFib(PREF))%mod;
                F1 = (F1+fastFib(PREF-1))%mod;
                
            }
            ans = F;
            for(int i=1;i<=n;i++){
                ffib = fastFib(-a[i]);
                ffibM1 = fastFib(-(a[i]+1));
                ffibP1 = ffib+ffibM1;
                t = ((F1*ffib*1l)%mod + (1l*F*ffibP1)%mod)%mod;
                while(t<0){
                    t = t+mod;
                }
                t1 = ((F1*ffibM1)%mod + (F*ffib)%mod + mod - 1)%mod;
                while(t1<0){
                    t1 = t1+mod;
                }
                F = t;
                F1 = t1;
                ans = (ans + t)%mod;
            }
            pw.println(ans);
        }
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
        public long[] readLongArray1(int n) throws IOException {
            long[] ar = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                ar[i] = readLong();
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
