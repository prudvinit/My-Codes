/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * PROBLEM : https://www.hackerrank.com/contests/womens-codesprint-2/challenges/prime-xor
 */

package primexor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author PrudhviRaj
 */
public class PrimeXor {

    /**
     * @param args the command line arguments
     */
    static final int mod = 1000000007;
   
    static final int MAX = 8193;
    static boolean primes[];
    static void init(){
        primes = new boolean[MAX];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for(int i=2;i<MAX;i++){
            if(primes[i]){
                for(int j=2;j*i<MAX;j++){
                    primes[i*j] = false;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        init();
        InputReader ir = new InputReader();
        int q = ir.readInt();
        int n,a[];
        long ways[][];
        int f[];
        while(q-->0){
            n = ir.readInt();
            a = ir.readIntArray(n);
            f = new int[MAX];
            for(int i=0;i<n;i++){
                f[a[i]]++;
            }
            HashSet<Integer> hs = new HashSet();
            for(int i=0;i<n;i++){
                hs.add(a[i]);
            }
            n = hs.size();
            ways = new long[n][MAX];
            int i = 0;
            for(int x : hs){
                ways[i][x] = (f[x]+1)/2;
                ways[i][0] = f[x]/2;
                for(int j=0;j<MAX;j++){
                    ways[i][j] = (ways[i][j] +  ((i-1>=0 && (j^x)<MAX)?(ways[i-1][j] + ways[i-1][j^x]*((f[x]+1)/2)):0)%mod)%mod;
                    ways[i][j] = (ways[i][j] + ((i-1>=0)?(f[x]/2)*ways[i-1][j]:0))%mod;
                }
                i++;
            }
            long ans = 0;
            for(int j=0;j<MAX;j++){
                if(primes[j]){
                    ans = (ans + ways[hs.size()-1][j])%mod;
                }
            }
            System.out.println(ans);
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
