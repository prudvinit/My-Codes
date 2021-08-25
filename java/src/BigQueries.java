package src;//PROBLEM : https://www.codechef.com/OCT16/problems/BGQRS/


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
 
/**
 *
 * @author prudvi.maddala@oracle.com
 */
 
public class BigQueries{
 
    /**
     * @param args the command line arguments
     */
    static int[] count(int x){
        int t = 0,f = 0;
        while(x%2==0){
            x/=2;
            t++;
        }
        while(x%5==0){
            x/=5;
            f++;
        }
        return new int[]{t,f};
    }
    
    static final int MAX = 100000;
    
    static void init(){
        TWOS = new int[MAX+1];
        FIVES = new int[MAX+1];
        int arr[];
        for(int i=2;i<=MAX;i++){
            arr = count(i);
            TWOS[i] = TWOS[i-1] + arr[0];
            FIVES[i] = FIVES[i-1] + arr[1];
        }
    }
    
    static int TWOS[],FIVES[];
    static int n,A[],tree[][],lazy[][];  
    static boolean zeros[];
    
    static void buildTree(int left, int right, int current){
        int t[];
        if(left==right){
            t = count(A[left]);
            tree[current][0] = t[0];
            tree[current][1] = t[1];
            return;
        }
        int mid = (left+right)>>1;
        buildTree(left,mid,2*current+1);
        buildTree(mid+1,right,2*current+2);
        tree[current][0] = tree[2*current+1][0] + tree[2*current+2][0];
        tree[current][1] = tree[2*current+1][1] + tree[2*current+2][1];        
    }
    
    static void updateTree(int uStart, int uEnd, int update, int start, int end, int current){
        if(zeros[current]){
            tree[current][0] = 0;
            tree[current][1] = 0;
            if(start!=end){
                zeros[2*current+1] = true;
                zeros[2*current+2] = true;
                
                lazy[2*current+1][0] = 0;
                lazy[2*current+1][1] = 0;
                lazy[2*current+2][0] = 0;
                lazy[2*current+2][1] = 0;
            }
            zeros[current] = false;
        }
        if(lazy[current][0]>0){
            tree[current][0] += (end-start+1)*lazy[current][0];
            if(start!=end){
                lazy[2*current+1][0] += lazy[current][0];
                lazy[2*current+2][0] += lazy[current][0];
            }
            lazy[current][0] = 0;
        }
        if(lazy[current][1]>0){
            tree[current][1] += (end-start+1)*lazy[current][1];
            if(start!=end){
                lazy[2*current+1][1] += lazy[current][1];
                lazy[2*current+2][1] += lazy[current][1];
            }
            lazy[current][1] = 0;
        }
        
        if(uStart>end||uEnd<start){
            return;
        }
        if(uStart<=start&&uEnd>=end){
            if(update==-1){
                tree[current][0] = 0;
                tree[current][1] = 0;
                if(start!=end){
                    zeros[2*current+1] = true;
                    zeros[2*current+2] = true;
                    lazy[2*current+1][0] = 0;
                    lazy[2*current+1][1] = 0;
                    lazy[2*current+2][0] = 0;
                    lazy[2*current+2][1] = 0;
                }
            }
            else{
                int t[] = count(update);
                tree[current][0] += (end-start+1)*t[0];
                tree[current][1] += (end-start+1)*t[1];
                if(start!=end){
                    lazy[2*current+1][0] += t[0];
                    lazy[2*current+2][0] += t[0];
                    lazy[2*current+1][1] += t[1];
                    lazy[2*current+2][1] += t[1];
                }
            }
            return;
        }
        int mid = (start+end)>>1;
        updateTree(uStart,uEnd,update,start,mid,2*current+1);
        updateTree(uStart,uEnd,update,mid+1,end,2*current+2);
        tree[current][0] = tree[2*current+1][0] + tree[2*current+2][0];
        tree[current][1] = tree[2*current+1][1] + tree[2*current+2][1];
    }
    
    static int[] getSum(int qStart, int qEnd, int start, int end, int current){
        if(zeros[current]){
            tree[current][0] = 0;
            tree[current][1] = 0;
            if(start!=end){
                zeros[2*current+1] = true;
                zeros[2*current+2] = true;
                
                lazy[2*current+1][0] = 0;
                lazy[2*current+1][1] = 0;
                lazy[2*current+2][0] = 0;
                lazy[2*current+2][1] = 0;
            }
            zeros[current] = false;
        }
        if(lazy[current][0]>0){
            tree[current][0] += (end-start+1)*lazy[current][0];
            if(start!=end){
                lazy[2*current+1][0] += lazy[current][0];
                lazy[2*current+2][0] += lazy[current][0];
            }
            lazy[current][0] = 0;
        }
        if(lazy[current][1]>0){
            tree[current][1] += (end-start+1)*lazy[current][1];
            if(start!=end){
                lazy[2*current+1][1] += lazy[current][1];
                lazy[2*current+2][1] += lazy[current][1];
            }
            lazy[current][1] = 0;
        }
        if(qStart>end||qEnd<start){
            return new int[]{0,0};
        }
        if(qStart<=start&&qEnd>=end){
            return tree[current];
        }        
        int mid = (start+end)>>1;
        int a[] = getSum(qStart,qEnd,start,mid,2*current+1);
        int b[] = getSum(qStart,qEnd,mid+1,end,2*current+2);
        return new int[]{a[0]+b[0], a[1]+b[1]};
    }    
    
