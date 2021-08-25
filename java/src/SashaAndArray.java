package src;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 *
 * @author PrudhviRaj
 */
 
public class SashaAndArray {

    /**
     * @param args the command line arguments
     */
    static int n, m;
    static final int mod = 1000000007;

    static HashMap<Long,Long> hm = new HashMap();
    static long F(long x){
        if(hm.containsKey(x)){
            return hm.get(x);
        }
        if(x==0){
            return 0;
        }
        if(x==1||x==2){
            return 1;
        }
        long f = F(x/2);
        long fm1 = F(x/2 + 1);
        long ans;
        if(x%2==0){
            ans = (f*(2*fm1 - f))%mod;
        }
        else{
            ans = ((f*f)%mod + (fm1*fm1)%mod)%mod;
        }
        hm.put(x, ans);
        return ans;
    }
    static long tree[], treeM1[], treeP1[];
    static int lazy[];
    static int a[];

    static void buildSegmentTree(int left, int right, int current) {
        if (left == right) {
            tree[current] = F(a[left]);
            treeM1[current] = F(a[left] - 1);
            treeP1[current] = F(a[left] + 1);
            return;
        }
        int mid = (left + right) >> 1;
        buildSegmentTree(left, mid, 2 * current + 1);
        buildSegmentTree(mid + 1, right, 2 * current + 2);
        tree[current] = (tree[2 * current + 1] + tree[2 * current + 2]) % mod;
        treeM1[current] = (treeM1[2 * current + 1] + treeM1[2 * current + 2]) % mod;
        treeP1[current] = (tree[current] + treeM1[current]) % mod;
    }

    static void updateTree(int uStart, int uEnd, int update, int start, int end, int current) {

        if (lazy[current] != 0) {
            treeP1[current] = ((F(lazy[current]) * tree[current]) % mod + (F(lazy[current] + 1) * treeP1[current]) % mod) % mod;
            tree[current] = ((F(lazy[current]) * treeM1[current]) % mod + (F(lazy[current] + 1) * tree[current]) % mod) % mod;

            treeM1[current] = (treeP1[current] - tree[current] + mod) % mod;
            if (start != end) {
                lazy[2 * current + 1] += lazy[current];
                lazy[2 * current + 2] += lazy[current];
            }
            lazy[current] = 0;
        }
        if (uStart > end || uEnd < start) {
            return;
        }
        if (uStart <= start && uEnd >= end) {
            treeP1[current] = ((F(update) * tree[current]) % mod + (F(update + 1) * treeP1[current]) % mod) % mod;
            tree[current] = ((F(update) * treeM1[current]) % mod + (F(update + 1) * tree[current]) % mod) % mod;
            treeM1[current] = (treeP1[current] - tree[current] + mod) % mod;
            if (start != end) {
                lazy[2 * current + 1] += update;
                lazy[2 * current + 2] += update;
            }
            return;
        }
        int mid = (start + end) >> 1;
        updateTree(uStart, uEnd, update, start, mid, 2 * current + 1);
        updateTree(uStart, uEnd, update, mid + 1, end, 2 * current + 2);
        tree[current] = (tree[2 * current + 1] + tree[2 * current + 2]) % mod;
        treeM1[current] = (treeM1[2 * current + 1] + treeM1[2 * current + 2]) % mod;
        treeP1[current] = (tree[current] + treeM1[current]) % mod;
    }

    static long getSum(int qStart, int qEnd, int start, int end, int current) {

        if (lazy[current] != 0) {
            treeP1[current] = ((F(lazy[current]) * tree[current]) % mod + (F(lazy[current] + 1) * treeP1[current]) % mod) % mod;
            tree[current] = ((F(lazy[current]) * treeM1[current]) % mod + (F(lazy[current] + 1) * tree[current]) % mod) % mod;

            treeM1[current] = (treeP1[current] - tree[current] + mod) % mod;
            if (start != end) {
                lazy[2 * current + 1] += lazy[current];
                lazy[2 * current + 2] += lazy[current];
            }
            lazy[current] = 0;
        }
        if (qStart > end || qEnd < start) {
            return 0;
        }
        if (qStart <= start && qEnd >= end) {
            return tree[current];
        }

        int mid = (start + end) >> 1;
        long a = getSum(qStart, qEnd, start, mid, 2 * current + 1);
        long b = getSum(qStart, qEnd, mid + 1, end, 2 * current + 2);
        return (a + b) % mod;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputReader ir = new InputReader();
        n = ir.readInt();
        m = ir.readInt();
        tree = new long[(int) Math.pow(2, (int) Math.ceil(Math.log(n) / Math.log(2)) + 1) - 1];
        lazy = new int[(int) Math.pow(2, (int) Math.ceil(Math.log(n) / Math.log(2)) + 1) - 1];
        treeM1 = new long[(int) Math.pow(2, (int) Math.ceil(Math.log(n) / Math.log(2)) + 1) - 1];
        treeP1 = new long[(int) Math.pow(2, (int) Math.ceil(Math.log(n) / Math.log(2)) + 1) - 1];
        a = ir.readIntArray1(n);
        buildSegmentTree(1, n, 0);
        int c, l, r;
        while (m-- > 0) {
            c = ir.readInt();
            l = ir.readInt();
            r = ir.readInt();
            if (c == 1) {
                int x = ir.readInt();
                updateTree(l, r, x, 1, n, 0);
            } else {
                System.out.println(getSum(l, r, 1, n, 0));
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
