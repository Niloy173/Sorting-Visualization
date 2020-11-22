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
public class MasterSort {
    private static  Pixel pixel;
    private static Pixel[] pixels;
    public static boolean sort(Pixel[] pixels, SortingMethod method,int iteration ) {
        for(int i =0;i<iteration ;i++)        ///iteration means how many swap we want to skip in this way
        if(method == SortingMethod.BubbleSort)BubbleSort.sort(pixels);
        //if(method == SortingMethod.InsertationSort)iInsertation.sort(pixels);
        //if(method == SortingMethod.selsectionSort)selsection.sort(pixels);
        
              /*thepurpose is same for all three of those*/
        
        return isSorted(pixels);
    }
    public static boolean isSorted(Pixel[] pixels)
    {
        for(int i =0;i<pixels.length;i++){
         pixel = pixels[i];
        if(i !=pixel.id)return false;
        
    }
        return true;
}

    
}
