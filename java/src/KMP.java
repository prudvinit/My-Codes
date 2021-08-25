package src;

import java.util.Arrays;

class KMP{
    public static int[] lps(String s){
        int ans[] = new int[s.length()];
        int i=1,j=0;
        while(i<s.length()){
            //If s[i] and s[j] match, increment both
            if(s.charAt(i)==s.charAt(j)){
                ans[i] = j+1;
                i++;
                j++;
            }
            else{
                //If j = 0, increment i
                if(j==0){
                    i++;
                }
                else{
                    //move j to previous j
                    while(j!=0&&s.charAt(i)!=s.charAt(j)){
                        j = ans[j-1];
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "abcxabcdabxabcdabcdabcy";
        String pattern = "abcdabcy";
        System.out.println(Arrays.toString(lps(pattern)));
        int lps[] = lps(pattern);
        int i=0,j=0;
        while(i<str.length()&&j<pattern.length()) {
            if(str.charAt(i)==pattern.charAt(j)){
                i++;j++;
                if(j==pattern.length()){
                    System.out.println("MATCH "+(i-pattern.length()));
                    break;
                }
            }
            else{
                if(j==0){
                    i++;
                }
                else {
                    j = lps[j - 1];
                }
            }
        }
    }
}
