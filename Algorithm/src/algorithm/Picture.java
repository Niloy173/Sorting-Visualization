/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ASUS
 */
public class Picture {
     private int[] pixels;
    private BufferedImage Image;
    private String path;
    
    public Picture(String path)
    {
        this.path = path;
        load();
    }
    private void load()
    {
        try {
            Image = ImageIO.read(Picture.class.getResource(path));
            int w = Image.getWidth();
            int h = Image.getHeight();
            pixels = new int[w * h];
            Image.getRGB(0,0,w,h,pixels,0, w);
        } catch (IOException ex) {
            Logger.getLogger(Picture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int[] getPixels()
    {
        return pixels;
    }
    public int getWidth()
    {
        return Image.getWidth();
    }
    public int getHeight()
    {
        return Image.getHeight(); 
    }
    
}
