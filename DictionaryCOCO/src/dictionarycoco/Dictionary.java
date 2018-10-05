

package dictionarycoco;

import java.util.HashMap;

public class Dictionary {
    HashMap<String,Word> words =new HashMap<String,Word>();
}
class Word {
    String spelling,explain;
    int id;
    public Word(int id,String a,String b){
        spelling =a;
        explain= b;
    }
    public Word(){
        
    }
}
