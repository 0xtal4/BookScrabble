package test;


public class Board {
    private static Board board_instance;
    private Tile[][] board;

    public static Board getBoard(){
        if(board_instance==null)
            board_instance = new Board();
        return board_instance;

    }
    public Tile[][] getTiles(){
        Tile[][] clone = new Tile[15][15];
        for(int i =0;i<15;i++){
            for (int j =0;j<15;j++) {
                clone = this.board[i];
            }
        }
        return clone;
    }
    public boolean boardLegal(Word word){
        
    }

}