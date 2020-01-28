package pl.com.store.webstore.photoService;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class DirectoryChooser {

    private  static String directoryPath;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Wybierz katalog do zapisania pliku : ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                directoryPath= jfc.getSelectedFile().getAbsolutePath();
            }
        }
    }

    public static String getDirectoryPath() {
        return directoryPath;
    }
}
