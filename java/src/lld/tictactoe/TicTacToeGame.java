package src.lld.tictactoe;

public class TicTacToeGame {
    public static void main(String[] args) {
        //Initialize a new board
        Board board = new Board(3);
        //Initialize players
        Player player1 = new Player("P1",Symbol.O);
        Player player2 = new Player("p2",Symbol.X);
        //Create a game

        Game ticTacToe = new Game(board,player1,player2);
        ticTacToe.play();
        ticTacToe.announceWinner();
    }
}
