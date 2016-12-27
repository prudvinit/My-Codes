//PROBLEM : https://www.codechef.com/problems/COPRIME3


import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    static int D[],mew[];
    static void init(int A[]){
        int count[] = new int[MAX+1];
        D = new int[MAX+1];
        for(int i=1;i<A.length;i++){
            count[A[i]]++;
        }
        for(int i=1;i<=MAX;i++){
            for(int j=1;j*i<=MAX;j++){
                D[i] = D[i] + count[j*i];
            }
        }
        int mob[] = new int[MAX+1];
        int p[] = new int[MAX+1];
        p[1] = 0;
        
        for(int i=2;i<=MAX;i++){            
            if(p[i]==0){
                for(int j=1;j*i<=MAX;j++){
                    int c = 0;
                    int t = i*j;
                    while(t%i==0){
                        c++;
                        t/=i;
                    }
                    p[i*j] += c;
                }
                p[i] = 1;
            }
        }
        mew = new int[MAX+1];
        for(int i=1;i<=MAX;i++){
            if(p[i]%2==0){
                mew[i] = 1;
            }
            else{
                mew[i] = -1;
            }
        }
        for(int i=2;i*i<=MAX;i++){
            for(int j=1;j*i*i<=MAX;j++){
                mew[j*i*i] = 0;
            }
        }
        
    }
    
    static long nc3(int n){
        if(n<3){
            return 0;
        }
        long ans = 1;
        ans = ans*n;
        ans = ans*(n-1);
        ans = ans*(n-2);
        ans = ans/6;
        return ans;
    }
    private static final int MAX = 200000;
	public static void main (String[] args) throws java.lang.Exception
	{
	    InputReader ir = new InputReader();
	    int N = ir.readInt();
	    int A[] = new int[N+1];
	    for(int i=1;i<=N;i++){
	        A[i] = ir.readInt();
	    }
	    Arrays.sort(A);
	    init(A);
	   
	    long ans = 0;
	    for(int i = 1; i<= MAX;i++){
	        ans = ans + 1l*mew[i]*nc3(D[i]);
	    }
	    System.out.println(ans);
	}
	 static class InputReader {
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
            if (bufferSize == -1)
                throw new IOException("No new bytes");
            for (; buffer[offset] < 0x30 || buffer[offset] == '-'; ++offset) {
                if (buffer[offset] == '-')
                    s = -1;
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
            for (int i = 0; i < n; i++)
                ar[i] = readInt();
 
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
                if (buffer[offset] == '-')
                    s = -1;
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
            if (s == -1)
                res = -res;
            return res;
        }
 
        public long[] readLongArray(int n) throws IOException {
            long[] ar = new long[n];
 
            for (int i = 0; i < n; i++)
                ar[i] = readLong();
 
            return ar;
        }
 
        public String read() throws IOException {
            StringBuilder sb = new StringBuilder();
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }
 
            if (bufferSize == -1 || bufferSize == 0)
                throw new IOException("No new bytes");
 
            for (;
                 buffer[offset] == ' ' || buffer[offset] == '\t' || buffer[offset] ==
                 '\n' || buffer[offset] == '\r'; ++offset) {
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (; offset < bufferSize; ++offset) {
                if (buffer[offset] == ' ' || buffer[offset] == '\t' ||
                    buffer[offset] == '\n' || buffer[offset] == '\r')
                    break;
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
 
            if (bufferSize == -1 || bufferSize == 0)
                throw new IOException("No new bytes");
 
            for (;
                 buffer[offset] == ' ' || buffer[offset] == '\t' || buffer[offset] ==
                 '\n' || buffer[offset] == '\r'; ++offset) {
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (int i = 0; offset < bufferSize && i < n; ++offset) {
                if (buffer[offset] == ' ' || buffer[offset] == '\t' ||
                    buffer[offset] == '\n' || buffer[offset] == '\r')
                    break;
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
