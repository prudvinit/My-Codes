package src.google2;

import java.util.Arrays;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ps[][] = new int[m][n];
        ps[0][0] = matrix[0][0]-48;
        for(int j=1;j<n;j++){
            ps[0][j] = ps[0][j-1]+(matrix[0][j]-48);
        }
        for(int i=1;i<m;i++){
            ps[i][0] = ps[i-1][0]+matrix[i][0]-48;
            for(int j=1;j<n;j++){
                ps[i][j] = ps[i-1][j]+ps[i][j-1]-ps[i-1][j-1]+matrix[i][j]-48;
            }
        }
//        for(int i=0;i<m;i++){
//            System.out.println(Arrays.toString(ps[i]));
//        }
        int ans = 0;
        for(int I=0;I<m;I++){
            for(int J=0;J<n;J++){
//                System.out.println("Starting from "+I+" "+J);
                for(int i=I;i<m;i++){
                    for(int j=J;j<n;j++){
                        int area = ps[i][j]-(I>=1?ps[I-1][j]:0)-(J>=1?ps[i][J-1]:0)+(I>=1&&J>=1?ps[I-1][J-1]:0);
//                        System.out.println("Area is "+area+" "+(i-I+1)*(j-J+1));

                        if(area==(i-I+1)*(j-J+1)){
                            ans = Math.max(ans,area);
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new MaximalRectangle().maximalRectangle(new char[][]{"10100".toCharArray(),"10111".toCharArray(),"11111".toCharArray(),"10010".toCharArray()
        }));
    }
}
