package src;

import java.io.*;

/**
 *
 * @author PrudhviNIT
 */
class RangeFenwickTrees {

    /**
     * @param args the command line arguments
     */
    
    /**
     * This method updates two fenwick trees fenA, fenM which are fenwick addition and fenwick multiplication
     * @param fenA Fenwick Addition array
     * @param fenM Fenwick Multiplication array
     * @param pos position to update the array
     * @param add value to add
     * @param mul value to multiply
     */
    static void updateFenwickTree(int fenA[], int fenM[], int pos, int add, int mul) {
        int tpos = pos;
        while (tpos < fenA.length) {
            fenA[tpos] = fenA[tpos] + add;
            fenM[tpos] = fenM[tpos] + mul;
            tpos += (tpos & -tpos);
        }
    }

    /**
     * This update updates the range of an array
     * @param fenA Fenwick addition array
     * @param fenM Fenwick multiplication array
     * @param left left index 
     * @param right right index
     * @param val value to update
     */
    static void updateFenwickTreeRange(int fenA[], int fenM[], int left, int right, int val) {
        updateFenwickTree(fenA, fenM, left, -val*(left-1),  val);
        updateFenwickTree(fenA, fenM, right, val * right, -val);
    }

    /**
     * This method returns the prefix sum
     * @param fenA  Fenwick addition array
     * @param fenM Fenwick multiplication array
     * @param pre prefix to return the sum
     * @return Returns the prefix sum
     */
    static int getSumFenwickTree(int fenA[], int fenM[], int pre) {
        int ansA = 0, ansM = 0;
        int tpos = pre;
        while (tpos > 0) {
            ansA = ansA + fenA[tpos];
            ansM = ansM + fenM[tpos];
            tpos -= (tpos & -tpos);
        }
        return ansM * pre + ansA;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputReader ir = new InputReader();
        //5 3 6 8 9 2 5
        int arr[] = new int[]{3, 1, 4, 6, 7, 2, 5};
        int fenA[] = new int[arr.length + 1];
        int fenM[] = new int[arr.length + 1];
//        for (int i = 0; i < arr.length; i++) {
//            updateFenwickTreeRange(fenA, fenM, i + 1, i + 1, arr[i]);
//        }
        int q = ir.readInt();
        while (q-- > 0) {
            int t = ir.readInt();
            if (t == 1) {
                int posL = ir.readInt();
                int posR = ir.readInt();
                int val = ir.readInt();
                updateFenwickTreeRange(fenA, fenM, posL + 1, posR + 1, val);
            } else {
                System.out.println(getSumFenwickTree(fenA, fenM, ir.readInt() + 1));
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
