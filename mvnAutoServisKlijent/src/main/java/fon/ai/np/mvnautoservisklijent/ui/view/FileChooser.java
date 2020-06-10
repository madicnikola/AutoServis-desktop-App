/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Nikola
 */
public class FileChooser {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        Date datum = new Date();
        System.out.println(sdf.format(datum));
        
        
//        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//        jfc.setDialogTitle("Choose a file to save your file: ");
//        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//        int returnValue = jfc.showSaveDialog(null);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            if (jfc.getSelectedFile().isFile()) {
//                System.out.println("You selected the directory: " + jfc.getSelectedFile());
//            }
//        }

    }

}
