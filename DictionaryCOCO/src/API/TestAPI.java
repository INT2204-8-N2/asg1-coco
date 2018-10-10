/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;


/**
 *
 * @author Admin
 */
public class TestAPI {
    public static void main(String[] args) throws IOException {
        SynthesiserV2 test = new SynthesiserV2();
        try {
            System.out.println(GoogleTranslate.translate("vi", "i love you"));
            test.speak("We are one");
        } catch (JavaLayerException ex) {
            Logger.getLogger(TestAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
