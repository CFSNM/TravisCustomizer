package Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Cesar on 01/08/2017.
 */
public class ImagePanel extends JPanel {

    public String imagePath;
    Image image;

    public ImagePanel(String imagePath){
        this.imagePath = imagePath;
        image = new ImageIcon(imagePath).getImage();

    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(image != null){
            g.drawImage(image, 0, 0, getWidth(),getHeight(),this);

            setOpaque(false);
        }else{
            setOpaque(true);
        }
    }

}
