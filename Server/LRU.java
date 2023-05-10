package src;
import java.util.LinkedList;
import java.util.Queue;


public class LRU implements CacheReplacementPolicy {
    Queue<String> queue ;
    public LRU(){
        queue= new LinkedList<String>();
    }
    public void add(String word){
        int flag=0;
        for (String w:queue){
            if(word==w)
                flag=1;
            }
        if(flag==1){
            queue.remove(word);
            queue.add(word);
        }
        else
            queue.add(word);
    }
    public String remove(){
        return queue.poll();
    }

}
