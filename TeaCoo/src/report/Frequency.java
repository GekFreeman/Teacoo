package report;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.swing.JPanel;


import org.jfree.chart.*;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.PeriodAxisLabelInfo;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.*;

public class Frequency extends ApplicationFrame
{
	public static int avenum;
	public static int numstoday;

    public Frequency(String s,String UserID)
    {
        super(s);
        XYDataset xydataset = createDataset(UserID);
        JFreeChart jfreechart = createChart(xydataset);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        chartpanel.setMouseZoomable(true, false);
        setContentPane(chartpanel);
    }

    private static JFreeChart createChart(XYDataset xydataset)
    {
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("", "Date", "Numbers per DAY", xydataset, true, true, false);
        jfreechart.setBackgroundPaint(null);
        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(true);
        org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot.getRenderer();
//        if(xyitemrenderer instanceof XYLineAndShapeRenderer)
//        {
//            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyitemrenderer;
//            xylineandshaperenderer.setShapesVisible(true);
//            xylineandshaperenderer.setShapesFilled(true);
//            xylineandshaperenderer.setBaseItemLabelsVisible(true);
//        }
        
        //折线图显示各节点数据的代码
        xyitemrenderer.setBaseItemLabelsVisible(true);
        xyitemrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.BASELINE_CENTER));;
        xyitemrenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyitemrenderer.setBaseItemLabelFont(new Font("Dialog", 1, 14));
        xyplot.setRenderer(xyitemrenderer);

        PeriodAxis periodaxis = new PeriodAxis("Date");
        periodaxis.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));
        periodaxis.setAutoRangeTimePeriodClass(org.jfree.data.time.Day.class);
        PeriodAxisLabelInfo aperiodaxislabelinfo[] = new PeriodAxisLabelInfo[3];
        aperiodaxislabelinfo[0] = new PeriodAxisLabelInfo(org.jfree.data.time.Day.class, new SimpleDateFormat("d"));
        aperiodaxislabelinfo[1] = new PeriodAxisLabelInfo(org.jfree.data.time.Month.class, new SimpleDateFormat("MMM"), new RectangleInsets(2D, 2D, 2D, 2D), new Font("SansSerif", 1, 10), Color.blue, false, new BasicStroke(0.0F), Color.lightGray);
        aperiodaxislabelinfo[2] = new PeriodAxisLabelInfo(org.jfree.data.time.Year.class, new SimpleDateFormat("yyyy"));
        periodaxis.setLabelInfo(aperiodaxislabelinfo);
        xyplot.setDomainAxis(periodaxis);
        return jfreechart;
    }

    private static XYDataset createDataset(String UserID)
    {
    	int[] a =new int[3];
    	int[] b =new int[5];
    	a=DateNow.DatePrint();
    	b=Query.query_time(UserID);
        TimeSeries timeseries = new TimeSeries("The Numbers per Day");
        for(int i=0;i<5;i++) {
        timeseries.add(new Day(a[2]-i, a[1], a[0]), b[i]);
        }
        avenum=(b[1]+b[2]+b[3]+b[4])/4;
        numstoday=b[0];
        TimeZone timezone = TimeZone.getTimeZone("Pacific/Auckland");
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timezone);
        timeseriescollection.addSeries(timeseries);
        timeseriescollection.setXPosition(TimePeriodAnchor.MIDDLE);
        return timeseriescollection;
    }

    public  ChartPanel createDemoPanel(String UserID)
    {
        JFreeChart jfreechart = createChart(createDataset(UserID));
        return new ChartPanel(jfreechart);
    }

//    public static void main(String args[])
//    {
//        Time_1 periodaxisdemo2 = new Time_1("Period Axis Demo 2");
//        periodaxisdemo2.pack();
//        RefineryUtilities.centerFrameOnScreen(periodaxisdemo2);
//        periodaxisdemo2.setVisible(true);
//    }
}

