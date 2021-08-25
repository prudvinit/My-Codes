package src;

import java.util.Scanner;

/**
 *
 * @author prudvi.maddala@oracle.com
 */
public class RMQ {

    /**
     * @param args the command line arguments
     */
    static int arr[],n;
    static int sparse[][];
    
    static int sparse2[][];
    
    static int log(int n){
        int ans = 0;
        while(n>1){
            n = n>>1;
            ans++;
        }
        return ans;
    }
    static int pow(int n){
        int ans = 1;
        while(n-->0){
            ans = ans<<1;
        }
        return ans;
    }
    static void buildSparseTable(){
        int c = log(n)+1;
        sparse = new int[n][c];
        sparse2 = new int[n][c];
        for(int i=0;i<n;i++){
            sparse[i][0] = i;
            sparse2[i][0] = i;
        }
        for(int j=1;j<c;j++){
            for(int i=0; i + pow(j) -1 <n;i++){
                sparse[i][j] = arr[sparse[i][j-1]] <= arr[sparse[i+pow(j)-pow(j-1)][j-1]] ? sparse[i][j-1] : sparse[i+pow(j-1)][j-1];         
            }
        }
    }
    static int getMin(int a, int b){
        int range = (b-a+1);
        int ln = log(range);
        int ans =  arr[sparse[a][ln]]<=arr[sparse[b-pow(ln)+1][ln]]?sparse[a][ln]:sparse[b-pow(ln)+1][ln];
        return ans;
    }
}
