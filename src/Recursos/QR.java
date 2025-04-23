/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;

import java.io.ByteArrayOutputStream;
import javax.swing.ImageIcon;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


/**
 *
 * @author mateo
 */
public class QR {
    public ImageIcon generar(String t){
        ByteArrayOutputStream aos = QRCode.from(t).to(ImageType.PNG).stream();
        ImageIcon icon = new ImageIcon(aos.toByteArray());
        return icon;
    }
}
