package test;
import java.util.Random;

public class Bag {
    private int[] letter_count;
    private Tile[] tiles;
    private static Bag bag_instance=null;
    private Bag() {
        this.letter_count = new int[]{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        this.tiles=new Tile[26];
        int [] scores = new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        for (int i=0;i<26;i++){
            this.tiles[i] = new Tile((char)('A'+i),scores[i]);
        }
    }
    public static Bag getBag(){
       if(bag_instance==null)
           bag_instance = new Bag();
       return bag_instance;

    }
    public int[] getLetter_count() {
        return letter_count;
    }

    public void setLetter_count(int[] letter_count) {
        this.letter_count = letter_count;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public Tile getRand(){
        int count=0;
        //check if the bag is empty
        for (int i:letter_count){
            if(i!=0)
                count++;
        }
        if(count==0)
            return null;

        Random r = new Random();
        int rint = r.nextInt(25);

        while(this.letter_count[rint]==0){
            rint = r.nextInt(25);
        }
        this.letter_count[rint]--;
        return this.tiles[rint];
    }

    public Tile getTile(char c){
        int index=0;
        for(Tile t:this.tiles){
            if(t.letter ==c && this.letter_count[index]>0) {
                this.letter_count[index]--;
                return t;
            }
            index++;
        }
        return null;
    }
    public void put(Tile tile) {
        int[] max_count = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        if(this.letter_count[(int)tile.letter-(int)'A']<(max_count[(int)tile.letter-(int)'A']))
        {
            this.letter_count[(int)tile.letter] +=1;
        }
    }
    public int size(){
        int size =0;
        for (int i:this.letter_count) {
           size+=i;
        }
        return size;
    }
    public int[] getQuantities(){
        int[] temp =  new int[26];
        for(int i=0;i<26;i++)
        {
           temp[i] = this.letter_count[i];
        }
        return temp;
    }

}
