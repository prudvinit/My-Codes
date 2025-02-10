package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TheGame {

    static void dfs(int u, int p, ArrayList<Integer> tree[], int size[], List<Integer> t){
        int c = 0;
        t.add(u);
        for(int child : tree[u]){
            if(child==p)continue;
            dfs(child,u,tree,size,t);
            c+=size[child];
        }
        size[u] = c+1;
    }

    static void update(int BIT[], int pos, int value){
        for(;pos<BIT.length;pos+=pos&-pos){
            BIT[pos]+=value;
        }
    }

    static int get(int BIT[], int pos){
        int ans = 0;
        for(;pos>0;pos-=pos&-pos){
            ans+=BIT[pos];
        }
        return ans;
    }

    static int getSum(int BIT[], int start, int end){
        return get(BIT,end)-(start>=1?get(BIT,start-1):0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0){
            int N = Integer.parseInt(br.readLine());
            String s[] = br.readLine().trim().split(" ");
            int w[] = new int[N+1];
            for(int i=0;i<N;i++){
                w[i+1] = Integer.parseInt(s[i]);
            }
            ArrayList<Integer> tree[] = new ArrayList[N+1];
            for(int i=0;i<=N;i++)tree[i] = new ArrayList();
            for(int i=0;i<N-1;i++){
                String uv[] = br.readLine().split(" ");
                int u = Integer.parseInt(uv[0]);
                int v = Integer.parseInt(uv[1]);
                tree[u].add(v);
                tree[v].add(u);
            }
            int size[] = new int[N+1];
            List<Integer> t = new ArrayList();
            dfs(1,-1,tree,size,t);
//            System.out.println("DFS is "+t);
//            System.out.println("Size is "+Arrays.toString(size));
            int pos[] = new int[N+1];
            for(int i=1;i<=N;i++){
                pos[t.get(i-1)] = i;
            }
//            System.out.println("Positions are "+Arrays.toString(pos));
            Integer ind[] = new Integer[N+1];
            for(int i=0;i<ind.length;i++)ind[i]=i;
            Arrays.sort(ind, (a,b)->{
                if(w[a]==w[b])return size[b]-size[a];
                return w[b]-w[a];
            });
            int pref[] = new int[N+1];
            for(int x : w)pref[x]++;
            for(int i=N-1;i>=0;i--){
                pref[i] = pref[i]+pref[i+1];
            }
            int BIT[] = new int[N+1];
            int ans = 0;
            for(int i=0;i<N;i++){
                int e = ind[i];
//                System.out.println("Element "+e+" Weight "+w[e]);
                int subtreeSize = size[e];
                int currentPos = pos[e];
                int currentWeight = w[e];
                int getSubTreeSum = getSum(BIT,currentPos,currentPos+subtreeSize-1);
                int remainingSum = (currentWeight+1<pref.length?pref[currentWeight+1]:0)-getSubTreeSum;
//                System.out.println("Remaining is "+remainingSum);
                if(remainingSum>0){
                    ans = e;
                    break;
                }
                update(BIT,currentPos,1);
            }
            System.out.println(ans);
        }
    }
}
