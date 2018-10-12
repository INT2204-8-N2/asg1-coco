package JFrameApplication;


import API.GSpeechDuplex;
import API.GSpeechResponseListener;
import API.GoogleResponse;
import API.GoogleTranslate;
import API.Microphone;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import net.sourceforge.javaflacencoder.FLACFileWriter;


public class Speechtotext extends JFrame implements GSpeechResponseListener {
     final Microphone mic = new Microphone(FLACFileWriter.FLAC);
     GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyAsgIKQ9U3O12D2FOZH5DSnHQTUeXTtL2g");
    public Speechtotext() {
        initComponents();
        init();
    }
     public void init(){
        duplex.setLanguage("en");
        stop.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        response = new javax.swing.JTextArea();
        record = new javax.swing.JButton();
        stop = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel2.setOpaque(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Speech to text");
        setLocation(new java.awt.Point(400, 150));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("You say: ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 464, -1));

        response.setEditable(false);
        response.setColumns(20);
        response.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        response.setLineWrap(true);
        response.setRows(5);
        jScrollPane1.setViewportView(response);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 59, 490, 190));

        record.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ghi.png"))); // NOI18N
        record.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordActionPerformed(evt);
            }
        });
        getContentPane().add(record, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, -1, -1));

        stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/stop.jpg"))); // NOI18N
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });
        getContentPane().add(stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, -1));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("English");
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("VietNamese");
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/traanslate_2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 40, 40));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 490, 200));

        jLabel3.setBackground(new java.awt.Color(255, 204, 153));
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected())
            duplex.setLanguage("en");
        else
            duplex.setLanguage("vi");
            
        new Thread(() -> {
					try {
						duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}).start();
				record.setEnabled(false);
				stop.setEnabled(true);
    abc();
    }//GEN-LAST:event_recordActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        // TODO add your handling code here:
        mic.close();
        duplex.stopSpeechRecognition();
        record.setEnabled(true);
        stop.setEnabled(false);
     
    }//GEN-LAST:event_stopActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String language ="";
        if(jRadioButton1.isSelected())
            language="vi";
        else
            language="en";
        try {
             // TODO add your handling code here:
             jTextArea1.setText(GoogleTranslate.translate( language,response.getText()));
         } catch (IOException ex) {
             Logger.getLogger(Speechtotext.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }//GEN-LAST:event_jButton1ActionPerformed
    public void abc(){
        duplex.addResponseListener(new GSpeechResponseListener() {
                String old_text = "";
                
                public void onResponse(GoogleResponse gr) {
                        String output = "";
                        output = gr.getResponse();
                        if (gr.getResponse() == null) {
                                this.old_text = response.getText();
                                if (this.old_text.contains("(")) {
                                        this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
                                }
                                this.old_text = ( response.getText() + "\n" );
                                this.old_text = this.old_text.replace(")", "").replace("( ", "");
                                response.setText(this.old_text);
                                return;
                        }
                        if (output.contains("(")) {
                                output = output.substring(0, output.indexOf('('));
                        }
                        if (!gr.getOtherPossibleResponses().isEmpty()) {
                                output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
                        }
                        response.setText("");
                        response.append(this.old_text);
                        response.append(output);
                }
        });
}

    @Override
    public void onResponse(GoogleResponse gr) {
            // TODO Auto-generated method stub

    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Speechtotext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Speechtotext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Speechtotext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Speechtotext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Speechtotext().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton record;
    private javax.swing.JTextArea response;
    private javax.swing.JButton stop;
    // End of variables declaration//GEN-END:variables

   

}
