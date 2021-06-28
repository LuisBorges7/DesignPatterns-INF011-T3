/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfdocument;

import interfaces.IDocument;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 *
 * @author luisborges
 */
public final class PDFDocument implements IDocument{
    
    static String supportedextension = "pdf";
    public static ArrayList<Icon> images = new ArrayList<>();
    JPanel jpanel = new JPanel();
    public int currentPage = 0;   
        
    @Override
    public void open(File file) {
        PDDocument document =null;
        BufferedImage image = null;

        try {
            document = Loader.loadPDF(new File(file.toString()));
        } catch (IOException ex) {
            Logger.getLogger(PDFDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!document.isEncrypted()) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int i = 0; i < document.getNumberOfPages(); i++){

                try {
                    image = pdfRenderer.renderImage(i);
                } catch (IOException ex) {
                    Logger.getLogger(PDFDocument.class.getName()).log(Level.SEVERE, null, ex);
                }
                    images.add((Icon) new ImageIcon(image));
                }
            }else{
            JOptionPane.showMessageDialog(jpanel,"O documento está encriptado, não sendo possível abrí-lo.");
            return;
        }
        JOptionPane.showMessageDialog(jpanel,"Carregamento concluído.");
        getEditor(currentPage);
    }

    @Override
    public JLabel getEditor(int currentPage) {
        ImageIcon imageIcon = (ImageIcon) images.get(currentPage);
        JLabel label = new JLabel(imageIcon);       
        return label;
    }

    @Override
    public String supportedExtension() {
        return supportedextension;
    }

    @Override
    public int getNumPages() {
        return images.size()-1;
    }
    
}
