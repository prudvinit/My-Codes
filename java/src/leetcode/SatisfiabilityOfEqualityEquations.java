package src.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class SatisfiabilityOfEqualityEquations {
    int parent[];

    int find(int a){
        if(parent[a]!=a){
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }
    int find(char a){
        return find(a-'a');
    }
    void union(String s){
        parent[find(s.charAt(0)-'a')] = parent[find(s.charAt(3)-'a')];
    }

    public boolean equationsPossible(String[] equations) {
        Arrays.sort(equations, new Comparator<String>() {
            public int compare(String a, String b) {
                if (a.contains("!=")) {
                    if (b.contains("!=")) {
                        return 0;
                    }
                    return 1;
                }
                return -1;
            }
        });
        parent = new int[26];
        for(int i=0;i<parent.length;i++)parent[i]=i;
        for (String eq : equations) {
            if (eq.contains("==")) {
                union(eq);
            }
            else{
                if(find(eq.charAt(0))==find(eq.charAt(eq.length()-1))){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SatisfiabilityOfEqualityEquations().equationsPossible(new String[]{"a==b","b!=a"}));
        System.out.println(new SatisfiabilityOfEqualityEquations().equationsPossible(new String[]{"b==a","a==b"}));
        System.out.println(new SatisfiabilityOfEqualityEquations().equationsPossible(new String[]{"a==b","b==c","a==c"}));
        System.out.println(new SatisfiabilityOfEqualityEquations().equationsPossible(new String[]{"a==b","b!=c","c==a"}));
    }
}
