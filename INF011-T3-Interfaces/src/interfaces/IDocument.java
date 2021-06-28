/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.File;
import javax.swing.JLabel;

/**
 *
 * @author luisborges
 */
public interface IDocument {
    void open(File file);
    JLabel getEditor(int currentpage);
    String supportedExtension();
    int getNumPages();
}
