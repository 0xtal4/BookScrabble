package mileStone2;


public class CacheManager {

    public interface CacheReplacementPolicy{
        void add(String word);
        String remove();
    }
	

}
