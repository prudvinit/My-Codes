package src.lld.tictactoe;

public class Game implements GameFlow {

    Board board;
    Player p1,p2;
    boolean p1Turn;
    Game(Board board,Player p1, Player p2){
        this.board = board;
        this.p1 = p1;
        this.p2 = p2;
        this.init();
    }

    @Override
    public void init() {
        board.init();
        p1Turn = true;
    }

    @Override
    public void play() {
        boolean result = true;
        while(result){
            result = p1Turn?board.makeMove(p1):board.makeMove(p2);
            p1Turn = !p1Turn;
        }
        System.out.println("Game concluded");
        board.display();
    }

    @Override
    public void announceWinner() {
        String winner = board.lastMove().split(" ")[0];
        System.out.println("Winner is "+winner);
    }
}
