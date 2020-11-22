/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

/**
 *
 * @author ASUS
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
public class Display extends Canvas implements Runnable {
    private JFrame frame;
    private Thread thread;
    private static String tittle = "Sorting Visualizer";
    private int width = 700;//temporary//
    private int height = 600;//temporary//
    private boolean running = false,sort = false;
    private Picture picture;
    
    private BufferedImage image;
    private int[] pixels;
    private Pixel[] myPixels;
    
    public Display()
    {
        frame = new JFrame();
        picture = new Picture("/Image/ritchie.jpeg");
        
        width = picture.getWidth();
        height = picture.getHeight();
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        myPixels = new Pixel[width * height];
        
        Dimension size = new Dimension(500,(int)(500/width * height));
         this.setPreferredSize(size);//canvas class calling and setpreferred method under in it// 
         
         initpixels();
    }
    private void initpixels()
    {
        for(int i =0;i<pixels.length;i++)
        {
            myPixels[i]=new Pixel(picture.getPixels()[i],i);
        }
        
        randomizePixels();
    }
    private void randomizePixels()// randomize pixels
    {
        ArrayList<Pixel> pixelList  = new ArrayList<Pixel>();
        
        for(int i =0;i<myPixels.length;i++)
        {
            pixelList.add(myPixels[i]);
        }
        Collections.shuffle(pixelList);//shuffling the image
        
        for(int i =0;i<myPixels.length;i++)
        {
            myPixels[i]= pixelList.get(i);
        }
        
    }
    public static void main(String[] args) {
        Display display = new Display();
        display.frame.setTitle(tittle);
        display.frame.add(display); 
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setVisible(true);
        
        display.start();
    }
    public synchronized void start()
    {
        running = true;
        thread = new Thread(this,"Display");
        thread.start();
    }
     public synchronized void stop()
    {
        running = false ;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
     public void run() {
   
        long lasttime = System.nanoTime();///last time//
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 500;// dividing by 100 means
                                               //we want to run our 
                                               //programme 100frames a second incase its temporary//
            double delta = 0;                   //now its 5times faster
       int update =0;
       int frames = 0;
       while(running){
           long now = System.nanoTime();//current yime//
           delta += (now - lasttime)/ns;//how much time has passed//
           lasttime = now;
           while(delta>=1)
           {
               update();
               update++;
               delta--;
           }
           render();// screen updator//
           frames++; //frame per second//
           if(System.currentTimeMillis()-timer >= 1000)
           {
               timer += 1000;
               frame.setTitle(tittle +" | "  + frames + " fps ");//number of frames displayed in per second///
               frames = 0;
               update = 0;
               
           }
             
       }
    }
     public void render()
     {
         BufferStrategy bs = this.getBufferStrategy();//how i'am going to display item
        if(bs== null)                                   //to the scren like how many windows
        {                                                //ahead of time we are preparing//
           this.createBufferStrategy(3);
            return ;
        } 
        for(int i=0;i< pixels.length;i++)
            pixels[i]= myPixels[i].color;  //integrity of actual image //
        Graphics g = bs.getDrawGraphics();           
        g.drawImage(image,0,0,getWidth(),getHeight(),null);//black color//
        g.dispose();
        bs.show();
     }
     public void update()
     {
         if(!sort) 
         sort = MasterSort.sort(myPixels, SortingMethod.BubbleSort,70000);
     }
}
