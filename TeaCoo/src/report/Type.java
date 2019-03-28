package report;

import java.awt.*;

import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Type extends ApplicationFrame
{
	public static double[] Type=new double[3];

    public Type(String s,String UserID)
    {
        super(s);
        JPanel jpanel = createDemoPanel(UserID);
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static CategoryDataset createDataset(String UserID)
    {
    	double[] a=new double[3];
    	a=Query.query_type(UserID);
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        String s = "D-Rate*100";
        for (int i=0;i<3;i++) {
        defaultcategorydataset.addValue(a[i], s, "Type"+(i+1));
        Type[i]=a[i];
        }
        return defaultcategorydataset;
    }

    private static JFreeChart createChart(CategoryDataset categorydataset)
    {
        JFreeChart jfreechart = ChartFactory.createBarChart("", "Types",null, categorydataset, PlotOrientation.HORIZONTAL, true, true, false);
//        jfreechart.addSubtitle(new TextTitle("Source: http://www.homeoffice.gov.uk/rds/pdfs2/r188.pdf", new Font("Dialog", 2, 10)));
        jfreechart.setBackgroundPaint(null);
        CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
        categoryplot.setBackgroundPaint(Color.lightGray);
        categoryplot.setRangeGridlinePaint(Color.white);
        categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
        barrenderer.setBaseItemLabelsVisible(true);
        barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryMargin(0.0D);
        categoryaxis.setUpperMargin(0.02D);
        categoryaxis.setLowerMargin(0.02D);
        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setUpperMargin(0.10000000000000001D);
        return jfreechart;
    }

    public  ChartPanel createDemoPanel(String UserID)
    {
        JFreeChart jfreechart = createChart(createDataset(UserID));
        return new ChartPanel(jfreechart,true);
    }

//    public static void main(String args[])
//    {
//        BarChartDemo5 barchartdemo5 = new BarChartDemo5("Bar Chart Demo 5");
//        barchartdemo5.pack();
//        RefineryUtilities.centerFrameOnScreen(barchartdemo5);
//        barchartdemo5.setVisible(true);
//    }
}

