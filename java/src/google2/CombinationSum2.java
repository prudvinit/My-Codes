package src.google2;

import java.util.*;

public class CombinationSum2 {
    List<List<Integer>> ans = new ArrayList();
    Set<String> set = new HashSet();

    public void comb(int ind, List<Integer> temp,int cand[][], int target){
        if(target<0){
            return;
        }
        if(target==0){
            System.out.println("Found "+temp);
            String gen = temp.get(0)+"";
            for(int i=1;i<temp.size();i++){
                gen = gen+" "+temp.get(i);
            }
            set.add(gen);
            return;
        }
        if(ind>=cand.length){
            return;
        }
        comb(ind+1,temp,cand,target);
        if(cand[ind][1]>=1) {
            temp.add(cand[ind][0]);
            cand[ind][1]--;
            comb(ind, temp, cand, target - cand[ind][0]);
            temp.remove(temp.size() - 1);
            cand[ind][1]++;
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Map<Integer,Integer> map = new HashMap();
        for(int c : candidates){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int cand[][] = new int[map.keySet().size()][2];
        int ind = 0;
        for(Integer i : map.keySet()){
            cand[ind][0] = i;
            cand[ind][1] = map.get(i);
            ind++;
        }
//        for(int i=0;i<cand.length;i++){
//            System.out.println(Arrays.toString(cand[i]));
//        }
//        Arrays.toString(candidates);
        comb(0, new ArrayList(),cand,target);
        for(String x : set){
            List<Integer> tans = new ArrayList();
            for(String t : x.split(" ")) {
                tans.add(Integer.parseInt(t));
            }
            ans.add(tans);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum2().combinationSum2(new int[]{10,1,2,7,6,1,5},8));
    }
}
