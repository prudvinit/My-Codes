/*
  PROBLEM http://www.codeforces.com/contest/707/problem/D
  Idea given by sumeet varma
*/


package persistentbookcase;

import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;
import java.util.ArrayList;

public class PersistentBookcase {

    /**
     * @param args the command line arguments
     * @author prudvi.maddala@oracle.com
     */
    static class Q{
        int index,type,a,b=-1,ans,parent,fans;
        ArrayList<Integer> childs;
        boolean change;
        Q(int index,int type,int a,int b){
            this.index = index;
            this.type = type;
            this.a = a;
            this.b = b;
            childs = new ArrayList();
        }
        Q(int index,int type,int a){
            this.index = index;
            this.type = type;
            this.a = a;
            childs = new ArrayList();
        }
        @Override
        public String toString(){
            String ans = "("+type +" ";
            ans = ans + a;
            if(b!=-1){
                ans = ans+" "+b;
            }
            ans = ans+")";
            return ans;
        }
    }
    //This is the method with longest name that i've ever written
    static void performDfsAndCalculateTheAnswerForEachQuery(int u){
        int type = query[u].type;
        int a = query[u].a;
        int b = query[u].b;
        int parent = query[u].parent;
        query[u].ans = query[parent].ans;
        if(type==1){            
            if(!shelf[a].get(b)){
                shelf[a].set(b);
                query[u].change = true;
                query[u].ans++;
            }
        }
        else if(type==2){
            if(shelf[a].get(b)){
                shelf[a].clear(b);
                query[u].change = true;
                query[u].ans--;
            }
        }
        else if(type==3){
            int card = shelf[a].cardinality();
            shelf[a].flip(1, m+1);
            int fin = m-card;
            query[u].ans = query[u].ans + fin - card;
        }
        else if(type==4){
            
        }
        if(u!=0){
            query[u].fans = query[u].ans;
        }
        
        for(int x : query[u].childs){
            performDfsAndCalculateTheAnswerForEachQuery(x);
        }
        //Get Back the node to normal state
        
        if(type==1){
            if(query[u].change){
                shelf[a].clear(b);
                query[u].ans--;
            }
        }
        else if(type==2){
            if(query[u].change){
                shelf[a].set(b);
                query[u].ans++;
            }
        }
        else if(type==3){
            int card = shelf[a].cardinality();
            shelf[a].flip(1,m+1);
            int fin = m;
            query[u].ans = query[u].ans + fin -card;            
        }
        else if(type==4){
            
        }
        
    }
    
    static Q[] query;
    static BitSet shelf[];
    static int n,m,q;
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputReader ir = new InputReader();
        n = ir.readInt();m = ir.readInt();q = ir.readInt();
        int ans[] = new int[q+1],ch,s,p,tans = 0;
        shelf = new BitSet[n+1];
        for(int i=1;i<=n;i++){
            shelf[i] = new BitSet(m+1);
        }
        query = new Q[q+1];
        query[0] = new Q(0, 0, -1); //This is our beautiful root
        for(int i=1;i<=q;i++){
            //System.out.println("q "+i);
            ch = ir.readInt();
            if(ch==1){
                s = ir.readInt();p = ir.readInt();
                query[i] = new Q(i,ch,s,p);
                query[i-1].childs.add(i);
                query[i].parent = i-1;
            }
            else if(ch==2){
                s = ir.readInt();p = ir.readInt();
                query[i] = new Q(i,ch,s,p);
                query[i-1].childs.add(i);
                query[i].parent = i-1;
            }
            else if(ch==3){
                s = ir.readInt();
                query[i] = new Q(i,ch,s);
                query[i-1].childs.add(i);
                query[i].parent = i-1;
            }
            else{
                s = ir.readInt();
                query[i] = new Q(i,ch,s);
                query[s].childs.add(i);
                query[i].parent = s;
            }
        }
        performDfsAndCalculateTheAnswerForEachQuery(0);
        for(int i=1;i<=q;i++){
            System.out.println(query[i].fans);
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
    }
}
