/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author buidangthutra
 */
public class HelloAPI {
    public static void main(String[] args) {
        try {
            System.out.println(GoogleTranslate.translate("vi", "I a exo lover"));
        } catch (IOException ex) {
            Logger.getLogger(HelloAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
      
}
