package src.leetcode;

public class FruitsIntoBasket {
    public int totalFruit(int[] fruits) {
        int fruit1=fruits[0],count1=1,fruit2=-1,count2=0,n=fruits.length;
        for(int i=1;i<n;i++){
            if(fruits[i]!=fruit1){
                fruit2 = fruits[i];
                count2 = 0;
                break;
            }
        }
        if(fruit2==-1){
            return n;
        }
        int t=1;
        int ans = 1;
        for(int i=1;i<n;i++){
            if(fruits[i]==fruit1){
                count1++;
            }
            else if(fruits[i]==fruit2){
                count2++;
            }
            else{
                if(fruits[i-1]==fruit1){
                    fruit2 = fruits[i];
                    count2 = 1;
                    count1 = t;
                }
                else{
                    fruit1 = fruits[i];
                    count1 = 1;
                    count2 = t;
                }
            }
            if(fruits[i]==fruits[i-1]){
                t++;
            }
            else{
                t = 1;
            }
            ans = Math.max(ans,count1+count2);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new FruitsIntoBasket().totalFruit(new int[]{1,2,1}));
        System.out.println(new FruitsIntoBasket().totalFruit(new int[]{0,1,2,2}));
        System.out.println(new FruitsIntoBasket().totalFruit(new int[]{1,0,1,4,1,4,1,2,3}));
    }
}
