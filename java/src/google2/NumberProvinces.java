package src.google2;

import java.util.HashSet;
import java.util.Set;

public class NumberProvinces {

    public int find(int a, int parent[]) {
        if(parent[a]==a)return a;
        parent[a] = find(parent[a],parent);
        return parent[a];
    }

    public void union(int a, int b, int parent[]) {
        parent[find(b, parent)] = parent[find(a, parent)];
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int parent[] = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    System.out.println("Uniting "+i+" "+j);
                    union(i, j, parent);
                }
            }
        }
        Set<Integer> set = new HashSet();
        for (int i = 0; i < n; i++) {
            set.add(find(i, parent));
        }
        return set.size();
    }

    public static void main(String[] args) {
        System.out.println(new NumberProvinces().findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
    }

}
