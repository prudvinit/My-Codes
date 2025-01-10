package src.lld.tictactoe;

import java.util.*;

//Board represents the Game board and corresponding symbols
public class Board implements BoardFeatures {

    private int n;
    private Symbol board[][];
    private List<String> moves;
    private Map<String,Integer> scores;
    private int totalMoves;


    Board(int n) {
        this.n = n;
        moves = new ArrayList();
        scores = new HashMap<>();
        totalMoves = 0;
    }

    @Override
    public void init() {
        board = new Symbol[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], Symbol.E);
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < n & y < n && board[x][y].equals(Symbol.E);
    }

    @Override
    public boolean makeMove(BoardGamePlayer p) {
        String s[] = p.makeChoice().split(" ");
        int x = Integer.parseInt(s[0]);
        int y = Integer.parseInt(s[1]);

        if(totalMoves==n*n){
            System.out.println("No more moves allowed on the board. Game finished");
            return false;
        }

        while (!isValid(x, y)) {
            System.out.println("Invalid move. Please make valid move");
            s = p.makeChoice().split(" ");
            x = Integer.parseInt(s[0]);
            y = Integer.parseInt(s[1]);
        }
        board[x][y] = p.symbol();
        moves.add(p.name() + " " + x + " " + y);
        totalMoves++;
        scores.put("ROW-"+x,scores.getOrDefault("ROW-"+x,0)+p.symbol().value());
        scores.put("COL-"+y,scores.getOrDefault("COL-"+x,0)+p.symbol().value());
        if(x==y){
            scores.put("D1",scores.getOrDefault("D1",0)+p.symbol().value());
        }
        if(x+y==n){
            scores.put("D2",scores.getOrDefault("D2",0)+p.symbol().value());
        }
        int xScore = scores.getOrDefault("ROW-"+x,0);
        int yScore = scores.getOrDefault("COL-"+x,0);
        int d1Score = x==y?scores.getOrDefault("D1",0):0;
        int d2Score = x+y==n?scores.getOrDefault("D2",0):0;
        int v1 = Symbol.O.value()*n;
        int v2 = Symbol.X.value()*n;
        if(xScore==v1||xScore==v2||yScore==v1||yScore==v2||d1Score==v1||d1Score==v2||d2Score==v1||d2Score==v2||totalMoves==n*n){
            System.out.println("Game finished");
            totalMoves = n*n;
            return false;
        }
        return true;
    }

    public String lastMove(){
        return moves.get(moves.size()-1);
    }

    @Override
    public void display(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}
