//https://techdevguide.withgoogle.com/resources/former-interview-question-compression-and-decompression/#!
package src.google;

public class CompressionAndDecompression {
    static String compress(String s){
        if(s.length()==0)
            return s;
        boolean startsWithDigit = Character.isDigit(s.charAt(0));
        if(startsWithDigit){
            int num = 0;
            int ind = 0;
            while (s.charAt(ind)!='['){
                num = 10*num + s.charAt(ind)-48;
                ind++;
            }
            int left = ind;
            int lc = 1;
            int right = ind+1;
            for(int i=left+1;i<s.length();i++){
                if(s.charAt(i)=='['){
                    lc++;
                }
                else if(s.charAt(i)==']'){
                    lc--;
                    if(lc==0){
                        right = i;
                        break;
                    }
                }
            }
            String rec = compress(s.substring(left+1,right));
            String ans = "";
            for(int i=0;i<num;i++){
                ans = ans + rec;
            }
            return ans + compress(s.substring(right+1));
        }
        return s;
    }
    public static void main(String[] args) {
        String s = "3[abc]4[ab]c";
        System.out.println(s+" : "+compress(s));
        s = "10[a]";
        System.out.println(s+" : "+compress(s));
        s = "2[3[a]b]";
        System.out.println(s+" : "+compress(s));
    }
}
