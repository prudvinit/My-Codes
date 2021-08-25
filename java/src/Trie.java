package src;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author prudvi.maddala@oracle.com
 */
public class Trie {

    static int trie[][], count[][];
    static int global = 0;
    static int BITS = 5;

    static boolean isPresent(int x) {
        int bit = 0;
        for (int i = 0; i <= BITS - 1; i++) {
            if ((x & (1 << (BITS - 1 - i))) == 0) {
                if (count[bit][0] == 0) {
                    return false;
                } else {
                    bit = trie[bit][0];
                }
            } else if (count[bit][1] == 0) {
                return false;
            } else {
                bit = trie[bit][1];
            }
        }
        return true;
    }

    static void insert(int x) {
        int bits = 0;
        for (int i = 0; i <= BITS - 1; i++) {
            if ((x & (1 << (BITS - 1 - i))) == 0) {
                if (trie[bits][0] == 0) {
                    trie[bits][0] = global + 1;
                    count[bits][0]++;
                    ++global;
                    bits = global;
                } else {
                    bits = trie[bits][0];
                }
            } else if (trie[bits][1] == 0) {
                trie[bits][1] = global + 1;
                count[bits][1]++;
                ++global;
                bits = global;
            } else {
                bits = trie[bits][1];
            }
        }
    }

    static void remove(int x) {
        if (!isPresent(x)) {
            return;
        }
        int bits = 0;
        for (int i = 0; i <= BITS - 1; i++) {
            if ((x & (1 << (BITS - 1 - i))) == 0) {
                count[bits][0]--;
                bits = trie[bits][0];
            } else {
                count[bits][1]--;
                bits = trie[bits][1];
            }
        }
    }

    static int max(int x) {
        int bits = 0;
        int max = 0;
        for (int i = 0; i <= BITS - 1; i++) {
            if ((x & (1 << (BITS - 1 - i))) == 0) {
                if (count[bits][1] > 0) {
                    max = 2 * max + 1;
                    bits = trie[bits][1];
                } else {
                    max = 2 * max;
                    bits = trie[bits][0];
                }
            } else if (count[bits][0] > 0) {
                max = 2 * max + 1;
                bits = trie[bits][0];
            } else {
                max = 2 * max;
                bits = trie[bits][1];
            }
        }
        return max;
    }
    static int q;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputReader ir = new InputReader();
        q = ir.readInt();
        trie = new int[BITS * q][2];
        count = new int[BITS * q][2];
        while (q-- > 0) {
            String str = ir.read();
            if (str.toLowerCase().equals("insert")) {
                insert(ir.readInt());
            } else if (str.toLowerCase().equals("remove")) {
                remove(ir.readInt());
            } else if (str.toLowerCase().equals("present")) {
                System.out.println(isPresent(ir.readInt()));
            } else if (str.toLowerCase().equals("max")) {
                System.out.println(max(ir.readInt()));
            }
        }
        System.out.println(isPresent(8));
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
