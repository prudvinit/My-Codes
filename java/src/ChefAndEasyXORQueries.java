package src;
//https://www.codechef.com/problems/CHEFEXQ

import java.io.*;
import java.util.*;

/**
 *
 * @author pmaddala
 */
public class ChefAndEasyXORQueries {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        int N=ir.readInt(),Q=ir.readInt(),A[]=ir.readIntArray(N);
        int size = (int)Math.sqrt(N);
        int block[] = new int[N+1];
        int start[] = new int[size+2];
        int end[] = new int[N+1];
        HashMap<Integer,Integer> hm[] = new HashMap[size+2];
        for(int i=0;i<hm.length;i++){
            hm[i] = new HashMap();
        }
        int xor[] = new int[size+2];
        for(int i=0;i<N;i++){
            block[i] = (i/(size)); 
            xor[block[i]] ^= A[i];
            if(!hm[block[i]].containsKey(xor[block[i]])){
                hm[block[i]].put(xor[block[i]], 0);
            }
            hm[block[i]].put(xor[block[i]], hm[block[i]].get(xor[block[i]])+1);
        }
        for(int i=N-1;i>=0;i--){
            start[block[i]] = i;
        }
        while(Q-->0){
            int type = ir.readInt();
            int L = ir.readInt()-1;
            int R = ir.readInt();
            if(type==1){
                int upd = A[L]^R;
                xor[block[L]] ^= upd;
                hm[block[L]].clear();
                int tx = 0;
                A[L] = R;
                for(int i=start[block[L]];block[i]==block[L];i++){
                    tx = tx^A[i];
                    if(!hm[block[L]].containsKey(tx)){
                        hm[block[L]].put(tx, 0);
                    }
                    hm[block[L]].put(tx,hm[block[L]].get(tx)+1);
                }
            }
            else{
                int ans = 0;
                int prev = 0;
                for(int i=0;i<=block[L]-1;i++){
                    int target = prev^R;
                    if(hm[i].containsKey(target)){
                        ans = ans + hm[i].get(target);
                    }
                    prev = prev^xor[i];
                }
                for(int i=start[block[L]];i<=L&&block[i]==block[L];i++){
                    prev = prev^A[i];
                    if(prev==R){
                        ans++;
                    }
                }
                System.out.println(ans);
            }
        }
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



