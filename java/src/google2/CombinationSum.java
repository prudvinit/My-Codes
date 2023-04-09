package src.google2;

import java.util.*;

public class CombinationSum{
    List<List<Integer>> ans = new ArrayList();
    Set<String> set = new HashSet();

    public void comb(int ind, List<Integer> temp,int cand[], int target){
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
        temp.add(cand[ind]);
        comb(ind,temp,cand,target-cand[ind]);
        temp.remove(temp.size()-1);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.toString(candidates);
        comb(0,new ArrayList<Integer>(),candidates,target);
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
        System.out.println(new CombinationSum().combinationSum(new int[]{3,4,7,8},11));
    }
}