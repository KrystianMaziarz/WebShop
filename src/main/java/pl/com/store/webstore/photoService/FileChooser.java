package pl.com.store.webstore.photoService;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class FileChooser {

    private static String path;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        FileFilter filter = new FileNameExtensionFilter("jpg,img,png,gif,jpeg", "jpg", "img", "png", "gif", "jpeg");

        SwingUtilities.invokeLater(() -> {
            frame.setType(Frame.Type.UTILITY);
            frame.setAlwaysOnTop(true);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.removeChoosableFileFilter(jfc.getAcceptAllFileFilter());
        jfc.setFileFilter(filter);
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            path = selectedFile.getAbsolutePath();
        }
        frame.setVisible(false);
    }

    public static String getPath() {
        return path;
    }
}

