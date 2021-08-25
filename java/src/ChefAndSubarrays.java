/*
 *PROBLEM : https://www.codechef.com/COOK77/problems/CHEFARRB
 */

package src;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author PrudhviRaj
 */
public class ChefAndSubarrays {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        InputReader ir = new InputReader();
        int T = ir.readInt();
        while(T-->0){
            int N = ir.readInt();
            int K = ir.readInt();
            long A[] = ir.readLongArray(N);
            long ans = 0;
            long prefix = 0;
            int pointer1,pointer2;
            pointer1 = pointer2 = 0;
            long preOr = 0;
            while(pointer1<N){
                preOr |= A[pointer1];
                if(preOr>=K){
                    long posOr = 0;
                    int j = pointer1;
                    while((posOr|A[j]) <K){                        
                        posOr |= A[j];
                        j--;
                    }
                    preOr = posOr;
                    ans = ans + (j-pointer2+1l)*1l*(N-pointer1);
                    pointer2 = j+1;
                }
                pointer1++;
            }
            System.out.println(ans);
        }
    }
}
