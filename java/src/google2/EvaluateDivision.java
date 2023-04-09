package src.google2;

import java.util.*;

public class EvaluateDivision {
    String[] simplify(String a, String b) {
        int f1[] = new int[26];
        int f2[] = new int[26];
        for (int i = 0; i < a.length(); i++) {
            f1[a.charAt(i) - 'a']++;
        }
        for (int i = 0; i < b.length(); i++) {
            f2[b.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            int m = Math.min(f1[i], f2[i]);
            f1[i] -= m;
            f2[i] -= m;
        }
        String a1 = "";
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < f1[i]; j++)
                a1 = a1 + (char) ('a' + i);
        }
        String b1 = "";
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < f2[i]; j++)
                b1 = b1 + (char) ('a' + i);
        }
        return new String[]{a1, b1};
    }

    static double fans = -1;

    public void dfs(String V, String target, String parent, Map<String, List<String>> graph, Map<String, Double> weight, Set<String> visited, double prod) {
//        System.out.println("Node "+V+" "+visited.contains(V));
        visited.add(V);
        if (V.equals(target)) {
            fans = prod;
            return;
        }
        double ans = 1;
        for (String child : graph.getOrDefault(V,new ArrayList<String>())) {
            if (!visited.contains(child)) {
                dfs(child, target, V, graph, weight, visited, prod * weight.get(V + " " + child));
            }
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Double> weight = new HashMap();
        Map<String, List<String>> graph = new HashMap();
        int ind = 0;
        for (List<String> eq : equations) {
            String s[] = simplify(eq.get(0), eq.get(1));
            String a = s[0];
            String b = s[1];
            if (!graph.containsKey(a)) {
                graph.put(a, new ArrayList());
            }
            if (!graph.containsKey(b)) {
                graph.put(b, new ArrayList());
            }
            graph.get(a).add(b);
            graph.get(b).add(a);
            weight.put(a + " " + b, values[ind]);
            weight.put(b + " " + a, 1 / values[ind]);
            ind++;
        }
        double ans[] = new double[queries.size()];
        ind = 0;
        for (List<String> q : queries) {
            fans = -1;
            String s[] = simplify(q.get(0), q.get(1));
            if (s[0].equals(s[1])) {
                if(graph.containsKey(q.get(0))) {
                    ans[ind++] = 1d;
                }
                else{
                    ans[ind++] = -1;
                }
            } else {
                dfs(s[0], s[1], "", graph, weight, new HashSet<String>(), 1);
                ans[ind++] = fans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<String> first = Arrays.asList("a","b");
        List<String> second = Arrays.asList("b","c");
        List<List<String>> eq = new ArrayList(Arrays.asList(Arrays.asList("a","b"),Arrays.asList("b","c"),Arrays.asList("bc","cd")));
        System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(eq,
                new double[]{1.5d,2.5d,5d},
                new ArrayList(Arrays.asList(Arrays.asList("cd","bc"))))));
    }
}