    static int prefix[][],plazy[][];
    
    static void updatePrefix(int uStart, int uEnd, int updL, int updR, int start, int end, int current){
        if(plazy[current][0]!=0||plazy[current][1]!=0){
            int l = plazy[current][0];
            int r = plazy[current][1];
            prefix[current][0] = TWOS[r]-TWOS[l-1];
            prefix[current][1] = FIVES[r]-FIVES[l-1];
            if(start!=end){            
                plazy[2*current+1][0] = plazy[current][0];
                plazy[2*current+1][1] = (plazy[current][0]+plazy[current][1])/2;
            
                plazy[2*current+2][0] = plazy[2*current+1][1]+1;
                plazy[2*current+2][1] = plazy[current][1];
            }
            plazy[current][0] = plazy[current][1] = 0;
        }
        if(uStart>end||uEnd<start){
            return;
        }
        if(uStart<=start&&uEnd>=end){
            prefix[current][0] = TWOS[updR]-TWOS[updL-1];
            prefix[current][1] = FIVES[updR]-FIVES[updL-1];
            if(start!=end){
                plazy[2*current+1][0] = updL;
                plazy[2*current+1][1] = (updL+updR)/2;
                plazy[2*current+2][0] = plazy[2*current+1][1]+1;
                plazy[2*current+2][1] = updR;
            }
            return;
        }
        int mid = (start+end)>>1;
        int ls,le,rs,re;
        ls = le = rs = re = 0;
        if(mid<uStart){
            rs = updL;
            re = updR;
            updatePrefix(uStart,uEnd,ls,le , start,mid,2*current+1);
            updatePrefix(uStart,uEnd,rs,re, mid+1,end,2*current+2);
        }
        else if(mid>uEnd){
            ls = updL;
            le = updR;
            updatePrefix(uStart,uEnd,ls,le , start,mid,2*current+1);
            updatePrefix(uStart,uEnd,rs,re, mid+1,end,2*current+2);
        }
        else{
            ls = updL;
            le = mid-Math.max(uStart,start)+updL;
            rs = le+1;
            re = Math.min(uEnd,end)-mid+le;
            updatePrefix(uStart,uEnd,ls,le , start,mid,2*current+1);
            updatePrefix(uStart,uEnd,rs,re, mid+1,end,2*current+2);
        }
        prefix[current][0] = prefix[2*current+1][0]+prefix[2*current+2][0];
        prefix[current][1] = prefix[2*current+1][1]+prefix[2*current+2][1];
    }
    
    static int[] getCountPrefix(int qStart, int qEnd, int start, int end, int current){
        if(plazy[current][0]!=0||plazy[current][1]!=0){
            int l = plazy[current][0];
            int r = plazy[current][1];
            prefix[current][0] = TWOS[r]-TWOS[l-1];
            prefix[current][1] = FIVES[r]-FIVES[l-1];
            if(start!=end){            
                plazy[2*current+1][0] = plazy[current][0];
                plazy[2*current+1][1] = (plazy[current][0]+plazy[current][1])/2;
                plazy[2*current+2][0] = plazy[2*current+1][1]+1;
                plazy[2*current+2][1] = plazy[current][1];
            }
            plazy[current][0] = plazy[current][1] = 0;
        }
        if(qStart>end||qEnd<start){
            return new int[]{0,0};
        }
        if(qStart<=start&&qEnd>=end){
            return new int[]{prefix[current][0],prefix[current][1]};
        }
        int mid = (start+end)>>1;
        int a[] = getCountPrefix(qStart, qEnd, start, mid, 2*current+1);
        int b[] = getCountPrefix(qStart, qEnd, mid+1, end, 2*current+2);
        return new int[]{a[0]+b[0],a[1]+b[1]};
    }
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        init();
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);
        int T = ir.readInt(),m,type,L,R,X,Y,tans;
        long ans = 0;
        while(T-->0){
            n = ir.readInt();
            m = ir.readInt();
            A = ir.readIntArray1(n);            
            final int size = (int)Math.pow(2,(int)Math.ceil(Math.log(n+1)/Math.log(2))+1)-1;
            tree = new int[size][2];
            lazy = new int[size][2];
            zeros = new boolean[size];
            prefix = new int[size][2];;
            plazy = new int[size][2];
            buildTree(1, n, 0);
            ans = 0;
            while(m-->0){
                type = ir.readInt();
                L = ir.readInt();
                R = ir.readInt();
                if(type==1){
                    X = ir.readInt();
                    updateTree(L, R, X, 1, n, 0); 
                }
                else if(type==2){
                    Y = ir.readInt();
                    updateTree(L, R, -1, 1, n, 0);
                    updateTree(L, R, Y, 1, n, 0);
                    updatePrefix(L, R, 1, R-L+1, 1, n, 0);
                }
                else{
                    int arr1[] = getSum(L, R, 1, n, 0);
                    int arr2[] = getCountPrefix(L, R, 1, n, 0);
                    ans += Math.min(arr1[0]+arr2[0],arr1[1]+arr2[1]);
                }
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
