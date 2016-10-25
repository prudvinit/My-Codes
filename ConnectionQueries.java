/*
 * PROBLEM : https://www.hackerrank.com/contests/sears-dots-arrows/challenges/connection-queries
 */

package connectionqueries;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author prudvi.maddala@oracle.com
 */
public class ConnectionQueries {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        int N = ir.readInt();
        int Q = ir.readInt();
        int A[] = ir.readIntArray1(N);
        final int L[] = new int[Q];
        final int R[] = new int[Q];
        Integer ind[] = new Integer[Q];
        for(int i=0;i<Q;i++){
            L[i] = ir.readInt();
            R[i] = ir.readInt();
            ind[i] = i;
        }
        final int buckets = (int)Math.sqrt(N);
        Arrays.sort(ind,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                int b1 = L[o1]/buckets;
                int b2 = L[o2]/buckets;
                if(b1==b2){
                    return R[o1]-R[o2];
                }
                return b1-b2;
            }
        });
        int comps = 0;
        int h[] = new int[N+2];
        int ans[] = new int[Q+1];
        int preL = L[ind[0]];
        int preR = R[ind[0]];
        for(int j=preL;j<=preR;j++){
            h[A[j]] = 1;
            comps++;
            if(h[A[j]-1]==1){
                comps--;
            }
            if(h[A[j]+1]==1){
                comps--;
            }
        }
        ans[ind[0]] = comps;            
        for(int i=1;i<Q;i++){
            while(preL<L[ind[i]]){
                if(h[A[preL]]==1){
                    comps--;
                    if(h[A[preL]-1]==1){
                        comps++;
                    }
                    if(h[A[preL]+1]==1){
                        comps++;
                    }
                }
                h[A[preL]]--;
                preL++;
            }
            while(preL>L[ind[i]]){
                preL--;
                if(h[A[preL]]==0){
                    comps++;
                    if(h[A[preL]-1]==1){
                        comps--;
                    }
                    if(h[A[preL]+1]==1){
                        comps--;
                    }
                }
                h[A[preL]]++;
            }
            while(preR<R[ind[i]]){
                preR++;
                if(h[A[preR]]==0){
                    comps++;
                    if(h[A[preR]-1]==1){
                        comps--;
                    }
                    if(h[A[preR]+1]==1){
                        comps--;
                    }
                }
                h[A[preR]]++;
            }
            while(preR>R[ind[i]]){
                if(h[A[preR]]==1){
                    comps--;
                    if(h[A[preR]-1]==1){
                        comps++;
                    }
                    if(h[A[preR]+1]==1){
                        comps++;
                    }
                }
                h[A[preR]]--;
                preR--;
            }
            ans[ind[i]] = comps;
        }        
        for(int i=0;i<Q;i++){
            System.out.println(ans[i]);
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
