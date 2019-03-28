package report;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.ui.TextAnchor;

public class Difficulty extends ApplicationFrame {
	
	public static int Diff;
	public static boolean a=false;

	public Difficulty(String s, String UserID) {
		super(s);
		JPanel jpanel = createDemoPanel(UserID);
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);

	}

	private static CategoryDataset createDataset(String UserID) {
		double[] b = new double[5];
		b = Query.query_diff(UserID);
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		for (int i = 0; i < 5; i++) {
			defaultcategorydataset.addValue(b[i], "DIff" + (i+1), "");
			if (!a) {
			if (b[i]<80) {
				Diff=i+1;
				a=true;
			}
			else if (b[i]>=80) {
				if (i==0) Diff=1;
				else if (b[i]<b[Diff-1]) Diff=i+1;
			}
			}
		
		}
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset) {
		JFreeChart jfreechart = ChartFactory.createBarChart3D(
				"", null, "D-rate*100", categorydataset,
				PlotOrientation.VERTICAL, true, true, false);
		jfreechart.setBackgroundPaint(null);
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(0.39269908169872414D));
		CategoryItemRenderer categoryitemrenderer = categoryplot.getRenderer();
		categoryitemrenderer.setBaseItemLabelsVisible(true);
		BarRenderer barrenderer = (BarRenderer) categoryitemrenderer;
		barrenderer.setItemMargin(0.20000000000000001D);
		barrenderer.setBaseItemLabelsVisible(true);
		barrenderer
		.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));

		return jfreechart;
	}

	public ChartPanel createDemoPanel(String UserID) {
		JFreeChart jfreechart = createChart(createDataset(UserID));
		return new ChartPanel(jfreechart, true);
	}

}
