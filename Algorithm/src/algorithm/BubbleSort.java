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
public class BubbleSort {
    private static int index = 0;
    private static boolean swap = false;    
   // private static Pixel[] Pixels;
    public static boolean sort(Pixel[]  pixels)
    {
        if(index+1 >= pixels.length)
        {
            if(swap)
            {
                index =0;
                swap = false;
            }else
            {
                return true;
            }
        }
            
        
        Pixel p1 = pixels[index];
        Pixel p2 = pixels[index + 1];
        if(p1.id > p2.id)
        {
            pixels[index]=p2;
            pixels[index+1]=p1;
            swap = true;
        }
        
        index++;
        return false;
    }
}
