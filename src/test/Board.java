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
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                clone[i][j] = this.board[i][j];
            }
        }
        return clone;
    }
    private boolean firstWordStared(Word word,Tile [][] board){
        // if first word isnt on star
        if (board[7][7] == null) {
            if ((word.getCol() > 7) || (word.getCol()+word.getLength())<8)
                return false;
        }
        return true;
    }
    private boolean isOverride(Word word,Tile [][] board){
        int iterator=0;
        for(int j=word.getCol();j<(word.getCol()+word.getLength());j++){
            if (board[word.getRow()][j]!= null
                    &&
                    !board[word.getRow()][j].equals(word.getTiles()[iterator]))
                return true;
            iterator++;
        }
       return false;
    }
    private boolean neighbourDown(Word word,Tile [][] board){

        for (int i=0;i< word.getLength();i++){
            if(board[word.getRow()+1][i+ word.getCol()]!=null)
                return true;
        }
        return false;
    }
    private boolean neighbourUp(Word word, Tile [][] board){
        for (int i=0;i< word.getLength();i++){
            if(board[word.getRow()-1][i+ word.getCol()]!=null)
                return true;
        }
        return false;
    }


    private boolean isNeighbour(Word word, Tile [][] board ){
        //top left
        if (word.getCol() ==0 && word.getRow()==0 && (word.getCol()+ word.getLength()-1)<14){
            if (board[word.getRow()][word.getCol()+ word.getLength()]!=null)
                return true;
            if (neighbourDown(word,board))
                return true;
            return false;
        }
        //top right
        if ((word.getCol()+ word.getLength()-1) ==14 && word.getRow()==0 && word.getCol()>0){
            if (board[word.getRow()][word.getCol()-1]!=null)
                return true;
            if (neighbourDown(word,board))
                return true;
            return false;
        }
        //buttom left
        if (word.getCol() ==0 && word.getRow()==14 && (word.getCol()+ word.getLength()-1)<14){
            if (board[word.getRow()][word.getCol()+ word.getLength()]!=null)
                return true;
            if (neighbourUp(word,board))
                return true;
            return false;
        }
        //buttom right
        if ((word.getCol()+ word.getLength()-1) ==14 && word.getRow()==14 && word.getCol()>0){
            if (board[word.getRow()][word.getCol()-1]!=null)
                return true;
            if (neighbourUp(word,board))
                return true;
            return false;
        }
        //left
        if(word.getCol()==0 && word.getRow()<14 && word.getRow()>0 && (word.getCol()+word.getLength()-1)<14)
        {
            if (board[word.getRow()][word.getCol()+ word.getLength()]!=null)
                return true;
            if (neighbourUp(word,board))
                return true;
            if (neighbourDown(word,board))
                return true;
            return false;
        }
        //right
        if(word.getCol()>0 && word.getRow()<14 && word.getRow()>0 && (word.getCol()+word.getLength()-1)==14)
        {
            if (board[word.getRow()][word.getCol()-1]!=null)
                return true;
            if (neighbourUp(word,board))
                return true;
            if (neighbourDown(word,board))
                return true;
            return false;
        }
        //up
        if(word.getCol()>0 && word.getRow()==0  && (word.getCol()+word.getLength()-1)<14)
        {
            if (board[word.getRow()][word.getCol()-1]!=null)
                return true;
            if (board[word.getRow()][word.getCol()+ word.getLength()]!=null)
                return true;
            if (neighbourDown(word,board))
                return true;
            return false;
        }
        //down
        if(word.getCol()>0 && word.getRow()==14  && (word.getCol()+word.getLength()-1)<14)
        {
            if (board[word.getRow()][word.getCol()-1]!=null)
                return true;
            if (board[word.getRow()][word.getCol()+ word.getLength()]!=null)
                return true;
            if (neighbourUp(word,board))
                return true;
            return false;
        }
        //all line down
        if(word.getCol()==0 && word.getRow()==14  && (word.getCol()+word.getLength()-1)==14) {
            if (neighbourUp(word,board))
                return true;

            return false;
        }
        //all line up
        if(word.getCol()==0 && word.getRow()==0  && (word.getCol()+word.getLength()-1)==14) {
            if (neighbourDown(word,board))
                return true;

            return false;
        }
       //all line
        if(word.getCol()==0 && word.getRow()>0 && word.getRow()<14  && (word.getCol()+word.getLength()-1)==14)
        {
            if (neighbourDown(word,board))
                return true;
            if (neighbourUp(word,board))
                return true;
            return false;
        }
        //otopy- in borders
        if (neighbourUp(word,board)||neighbourDown(word,board)||board[word.getRow()][word.getCol()-1]!=null||board[word.getRow()][word.getCol()+ word.getLength()]!=null)
            return true;
        else
            return false;
    }
    private boolean boardLegalHorizontal(Word word,Tile[][] board) {
        if (!firstWordStared(word,board))
            return false;

        // if word is out of border
        if(word.getRow()<0 || word.getCol()<0 || (word.getLength()+word.getCol()-1)>14)
            return false;

        if (isOverride(word,board))
            return false;

        if (!isNeighbour(word,board))
            return false;
        return true;
    }

    private Tile[][] rotateCounterClock(Tile [][] board){
        //returns a copy of 90 degrees left flipped board
        Tile[][] clone = new Tile[15][15];
        for (int i=0;i<15;i++)
        {
            for (int j=0;j<15;j++)
            {
                clone[14-j][i] = board[i][j];
            }
        }
        return clone;

    }

    public boolean boardLegal(Word word) {
        if (word.isVertical())
            return boardLegalHorizontal(word,rotateCounterClock(board));
        else
            return boardLegalHorizontal(word,board);
    }
}