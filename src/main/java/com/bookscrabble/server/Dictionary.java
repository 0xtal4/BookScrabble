package com.bookscrabble.server;


import java.io.*;

public class Dictionary {
    private CacheManager cacheExists;
    private CacheManager cacheNotExists;
    private BloomFilter bloom;
    private String[] fileNames;

    public Dictionary(String... fileNames) {
        this.fileNames=fileNames;
        bloom = new BloomFilter(256, "SHA1", "MD5");
        CacheReplacementPolicy crpLRU = new LRU();
        CacheReplacementPolicy crpLFU = new LFU();
        cacheExists = new CacheManager(400, crpLRU);
        cacheNotExists = new CacheManager(100, crpLFU);

        String line;
        for(String file:fileNames){
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while(true){
                try {
                    if (!((line = reader.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String[] words = line.split("\\s");
                for (String fileWord: words){
                    cacheExists.add(fileWord);
                    bloom.add(fileWord);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public boolean query(String word) {
        if (cacheExists.query(word))
            return true;
        else if (cacheNotExists.query(word))
            return false;
        else {
            if (bloom.contains(word)) {
                cacheExists.add(word);
                return true;
            } else {
                cacheNotExists.add(word);
                return false;
            }
        }
    }
    public boolean challenge(String word){
        boolean flag;
        try {
            return IOSearcher.search(word,fileNames);
        } catch (Exception e) {
            return false;
        }
    }


}
