/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import javax.swing.*;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhuang
 */
public class MainInterface extends javax.swing.JFrame {

    private String file = "anime_list.txt";
    private ArrayList<String> animes = new ArrayList<String>();
    private FolderList folders = new FolderList() ;

    /**
     * Creates new form MainInterface
     */
    public MainInterface() throws IOException {

        initComponents();

        try {

            BufferedReader in = new BufferedReader(new FileReader(file));

            while (in.ready()) {
                String s = in.readLine();
                String ss = s.substring(1, s.length() - 1);
                //System.out.println(ss);
                animes.add(ss);
            }
            in.close();

        } catch (Exception e) {

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        runButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        unsortedFolderList = new javax.swing.JList();
        createDirSpinter = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("Manga Sorter"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                run(evt);
            }
        });

        jButton1.setText("Add Folders Where Unsorted Files Are (Required)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseWhereUnsortedFilesAre(evt);
            }
        });

        unsortedFolderList.setModel(new DefaultListModel());
        unsortedFolderList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        unsortedFolderList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                unsortedFolderListKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(unsortedFolderList);

        createDirSpinter.setPreferredSize(new java.awt.Dimension(1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(createDirSpinter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(createDirSpinter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * a method for file choosing
     */
    private File lastTimeChoice = null;   //a function-only local vairable for chooseFile

    private String chooseFile() {
        //Create a file chooser
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.setApproveButtonText("choose");

        chooser.setCurrentDirectory(lastTimeChoice);

        //In response to a button click:
        int returnVal = chooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //System.out.println("You chose to open this file: " + chooser.getSelectedFile());
            lastTimeChoice = chooser.getSelectedFile().getParentFile();
            return chooser.getSelectedFile().getPath();
        } else {
            return null;
        }
    }

    /*
     * the most important method of this class
     * read folder and then do the matching
     */
    private void run(java.awt.event.ActionEvent evt)//GEN-FIRST:event_run
    {//GEN-HEADEREND:event_run

        this.runButton.setEnabled(false);
        //this.runButton.setText("Running...");
        
       this.folders.table.clear();
        
        //get unsorted folders
        try {
            Object[] tempUnsortedArr = getDefaultListModel(unsortedFolderList).toArray();
            String[] unsortedPathArr = Arrays.copyOf(tempUnsortedArr, tempUnsortedArr.length, String[].class);

            for (String path : unsortedPathArr) {
                folders.addFolder(path);
            }

        } catch (Exception e) {

            e.printStackTrace();
            System.err.println(e.getStackTrace() + " during scanning unsorted files");
            JOptionPane.showMessageDialog(this, e + " during scanning unsorted files");
            this.runButton.setEnabled(true);
            //this.runButton.setText("Run");
            return;
        }

        StringBuilder mvStr = new StringBuilder();

        //run the matching algo
        try {

            for (Entry<String, File> entry : folders.table.entrySet()) {
                String key = entry.getKey();
                File value = entry.getValue();
                
               // System.out.println(key);

                for (String title : animes) {
                    
                    //System.out.println(key+"  "+title);
                    
                    if (key.contains(title)) {
                        
                        String destDir = "D:\\_MUSIC\\_Sorted\\"+title;
                        
                        mvStr.append("mkdir \"").append(destDir).append("\" \n");
                        mvStr.append("move \"").append(value.getPath()).append("\" \"" ).append(destDir).append("\\").append(key).append("\"\n\n");
                        
                        break;
                    }
                }
            }

            //System.out.println(mvStr.toString());

            //write result into text files
            PrintWriter out;

            String saveFolder = System.getProperty("user.dir");

            out = new PrintWriter(saveFolder + "\\" + "mv_command.txt");
            out.print(mvStr.toString());
            out.close();

            java.awt.Desktop.getDesktop().open(new File(saveFolder));

        } catch (Exception e) {
            System.err.println(e + "during calculation");
            JOptionPane.showMessageDialog(this, e + "during calculation");
        } finally {
            this.runButton.setEnabled(true);
            //this.runButton.setText("Run");
        }
    }//GEN-LAST:event_run

    /*
     * choose folder for unsorted list
     */
    private void chooseWhereUnsortedFilesAre(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chooseWhereUnsortedFilesAre
    {//GEN-HEADEREND:event_chooseWhereUnsortedFilesAre
        String file = chooseFile();
        getDefaultListModel(unsortedFolderList).addElement(file);
    }//GEN-LAST:event_chooseWhereUnsortedFilesAre

    /*
     * choose folder for sorted list
     */
    /*
     * called when program closing
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    /*
     *  delete list item from unsortedFolderList
     */
    private void unsortedFolderListKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_unsortedFolderListKeyReleased
    {//GEN-HEADEREND:event_unsortedFolderListKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int index = unsortedFolderList.getSelectedIndex();
            getDefaultListModel(unsortedFolderList).removeElementAt(index);
        }
    }//GEN-LAST:event_unsortedFolderListKeyReleased

    /**
     * a simple utility to get the defaultListModel of JList
     *
     * @param list
     * @return DefaultListModel
     */
    private DefaultListModel getDefaultListModel(JList list) {
        return (DefaultListModel) list.getModel();
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainInterface().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner createDirSpinter;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton runButton;
    private javax.swing.JList unsortedFolderList;
    // End of variables declaration//GEN-END:variables
}