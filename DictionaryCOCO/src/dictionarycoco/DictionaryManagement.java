/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionarycoco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DictionaryManagement {
    Dictionary dic = new Dictionary();

    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bạn muốn nhập bao nhiêu từ: ");
        int n = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            Word ex = new Word();
            System.out.print((i + 1) + " Tiếng Anh: ");
            ex.spelling = scan.nextLine();
            System.out.print("Nghĩa Tiếng Việt: ");
            ex.explain = scan.nextLine();
            ex.id=i+1;
            dic.words.put(ex.spelling,ex.explain);
        }
    }

    public void insertFromFile() {
        File file = new File("dictionaries.txt");
       
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            int i = 0;
            String line = "";
            for (i = 0; (line = br.readLine()) != null; i++) {
                Word ex = new Word();
                String[] a = line.split("\\s", 2);
                ex.spelling = a[0];
                ex.explain = a[1];
                dic.words.put(ex.spelling,ex);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            
    }

    public void showAllWorlds() {
        System.out.println( "Englih" + "\t\t\t" + "VietNamese");
        dic.words.forEach((eng,viet)->{
            
            System.out.printf("%-15s\t%-15s\n" , eng, viet.explain);
        });
       
    }

    public void dictionaryLookUp() {
        Scanner s = new Scanner(System.in);
        System.out.println("Find the word: ");
        String eng = s.nextLine().toLowerCase();
        dic.words.forEach((key,value)->{
            if (eng.equals(key)) {
                System.out.println("It means: " + value);
                return;
            }
        });
        if(!dic.words.containsKey(eng))
        System.out.println("Sorry, We can not found");

    }

    public void addword() {
        System.out.println("Do you wanna add any Word??? Yes or No ");
        Scanner scan = new Scanner(System.in);
        String result=scan.nextLine();
        if(result.equals("N")||result.equals("n"))
            return;
        else{
            Word word=new Word();
            System.out.println("Spelling: ");
            word.spelling=scan.nextLine();
            System.out.println("Explain: ");
            word.explain=scan.nextLine();
            dic.words.put(word.spelling,word);
            System.out.println("Add sucecss!!!");
        }
    }

    public void delete(String spelling) {
        dic.words.remove(spelling);
    }

    public void edit(String spelling) {
        Scanner scan = new Scanner(System.in);
        dic.words.forEach((key,value)->{
            if(key.equals(spelling)){
                Word w = new Word();
                System.out.println("new spelling: ");
                key = scan.nextLine();
                System.out.println("new explain: ");
                value.explain= scan.nextLine();
                
            }
        });
        System.out.println("Success!!!");
    }
    public void dictionaryExportToFile(String name){
        File file = new File(name+".txt");
        try{
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            dic.words.forEach((key,value)->{
                try {
                    bw.write(key+" "+value+"\n");
                } catch (IOException ex) {
                    Logger.getLogger(DictionaryManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            bw.close();
        }
        catch (IOException e){
            System.out.println("Can't create file");
        }
    }
    public void dictionarySearcher(){
        System.out.println("Find :");
        Scanner scan= new Scanner(System.in);
        String w=scan.nextLine().toLowerCase();
        dic.words.forEach((key,value)->{
            if(w.length()<=key.length()){
                String a=key.toLowerCase().substring(0,w.length());
                if(a.equals(w))
                    System.out.println(key+" "+value);
            }
        });
    }
}
