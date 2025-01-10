package src.lld.tictactoe;

public enum Symbol {
    X('X',-1),
    E(' ',0),
    O('O',1);
    private char symbol;
    private int  value;
    Symbol(char symbol, int value){
        this.symbol = symbol;
        this.value = value;
    }

    int value(){
        return value;
    }

}
