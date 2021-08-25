package src;//PROBLEM : https://www.hackerrank.com/contests/w3/challenges/gcd-product

import java.io.*;

public class GCDProduct {

    static final int MAX = 15000000,MOD = 1000000007;
    
    static long pow(long x , long y){
        if(y==0)return 1;
        long ans = pow(x,y/2);
        ans = (ans*ans)%MOD;
        if(y%2==1)ans = (ans*x)%MOD;
        return ans;
    }
             
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        long N = ir.readInt();
        long M = ir.readInt();        
        long F[] = new long[MAX+1];
        for(int i = 1; i <= MAX; i++){
            F[i] = (1l * (N / i) * (M / i)) ;
        }
        for(int i = MAX; i >= 1; i--)
            for(int j = i + i; j <= MAX; j += i) {
                F[i] -= F[j];
            }
    
        int e = (int)Math.min(N, M);
        long ans = 1;
        for(int i=2;i<=e;i++){
            ans = (ans * pow(i,F[i]))%MOD;
        }
        System.out.println(ans);        
    }
}
