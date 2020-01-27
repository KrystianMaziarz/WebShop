package pl.com.store.webstore.photoService;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class DirectoryChooser {

    public File chooseDirectoryToSaveFile(){
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a directory to save your file: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                return jfc.getSelectedFile();
            }
        }
            return null;
    }
}
