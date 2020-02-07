package pl.com.store.webstore.photoService;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
            String name = selectedFile.getName();
            int length = name.length();
            String newName=name.substring(0,length-3)+"png";
            File newFile = new File("C:\\workspace\\WebShop\\src\\main\\resources\\static\\img\\" + newName);
            try {
                BufferedImage selectedImage = ImageIO.read(selectedFile);
                ImageIO.write(selectedImage, "png", newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String absolutePath = newFile.getPath();
            String[] sp = absolutePath.split("\\\\");
            FileChooser.path = "\\" + sp[sp.length - 2] + "\\" + sp[sp.length - 1];
        }
        frame.setVisible(false);
    }

    public static String getPath() {
        return path;
    }
}

