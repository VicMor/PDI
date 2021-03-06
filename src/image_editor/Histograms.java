/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_editor;

import java.awt.BorderLayout;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author vicman
 */
public class Histograms extends javax.swing.JFrame {

    /**
     * Creates new form Histograms
     */
    public Histograms() {
        initComponents();
        this.setLocationRelativeTo(Histograms.this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rHistogramPanel = new javax.swing.JPanel();
        gHistogramPanel = new javax.swing.JPanel();
        bHistogramPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 650));

        rHistogramPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rHistogramPanel.setLayout(new java.awt.BorderLayout());

        gHistogramPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        gHistogramPanel.setLayout(new java.awt.BorderLayout());

        bHistogramPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bHistogramPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rHistogramPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addComponent(gHistogramPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bHistogramPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rHistogramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gHistogramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bHistogramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void drawHistograms(XYSeries red_data, XYSeries green_data, XYSeries blue_data){
        XYSeriesCollection red_serie = new XYSeriesCollection(red_data);
        XYSeriesCollection green_serie = new XYSeriesCollection(green_data);
        XYSeriesCollection blue_serie = new XYSeriesCollection(blue_data);
        //JFreeChart histogram_chart = ChartFactory.createBarChart("R-Histogram", "Color", "Frequency", red_dataset, PlotOrientation.VERTICAL, false, true, false);       
        JFreeChart red_chart = ChartFactory.createXYLineChart("Red channel", "Color intensity", "Frequency", red_serie, PlotOrientation.VERTICAL, false, true, false);
        JFreeChart green_chart = ChartFactory.createXYLineChart("Green channel", "Color intensity", "Frequency", green_serie, PlotOrientation.VERTICAL, false, true, false);
        JFreeChart blue_chart = ChartFactory.createXYLineChart("Blue channel", "Color intensity", "Frequency", blue_serie, PlotOrientation.VERTICAL, false, true, false);
        XYPlot plot = red_chart.getXYPlot();
        XYPlot g_plot = green_chart.getXYPlot();
        XYPlot b_plot = blue_chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();        
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesShapesVisible(0,false);
        plot.setRenderer(renderer);
        XYLineAndShapeRenderer g_renderer = new XYLineAndShapeRenderer();        
        g_renderer.setSeriesPaint(0, Color.GREEN);
        g_renderer.setSeriesShapesVisible(0,false);
        g_plot.setRenderer(g_renderer);
        XYLineAndShapeRenderer b_renderer = new XYLineAndShapeRenderer();        
        b_renderer.setSeriesPaint(0, Color.BLUE);
        b_renderer.setSeriesShapesVisible(0,false);
        b_plot.setRenderer(b_renderer);
                
        ChartPanel red_panel = new ChartPanel(red_chart);
        ChartPanel green_panel = new ChartPanel(green_chart);
        ChartPanel blue_panel = new ChartPanel(blue_chart);
        rHistogramPanel.removeAll();
        rHistogramPanel.add(red_panel, BorderLayout.CENTER);
        rHistogramPanel.validate();        
        gHistogramPanel.removeAll();
        gHistogramPanel.add(green_panel, BorderLayout.CENTER);
        gHistogramPanel.validate();        
        bHistogramPanel.removeAll();
        bHistogramPanel.add(blue_panel, BorderLayout.CENTER);
        bHistogramPanel.validate();        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Histograms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Histograms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Histograms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Histograms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Histograms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bHistogramPanel;
    private javax.swing.JPanel gHistogramPanel;
    private javax.swing.JPanel rHistogramPanel;
    // End of variables declaration//GEN-END:variables
}
