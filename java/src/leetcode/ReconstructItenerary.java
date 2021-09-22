package src.leetcode;


import java.util.*;

public class ReconstructItenerary {

    void dfs(Map<String,List<String>> graph, String s, Stack<String> ans, Map<String,Integer> edgeCount){
        for(String edge : graph.getOrDefault(s,new ArrayList<String>())){
            if(edgeCount.getOrDefault(s+" "+edge,0)>0){
                edgeCount.put(s+" "+edge,edgeCount.get(s+" "+edge)-1);
                dfs(graph,edge,ans,edgeCount);
            }
        }
        ans.add(s);
    }
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String,List<String>> graph = new HashMap();
        Map<String, Integer> edgeCount = new HashMap();
        for(List<String> ticket : tickets){
            if(!graph.containsKey(ticket.get(0))){
                graph.put(ticket.get(0), new ArrayList<String>());
            }
            graph.get(ticket.get(0)).add(ticket.get(1));
            edgeCount.put(ticket.get(0)+" "+ticket.get(1),edgeCount.getOrDefault(ticket.get(0)+" "+ticket.get(1),0)+1);
        }

        for(String key : graph.keySet()){
            Collections.sort(graph.get(key));
        }
        System.out.println(graph);
        Stack<String> ans = new Stack();
        dfs(graph,"JFK",ans,edgeCount);
        List<String> fans = new ArrayList();
        while (!ans.isEmpty()){
            fans.add(ans.pop());
        }
        return fans;
    }

    static List<List<String>> makeList(String arr[][]){
        List<List<String>> ans = new ArrayList();
        for(String s[] : arr){
            ans.add(Arrays.asList(s));
        }

        return ans;
    }
    public static void main(String[] args) {

//        System.out.println(new ReconstructItenerary().findItinerary(makeList(new String[][]{{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}})));
//        System.out.println(new ReconstructItenerary().findItinerary(makeList(new String[][]{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}})));
//        System.out.println(new ReconstructItenerary().findItinerary(makeList(new String[][]{{"JFK","NRT"},{"NRT","JFK"},{"JFK","KUL"}})));
        System.out.println(new ReconstructItenerary().findItinerary(makeList(new String[][]{{"EZE","AXA"},{"TIA","ANU"},{"ANU","JFK"},{"JFK","ANU"},{"ANU","EZE"},{"TIA","ANU"},{"AXA","TIA"},{"TIA","JFK"},{"ANU","TIA"},{"JFK","TIA"}})));
    }
}
