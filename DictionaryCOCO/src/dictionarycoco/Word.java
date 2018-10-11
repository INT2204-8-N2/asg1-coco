/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionarycoco;

/**
 *
 * @author Administrator
 */
public class Word {
    String spelling,explain;
    int id;
    public Word(int id,String spe,String exp){
        spelling =spe;
        explain= exp;
    }
    public Word(){
        
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
