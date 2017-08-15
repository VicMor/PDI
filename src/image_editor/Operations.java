/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_editor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author vicman
 */
public class Operations {    
    double[] red_histogram = new double[256];        
    double[] green_histogram = new double[256];
    double[] blue_histogram = new double[256];
    public BufferedImage modified_image = null;
    int image_size;
    
    public BufferedImage openImage(File ima_file){    
    BufferedImage original_image = null;
        try {
            original_image = ImageIO.read(ima_file);
        } catch (IOException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    return original_image;
    }
    
    public String saveImage(File f_image, BufferedImage final_image){
        String answer = null;
        try {
            ImageIO.write(final_image, "jpg", f_image);
            answer = "Image saved correctly.";
        } catch (Exception e) {
        }
        return answer;
    }
    
    public Object[] getHistogram(BufferedImage image){           
        red_histogram = new double[256];
        green_histogram = new double[256];
        blue_histogram = new double[256];
        int w_size = image.getWidth();
        int h_size = image.getHeight();
        image_size = w_size * h_size;
            for (int i = 0; i < w_size; i++) {
                for (int j = 0; j < h_size; j++) {
                    int pixel_value = image.getRGB(i, j);
                    Color c = new Color(pixel_value);
                    int valR = c.getRed();
                    red_histogram[valR] += 1;
                    int valG = c.getGreen();
                    green_histogram[valG] += 1;
                    int valB = c.getBlue();    
                    blue_histogram[valB] += 1;
                }
            }
        System.out.println("Histogram obtained");
        return new Object[]{red_histogram, green_histogram, blue_histogram}; 
    }
    
    public BufferedImage negativeOperation(BufferedImage image){                
        int w_size = image.getWidth();
        int h_size = image.getHeight();        
        BufferedImage negativeImage = new BufferedImage(w_size, h_size, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < w_size; i++) {
            for (int j = 0; j < h_size; j++) {
                int pixel_value = image.getRGB(i, j);
                    Color c = new Color(pixel_value);
                    int valR = c.getRed();                                        
                    int valG = c.getGreen();                    
                    int valB = c.getBlue(); 
                    int newR = 255 - valR;
                    int newG = 255 - valG;
                    int newB = 255 - valB;
                    int new_color = (newR << 16) | (newG << 8) | newB;
                    negativeImage.setRGB(i, j, new_color);                   
            }
        }        
        return negativeImage;
    }
    
    public BufferedImage gammaCorrection(BufferedImage image, double gamma){
        int w_size = image.getWidth();
        int h_size = image.getHeight();        
        BufferedImage gammaImage = new BufferedImage(w_size, h_size, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < w_size; i++) {
            for (int j = 0; j < h_size; j++) {
                int pixel_value = image.getRGB(i, j);
                    Color c = new Color(pixel_value);
                    double valR = c.getRed();                                        
                    double valG = c.getGreen();                    
                    double valB = c.getBlue(); 
                   // System.out.println("R:"+valR+" G:"+valG+" B:"+valB);
                    double temp_r = valR/255;
                    double temp_g = valG/255;
                    double temp_b = valB/255;
                    //System.out.println("temp_r:"+temp_r);
                    double red = 255 * Math.pow(temp_r, gamma);
                    double green = 255 * Math.pow(temp_g, gamma);
                    double blue = 255 * Math.pow(temp_b, gamma);
                    //System.out.println("Red:"+red);
                    int newR = (int) red;
                    int newG = (int) green;
                    int newB = (int) blue;
                    //System.out.println("Red entero:"+newR);
                    int new_color = (newR << 16) | (newG << 8) | newB;
                    gammaImage.setRGB(i, j, new_color);                   
            }
        }
        modified_image = gammaImage;
        return gammaImage;
    }
    
    public XYSeriesCollection drawHistogram(){                        
        double sumatoria = 0;
        double sumatoria_g = 0;
        double sumatoria_b = 0;
        XYSeries red_dataset = new XYSeries("R-channel");
        XYSeries green_dataset = new XYSeries("G-channel");
        XYSeries blue_dataset = new XYSeries("B-channel");        
        for (int i = 0; i < red_histogram.length; i++) {
            double normalized_value_r = red_histogram[i]/image_size;            
            double normalized_value_g = green_histogram[i]/image_size;            
            double normalized_value_b = blue_histogram[i]/image_size;            
            red_dataset.add(i, normalized_value_r);
            green_dataset.add(i, normalized_value_g);
            blue_dataset.add(i, normalized_value_b);
            sumatoria += normalized_value_r;
            sumatoria_g += normalized_value_g;
            sumatoria_b += normalized_value_b;
            //System.out.println("i:"+i+" valor: "+red_histogram[i]);
        }
        //System.out.println("Sumatoria_r:"+sumatoria+"\nSumatoria_g:"+sumatoria_g+"\nSumatoria_b:"+sumatoria_b);
        XYSeriesCollection r_dataset = new XYSeriesCollection();
        r_dataset.addSeries(red_dataset);
        r_dataset.addSeries(green_dataset);
        r_dataset.addSeries(blue_dataset);
        return r_dataset;        
    }
}
