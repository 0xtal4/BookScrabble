package com.bookscrabble.server;

import java.util.*;

public class LFU implements CacheReplacementPolicy{
    Queue<Integer> freqsQueue;
    HashMap<String,Integer> wordFreqMap ;
    HashMap<Integer,Queue<String>> freqQueueMap;
    public LFU(){
        freqsQueue = new PriorityQueue<>();
        wordFreqMap= new HashMap<String,Integer>();
        freqQueueMap = new HashMap<Integer,Queue<String>>();
    }
    public void add(String word){
        if(!wordFreqMap.containsKey(word)){
            wordFreqMap.put(word,1);
            if(freqQueueMap.containsKey(1))
                freqQueueMap.get(1).add(word);
            else {
                Queue<String> q = new LinkedList<String>();
                q.add(word);
                freqQueueMap.put(1,q);
                freqsQueue.add(1);
            }
        }
        else{
            int wordFreq = wordFreqMap.get((word));
            if(freqQueueMap.containsKey(wordFreq+1)) {
                freqQueueMap.get(wordFreq + 1).add(word);
                freqQueueMap.get(wordFreq).remove(word);
                if (freqQueueMap.get(wordFreq).isEmpty())
                    freqQueueMap.remove(wordFreq);
            }
            else{
                freqQueueMap.get(wordFreq).remove(word);
                Queue<String> q = new LinkedList<String>();
                q.add(word);
                freqQueueMap.put(wordFreq+1,q);
                freqQueueMap.get(wordFreq).remove(wordFreq);
                freqsQueue.add(wordFreq+1);
            }
            wordFreqMap.put(word,wordFreq+1);
            }
    }
    public String remove(){
        String word;
        if(!freqsQueue.isEmpty()){
            int freqToRemove = freqsQueue.poll();
            word = freqQueueMap.get(freqToRemove).poll();
            if(freqQueueMap.get(wordFreqMap.get(word)).isEmpty()) {
                freqQueueMap.remove(wordFreqMap.get(word));
                freqsQueue.remove(wordFreqMap.get(word));
            }
            wordFreqMap.remove(word);
        }
        else
            word=null;
        return word;
    }

}
