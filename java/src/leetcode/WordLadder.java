//https://leetcode.com/problems/word-ladder/
package src.leetcode;

import java.util.*;

public class WordLadder {
    public boolean edge(String a, String b){
        int c = 0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)!=b.charAt(i))c++;
            if(c>1){
                return false;
            }
        }
        return true;
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(beginWord)) {
            wordList.add(0, beginWord);
        }
        int n = wordList.size();
        ArrayList<ArrayList<Integer>> graph = new ArrayList();
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<Integer>());
        }
        //Create a graph
        for(int i=0;i<wordList.size();i++){
            for(int j=i+1;j<wordList.size();j++){
                if(edge(wordList.get(i),wordList.get(j))){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        int dist[] = new int[n];
        boolean visited[] = new boolean[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList();
        int src = wordList.indexOf(beginWord);
        queue.add(src);
        dist[src] = 0;
        visited[src] = true;
        while (!queue.isEmpty()){
            int e = queue.remove();
            for(int c : graph.get(e)){
                //If there's edge between parent and child, ignore
                if(c==e){
                    continue;
                }
                //Only update, if not visited
                if(!visited[c]) {
                    dist[c] = Math.min(dist[c], 1 + dist[e]);
                    visited[c] = true;
                    queue.add(c);
                }
            }
        }

        return wordList.indexOf(endWord)==-1?0:(dist[wordList.indexOf(endWord)]!=Integer.MAX_VALUE?(1+dist[wordList.indexOf(endWord)]):0);
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList(Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println(new WordLadder().ladderLength("hit","cog",list));
        list = new ArrayList(Arrays.asList("hot","dot","dog","lot","log"));
        System.out.println(new WordLadder().ladderLength("hit","cog",list));
        list = new ArrayList(Arrays.asList("hot","dog"));
        System.out.println(new WordLadder().ladderLength("hot","dog",list));
    }
}
