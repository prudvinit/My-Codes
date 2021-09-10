package src;

public class LongestCommonSubstring {
    //This is a bruteforce approach
    int longestCommonSubstr1(String S1, String S2, int n, int m){
        if(S1.length()<S2.length()){
            String t = S1;
            S1 = S2;
            S2 = t;
        }
        // code here
        n = S1.length();
        m = S2.length();
        int fans = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int ans = 0;
                for(int k=j;k<m&&i+(k-j)<n;k++){
                    if(S2.charAt(k)==S1.charAt(i+(k-j))){
                        ans++;
                        fans = Math.max(fans,ans);
                    }
                    else{
                        break;
                    }
                }
            }
        }
        return fans;
    }

    //DP Approach

    int longestCommonSubstr(String S1, String S2, int n, int m){
        int dp[][] = new int[n][m];
        int ans = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                //Check if S1[i]==S2[j]
                int tail = S1.charAt(i)==S2.charAt(j)?1:0;
                //if i and jth chars are equal , the DP[i][j] = 1+DP[i-1][j-1], otherwise 0
                dp[i][j] = tail+((tail==1&&i>=1&&j>=1)?(dp[i-1][j-1]):0);
                ans = Math.max(dp[i][j],ans);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s1 = "ABCDGH";
        String s2 = "ACDGHR";
        int n = s1.length();
        int m = s2.length();
        System.out.println(new LongestCommonSubstring().longestCommonSubstr(s1,s2,n,m));
        s1 = "ABC";
        s2 = "ACB";
        n = s1.length();
        m = s2.length();
        System.out.println(new LongestCommonSubstring().longestCommonSubstr(s1,s2,n,m));
        s1 = "LRBBMQBHCDARZOWKKYHIDDQSCDXRJMOWFRXSJYBLDBEFSARCBYNECDYGGXXPKLORELLNMPAPQFWKHOPKMCO";
        s2 = "QHNWNKUEWHSQMGBBUQCLJJIVSWMDKQTBXIXMVTRRBLJPTNSNFWZQFJMAFADRRWSOFSBCNUVQHFFBSAQXWPQCAC";
        n = s1.length();
        m = s2.length();
        System.out.println(new LongestCommonSubstring().longestCommonSubstr(s1,s2,n,m));
        s1 = "CDEF";
        s2 = "CDEF";
        n = s1.length();
        m = s2.length();
        System.out.println(new LongestCommonSubstring().longestCommonSubstr(s1,s2,n,m));
    }
}
