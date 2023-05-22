package com.bookscrabble.server;


import java.util.HashSet;

public class CacheManager {
    private int size;
    private HashSet<String> cache;
    private CacheReplacementPolicy crp;
    public CacheManager(int size,CacheReplacementPolicy crp){
        this.size=size;
        this.crp=crp;
        cache = new HashSet<String>();
    }
    public boolean query(String word){return cache.contains(word);}
    public void add(String word){
        if (cache.size()>=size){
            String wordToRemove=crp.remove();
            cache.remove(wordToRemove);
        }
        crp.add(word);
        cache.add(word);
    }

}
