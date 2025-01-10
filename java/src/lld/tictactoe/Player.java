package src.lld.tictactoe;

import java.util.Scanner;

public class Player implements BoardGamePlayer{
    String name;
    Symbol symbol;
    Scanner scanner;

    public Player(String name,Symbol symbol){
        this.name = name;
        this.symbol = symbol;
        scanner = new Scanner(System.in);
    }

    public String makeChoice(){
        System.out.println("Enter the choice for "+name);
        return scanner.nextLine();
    }

    @Override
    public Symbol symbol() {
        return symbol;
    }

    @Override
    public String name(){
        return this.name;
    }

}
