package gui;
import org.jfree.chart.ChartPanel;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BieuDo extends JPanel{

   public BieuDo( String applicationTitle , String chartTitle ) {
//      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Tháng","Doanh Thu Từng Tháng (triệu)",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);

      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 360 ) );
//      setContentPane( chartPanel );
      this.add(chartPanel);
   }

   private DefaultCategoryDataset createDataset( ) {

      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( 10 , "doanh thu" , " 1" );
      dataset.addValue( 30 , "doanh thu" , " 2" );
      dataset.addValue( 50 , "doanh thu" ,  " 3" );
      dataset.addValue( 45 , "doanh thu" , " 4" );
      dataset.addValue( 60 , "doanh thu" , " 5" );
      dataset.addValue( 80 , "doanh thu" , " 6" );
      dataset.addValue( 85 , "doanh thu" , " 7" );
      dataset.addValue( 100 , "doanh thu" , " 8" );
      dataset.addValue( 200 , "doanh thu" ,  " 9" );
      dataset.addValue( 115 , "doanh thu" , " 10" );
      dataset.addValue( 150 , "doanh thu" , " 11" );
      dataset.addValue( 170 , "doanh thu" , " 12" );
      return dataset;
   }
   
   public static void main( String[ ] args ) {
      BieuDo chart = new BieuDo(
         "Doanh Thu Theo Tháng" ,
         "Doanh Thu Theo Từng Tháng");

//      chart.pack( );
//      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
}