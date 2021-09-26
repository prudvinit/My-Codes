//https://leetcode.com/problems/cat-and-mouse-ii/
//INCOMPLETE
package src.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CatAndMouse {

    char[][] maze;
    int catJump, mouseJump;
    Map<String,Boolean> dp = new HashMap();
    Map<String,Boolean> visit = new HashMap();

    void set(int cpos[], int mpos[], boolean result){
        dp.put(cpos[0]+" "+cpos[1]+" "+mpos[0]+" "+mpos[1],result);
    }

    Boolean get(int cpos[], int mpos[]){
        return dp.getOrDefault(cpos[0]+" "+cpos[1]+" "+mpos[0]+" "+mpos[1],null);
    }

    void visitPos(int cpos[], int mpos[]){
        visit.put(cpos[0]+" "+cpos[1]+" "+mpos[0]+" "+mpos[1],true);
    }

    void unVisitPos(int cpos[], int mpos[]){
        visit.put(cpos[0]+" "+cpos[1]+" "+mpos[0]+" "+mpos[1],false);
    }

    boolean isVisited(int cpos[], int mpos[]){
        return visit.getOrDefault(cpos[0]+" "+cpos[1]+" "+mpos[0]+" "+mpos[1],false);
    }



    private boolean isValid(int pos[]){
        int x=pos[0],y=pos[1];
//        System.out.println("Validate "+x+" "+y+" , "+maze.length+" "+maze[0].length);
        if(x<0||x>=maze.length||y<0||y>=maze[0].length){
//            System.out.println("OOR");
            return false;
        }
        if(maze[x][y]=='#'){
            return false;
        }
//        System.out.println("VALID");
        return true;
    }

    public char get(int pos[]){
        return maze[pos[0]][pos[1]];
    }

    private boolean isJumpValid(int oldPos[], int newPos[]){
        if(!isValid(newPos)){
//            System.out.println(Arrays.toString(newPos)+" is invalidd ");
            return false;
        }
        if(oldPos[0]!=newPos[0]) {
            int dir = (oldPos[0] - newPos[0]) < 0 ? 1 : -1;
            for (int i = oldPos[0]; i <= newPos[0]; i = i + dir) {
                if (!isValid(new int[]{i,newPos[1]})){
                    return false;
                }
            }
        }
        if(oldPos[1]!=newPos[1]) {
            int dir = (oldPos[1] - newPos[1]) < 0 ? 1 : -1;
            for (int i = oldPos[1]; i <= newPos[1]; i = i + dir) {
                if (!isValid(new int[]{newPos[0],i})){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean canMouseWin(int[] cpos, int[] mpos,int turn, int mouseTurns){
        System.out.println("Cat : "+ Arrays.toString(cpos)+" , Mouse : "+Arrays.toString(mpos)+", Turn "+(turn==0?"Mouse":"Cat"));
        if(!isValid(cpos)||!isValid(mpos)){
            return false;
        }
        if(get(mpos)=='F'){
            return true;
        }
        if(get(cpos)=='F'){
            return false;
        }
        if(cpos[0]==mpos[0]&&cpos[1]==mpos[1]){
            return false;
        }
        if(mouseTurns>2000){
            return false;
        }
        if(get(cpos,mpos)!=null){
            return get(cpos,mpos);
        }
        //This is mouse jump
        boolean canMouseWin = true;
        if(turn==0){
            for(int i=-mouseJump;i<=mouseJump;i++){
                if(i==0){
                    continue;
                }
                int newmpos[] = {mpos[0]+i,mpos[1]};
//                System.out.println("Mouse new pos "+Arrays.toString(newmpos));
                if(isJumpValid(mpos,newmpos)){
//                    System.out.println("Valid jump");
                    visitPos(cpos,newmpos);
                    boolean t = canMouseWin(cpos,newmpos,1-turn,mouseTurns+1);
                    unVisitPos(cpos,newmpos);
                    if(t) {
                        set(cpos,mpos,t);
                        return true;
                    }
                }
            }
            for(int j=-mouseJump;j<=mouseJump;j++){
                if(j==0)continue;
                int newmpos[] = {mpos[0],mpos[1]+j};
//                System.out.println("New Mouse pos "+Arrays.toString(newmpos));
                if(isJumpValid(mpos,newmpos)){
//                    System.out.println("Valid jump to right");
                    visitPos(cpos,newmpos);
                    boolean t = canMouseWin(cpos,newmpos,1-turn,mouseTurns+1);
                    unVisitPos(cpos,newmpos);
                    if(t) {
                        set(cpos,mpos,t);
                        return true;
                    }
                }
            }
            //Mouse makes no turn
            visitPos(cpos,mpos);
            boolean ans = canMouseWin(cpos,mpos,1-turn,mouseTurns+1);
            unVisitPos(cpos,mpos);
            set(cpos,mpos,ans);
        }
        //This is a cat turn
        else{
            for(int i=-catJump;i<=catJump;i++){
                if(i==0)continue;
                int newcpos[] = {cpos[0]+i,cpos[1]};
                if(isJumpValid(cpos,newcpos)){
                    boolean t = canMouseWin(newcpos,mpos,1-turn,mouseTurns+1);
                    if(!t) {
                        set(cpos,mpos,t);
                        return false;
                    }
                }
            }
            for(int j=-catJump;j<=catJump;j++){
                if(j==0)continue;
                int newcpos[] = {cpos[0],cpos[1]+j};
                if(isJumpValid(cpos,newcpos)){
                    boolean t = canMouseWin(newcpos,mpos,1-turn,mouseTurns+1);
                    if(!t) {
                        set(cpos,mpos,t);
                        return false;
                    }
                }
            }
            //Cat makes no turn
            boolean t = canMouseWin(cpos,mpos,1-turn,mouseTurns+1);
            if(t) {
                set(cpos,mpos,t);
                return true;
            }
        }
        set(cpos,mpos,false);
        return false;
    }

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        char[][] maze = new char[grid.length][grid[0].length()];

        this.maze = maze;
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        int cpos[] = {0,0};
        int mpos[] = {0,0};
        for(int i=0;i<grid.length;i++){
            maze[i] = grid[i].toCharArray();
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<maze[i].length;j++){
                if(maze[i][j]=='C'){
                    cpos = new int[]{i,j};
                }
                if(maze[i][j]=='M'){
                    mpos = new int[]{i,j};
                }
            }
        }
        boolean result = canMouseWin(cpos,mpos,0,0);
        System.out.println(dp);
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(new CatAndMouse().canMouseWin(new String[]{"####F","#C...","M...."},1,2));
        System.out.println(new CatAndMouse().canMouseWin(new String[]{"M.C...F"},1,4));
//        System.out.println(new CatAndMouse().canMouseWin(new String[]{"C...#","...#F","....#","M...."},2,5));
//        System.out.println(new CatAndMouse().canMouseWin(new String[]{".M...","..#..","#..#.","C#.#.","...#F"},3,1));
    }
}
