
package dictionarycoco;

public class DictionaryComandLine {
    public void dictionaryBasic(){
        DictionaryManagement dm = new DictionaryManagement();
        dm.insertFromFile();
        dm.showAllWorlds();
        dm.dictionaryLookUp();
        dm.dictionarySearcher();
        dm.addword();
        //dm.dictionaryExportToFile("output");
    }
    public static void main(String [] args){
        DictionaryComandLine dcl = new DictionaryComandLine();
        dcl.dictionaryBasic();
    }
}
