package src;

import java.util.ArrayList;
import java.util.Arrays;

class PrePost{
    public static void dfs(int pre[], int ps, int pe, int in[], int is, int ie){
        if(ps>pe){
            return;
        }
        //Find the root in inorder traversal
        int root = is;
        for(int i=is;i<=ie;i++){
            if(in[i]==pre[ps]){
                root = i;
                break;
            }
        }
        int lsize = root-is;
        int rsize = ie-root;
        if(lsize>0){
            dfs(pre,ps+1,ps+lsize,in,is,root-1);
        }
        if(rsize>0){
            dfs(pre,ps+lsize+1,pe,in,root+1,ie);
        }
        System.out.println(in[root]);
    }

    public static void main(String[] args) {
        int pre[] = {1,2,4,5,3,6,7};
        int in[] = {4,2,5,1,6,3,7};
        dfs(pre,0,6,in,0,6);
    }
}
