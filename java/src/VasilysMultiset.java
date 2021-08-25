package src;// Problem : http://www.codeforces.com/contest/706/problem/D

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author prudvi.maddala@oracle.com
 */
public class VasilysMultiset {

    /**
     * @param args the command line arguments
     */
    static class Trie {

        Trie left, right;
        int c = 0;
    }

    static void insert(int x, Trie root) {
        Trie temp = root;
        for (int i = 0; i < BITS; i++) {
            if ((x & (1 << BITS - i - 1)) == 0) {
                if (temp.left == null) {
                    temp.left = new Trie();
                }
                temp = temp.left;
                temp.c++;
            } else {
                if (temp.right == null) {
                    temp.right = new Trie();
                }
                temp = temp.right;
                temp.c++;
            }
        }
    }

    static void remove(int x, Trie root) {
        Trie temp = root;
        for (int i = 0; i < BITS; i++) {
            if ((x & (1 << BITS - i - 1)) == 0) {
                temp = temp.left;
                temp.c--;
            } else {
                temp = temp.right;
                temp.c--;
            }
        }

    }
    static final int BITS = 31;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputReader ir = new InputReader();
        int q = ir.readInt();
        Trie root = new Trie();
        insert(0, root);
        while (q-- > 0) {
            char s = ir.read().charAt(0);
            if (s == '+') {
                int x = ir.readInt();
                insert(x, root);
            } else if (s == '-') {
                int x = ir.readInt();
                remove(x, root);

            } else {
                int max = 0;
                int x = ir.readInt();
                Trie temp = root;
                for (int i = 0; i < BITS; i++) {
                    if ((x & (1 << BITS - i - 1)) == 0) {
                        if (temp.right != null && temp.right.c > 0) {
                            max = 2 * max + 1;
                            temp = temp.right;

                        } else if (temp.left != null && temp.left.c > 0) {
                            max = 2 * max;
                            temp = temp.left;
                        }
                    } else if (temp.left != null && temp.left.c > 0) {
                        max = 2 * max + 1;
                        temp = temp.left;

                    } else if (temp.right != null && temp.right.c > 0) {
                        max = 2 * max;
                        temp = temp.right;
                    }
                }
                System.out.println(max);
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
