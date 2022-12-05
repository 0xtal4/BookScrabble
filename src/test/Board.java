package test;


public class Board {
    private static Board board_instance;
    private Tile[][] board;

    public static Board getBoard() {
        if (board_instance == null)
            board_instance = new Board();
        return board_instance;

    }

    public Tile[][] getTiles() {
        Tile[][] clone = new Tile[15][15];
        for (int i = 0; i < 15; i++) {[]
            for (int j = 0; j < 15; j++) {
                clone = this.board[i];
            }
        }
        return clone;
    }
    private boolean firstWordStared(Word word){
        // if first word isnt on star
        if (this.board[7][7] == null) {
            if ((word.getCol() > 7) || (word.getCol()+word.getLength())<8)
                return false;
        }
        return true;
    }
    private boolean boardLegalHorizontal(Word word) {
        if (!firstWordStared(word))
            return false;

        // if word is out of border
        if(word.getRow()<0 || word.getCol()<0 || (word.getLength()+word.getCol()-1)>14))
            return false;

        for(int j=word.getCol();j<(word.getCol()+word.getLength());j++){
             if (this.board[word.getRow()][])


        }

    }

    public boolean boardLegal(Word word) {
        //word isnt leaking from the board
    }
}