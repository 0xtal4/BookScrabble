package com.bookscrabble.client.model;

import java.util.ArrayList;

public class Board {
    private static Board board_instance;
    private Tile[][] board;
    private char[][] bonuses;
    public static Board getBoard() {
        if (board_instance == null)
            board_instance = new Board();
        return board_instance;
    }
    private Board(){
        board = new Tile[15][15];
        for (int i=0;i<15;i++){
            for (int j=0;j<15;j++){
                board[i][j]=null;
            }
        }
        bonuses = new char[][]{
                {'r', ' ', ' ', 'a', ' ', ' ', ' ', 'r', ' ', ' ', ' ', 'a', ' ', ' ', 'r'},
                {' ', 'y', ' ', ' ', ' ', 'b', ' ', ' ', ' ', 'b', ' ', ' ', ' ', 'y', ' '},
                {' ', ' ', 'y', ' ', ' ', ' ', 'a', ' ', 'a', ' ', ' ', ' ', 'y', ' ', ' '},
                {'a', ' ', ' ', 'y', ' ', ' ', ' ', 'a', ' ', ' ', ' ', 'y', ' ', ' ', 'a'},
                {' ', ' ', ' ', ' ', 'y', ' ', ' ', ' ', ' ', ' ', 'y', ' ', ' ', ' ', ' '},
                {' ', 'b', ' ', ' ', ' ', 'b', ' ', ' ', ' ', 'b', ' ', ' ', ' ', 'b', ' '},
                {' ', ' ', 'a', ' ', ' ', ' ', 'a', ' ', 'a', ' ', ' ', ' ', 'a', ' ', ' '},
                {'r', ' ', ' ', 'a', ' ', ' ', ' ', 's', ' ', ' ', ' ', 'a', ' ', ' ', 'r'},
                {' ', ' ', 'a', ' ', ' ', ' ', 'a', ' ', 'a', ' ', ' ', ' ', 'a', ' ', ' '},
                {' ', 'b', ' ', ' ', ' ', 'b', ' ', ' ', ' ', 'b', ' ', ' ', ' ', 'b', ' '},
                {' ', ' ', ' ', ' ', 'y', ' ', ' ', ' ', ' ', ' ', 'y', ' ', ' ', ' ', ' '},
                {'a', ' ', ' ', 'y', ' ', ' ', ' ', 'a', ' ', ' ', ' ', 'y', ' ', ' ', 'a'},
                {' ', ' ', 'y', ' ', ' ', ' ', 'a', ' ', 'a', ' ', ' ', ' ', 'y', ' ', ' '},
                {' ', 'y', ' ', ' ', ' ', 'b', ' ', ' ', ' ', 'b', ' ', ' ', ' ', 'y', ' '},
                {'r', ' ', ' ', 'a', ' ', ' ', ' ', 'r', ' ', ' ', ' ', 'r', ' ', ' ', 'r'}
        };


    }
    /*public Tile[] getTiles(String word)
    {
        char[] letters = word.toCharArray();
        Tile[] tiles = new Tile[letters.length];
        for(int i=0;i<letters.length; i++)
            tiles[i] = Tile.Bag.getBag().getTile(letters[i]);
        return tiles;
    }*/

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
        // if first word isn't on star
        if (board[7][7] == null) {
            if ((word.getRow()!=7)||(word.getCol() > 7) || (word.getCol()+word.getLength())<8)
                return false;
        }
        return true;
    }
    private boolean isOverride(Word word,Tile [][] board){
        int iterator=0;
        for(int j=word.getCol();j<(word.getCol()+word.getLength());j++){
            if (word.getTiles()[iterator] == null && board[word.getRow()][j]!=null)
                continue;
            else if (board[word.getRow()][j]!= null
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

    private boolean isNeighbourV2(Word word, Tile[][] board){
        try {
            if (board[word.getRow()][word.getCol()-1]!=null)
                return true;} finally {System.out.println("error");}

        try{
            if (board[word.getRow()][word.getLength()+ word.getCol()]!=null)
                return true;
        } finally {System.out.println("error");}

        try{
            if (neighbourUp(word, board))
                return true;
        } finally {System.out.println("error");}

        try{
            if(neighbourDown(word, board))
                return true;
        }finally {System.out.println("error");}
        System.out.println("okkkkk");
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
    private boolean isEmpty(Tile[][] board){
        for (int i=0;i<14;i++)
        {
            for (int j=0;j<14;j++){
                if (board[i][j]!=null)
                    return false;
            }
        }
        return true;
    }
    private boolean boardLegalHorizontal(Word word,Tile[][] board) {
        if (isEmpty(board)) {
            if (!firstWordStared(word, board))
                return false;
            if(word.getRow()<0 || word.getCol()<0 || (word.getLength()+word.getCol()-1)>14)
                return false;

            if (isOverride(word,board))
                return false;
            return true;
        }
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
        if (word.isVertical()) {
            Word temp = new Word(word.getTiles(), word.getCol(), word.getRow(),false);
            return boardLegalHorizontal(temp, rotateCounterClock(board));
        }
        else
            return boardLegalHorizontal(word,board);
    }
    private boolean dictionaryLegal(Word word){return true;}
    private Word tilesList2Word(ArrayList<Tile> tiles,int row, int col,boolean isVert){
        Tile[] arr = new Tile[tiles.size()];
        int iter=0;
        for (Tile t:tiles){
            arr[iter] = t;
            iter++;
        }
        Word word = new Word(arr,row,col,isVert);
        return word;
    }
    private ArrayList<Word> extractWordsFromLine (Tile[] line,int row,boolean isVert){
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        ArrayList<Word> words = new ArrayList<Word>();
        int col;
        for(int i=0;i<15;i++)
        {
           if (line[i]!=null){
               int j=i;
               col=i;
               while(line[j]!=null && j<15){
                    tiles.add(line[j]);
                    j++;
               }
               if (tiles.size()>1) {
                   words.add(tilesList2Word(tiles, row, col, isVert));
               }
               tiles.clear();
               i=j;
           }
        }
        return words;
    }
    private ArrayList<Word> allHorizontalWords(Tile[][] board,boolean isVert){
        ArrayList<Word> oneLetter = new ArrayList<Word>();
        ArrayList<Word> words = new ArrayList<Word>();
        for (int i=0;i<15;i++)
        {
            words.addAll(extractWordsFromLine(board[i],i,isVert));

        }
//        for(Word w:words){
//            if (w.getLength()==1)
//                oneLetter.add(w);
//        }
//        words.removeAll(oneLetter);
//        return words;
        if (isVert)
            for(Word w:words)
                changeRowCol(w);
        return words;
    }
   public ArrayList<Word> getWords(Word word) {
       ArrayList<Word> currentWords = new ArrayList<Word>();
       currentWords.addAll(allHorizontalWords(this.board, false));
       currentWords.addAll(allHorizontalWords(rotateCounterClock(this.board), true));
       //cloning the board
       Tile[][] tempBoard = new Tile[15][15];
       for (int i = 0; i < 15; i++) {
           for (int j = 0; j < 15; j++) {
               tempBoard[i][j] = this.board[i][j];
           }
       }
       //adds new word to a temp board
       if (word.isVertical()) {
           for (int i = word.getRow(); i < word.getLength() + word.getRow(); i++) {
               if (word.getTiles()[i - word.getRow()]!=null)
                   tempBoard[i][word.getCol()] = word.getTiles()[i - word.getRow()];
           }
       } else {
           for (int j = word.getCol(); j < word.getLength() + word.getCol(); j++) {
               if (word.getTiles()[j - word.getCol()]!=null)
                   tempBoard[word.getRow()][j] = word.getTiles()[j - word.getCol()];
           }
       }
       //extract all new words
       ArrayList<Word> newWords = new ArrayList<Word>();
       newWords.addAll(allHorizontalWords(tempBoard, false));
       newWords.addAll(allHorizontalWords(rotateCounterClock(tempBoard), true));


       newWords.removeAll(currentWords);
       return newWords;
   }
   private void changeRowCol(Word w){
        int temp;
        temp = w.getCol();
        w.setCol(w.getRow());
        w.setRow(temp);
   }
   //private ArrayList<Word> removeAll(ArrayList<Word> src,ArrayList<Word> del){
//
   //}

   public int getScore(Word word){
        int score=0;
        int mul=1;
        for(Tile t:word.getTiles()){
            score+=t.score;
        }
        if (word.isVertical()){
            for (int i= word.getRow();i< word.getRow()+ word.getLength();i++){
                switch (bonuses[i][word.getCol()]){
                    case 's':
                        mul=2;
                        bonuses[i][word.getCol()]=' ';
                        break;
                    case 'r':
                        mul =3;
                        break;
                    case 'y':
                        mul=2;
                        break;
                    case 'a':
                        score+=word.getTiles()[i-word.getRow()].score;
                        break;
                    case 'b':
                        score+=word.getTiles()[i- word.getRow()].score*2;
                        break;
                }
            }
        }else {
            for (int j = word.getCol(); j < word.getCol() + word.getLength(); j++) {
                switch (bonuses[word.getRow()][j]) {
                    case 's':
                        mul = 2;
                        bonuses[word.getRow()][j] = ' ';
                        break;
                    case 'r':
                        mul = 3;
                        break;
                    case 'y':
                        mul = 2;
                        break;
                    case 'a':
                        score += word.getTiles()[j - word.getCol()].score;
                        break;
                    case 'b':
                        score += word.getTiles()[j - word.getCol()].score * 2;
                        break;
                }
            }
        }
        return score*mul;
   }
   private void printBoard(){
       System.out.println("-------------------------------------------------");
        for (int i=0;i<15;i++){
            for (int j=0;j<15;j++){
                if (board[i][j]==null)
                    System.out.print("_ ");
                else
                    System.out.print(board[i][j].letter);
            }
            System.out.print('\n');
        }
       System.out.println("-------------------------------------------------");
   }
   public int tryPlaceWord(Word word){
        ArrayList<Word> newWords = new ArrayList<Word>();
        int score=0;
        if (!boardLegal(word))
            return 0;
        newWords = getWords(word);
        for (Word w:newWords) {
            if (!dictionaryLegal(w))
                return 0;
        }
       if (word.isVertical()) {
           for (int i = word.getRow(); i < word.getRow() + word.getLength(); i++) {
               if (word.getTiles()[i - word.getRow()] != null) {
                   board[i][word.getCol()] = word.getTiles()[i - word.getRow()];
               }
           }
       } else {
           for (int j=word.getCol();j<word.getLength()+word.getCol();j++){
               board[word.getRow()][j] = word.getTiles()[j-word.getCol()];
           }
       }

        for (Word w:newWords){
            score+=getScore(w);
        }

        
        return score;

   }
   public char[][] getBonuses(){
        return bonuses;
   }
}