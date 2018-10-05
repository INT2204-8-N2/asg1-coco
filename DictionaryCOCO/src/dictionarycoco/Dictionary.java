

package dictionarycoco;

import java.util.HashMap;

public class Dictionary {
    HashMap<String,String> words =new HashMap<String,String>();
}
class Word {
    String spelling,explain;
    int id;
    public Word(int id,String spe,String exp){
        spelling =spe;
        explain= exp;
    }
    public Word(){
        
    }
}
