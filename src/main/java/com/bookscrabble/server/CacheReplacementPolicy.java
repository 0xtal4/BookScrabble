package com.bookscrabble.server;

public interface CacheReplacementPolicy{
	void add(String word);
	String remove(); 
}
