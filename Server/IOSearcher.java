package src;


import java.io.BufferedReader;
import java.io.FileReader;

public class IOSearcher {

    static public boolean search(String word,String...files) throws Exception{
        String line;
        for(String file:files){
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                String[] words = line.split("\\s");
                for (String fileWord: words){
                    if (word.equals(fileWord))
                        return true;
                }
            }
            reader.close();
        }
        return false;
    }
}
