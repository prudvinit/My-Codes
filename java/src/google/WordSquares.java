//https://techdevguide.withgoogle.com/resources/former-interview-question-word-squares/#!
package src.google;

import java.util.ArrayList;

import java.util.List;

public class WordSquares {

    static boolean isValid(List<String> list){
        if(list.size()==1){
            return true;
        }
        int n = list.size();
        String last = list.get(n-1);
        for(int i=0;i<n-1;i++){
            if(last.charAt(i)!=list.get(i).charAt(n-1)){
                return false;
            }
        }
        return true;
    }

    static void squares(String[] word, List<String> list){
        if(!list.isEmpty()&&list.size()==list.get(0).length()){
            System.out.println(list);
            return;
        }
        for(String w : word){
            if(list.contains(w)){
                continue;
            }
            list.add(w);
            if(isValid(list)) {
                squares(word, list);
            }
            list.remove(list.size()-1);
        }
    }
    static void squares(String[] word){
        List<String> list = new ArrayList();
        squares(word,list);
    }

    public static void main(String[] args) {
        squares(new String[]{"AREA", "BALL", "DEAR", "LADY", "LEAD", "YARD"});
    }
}
