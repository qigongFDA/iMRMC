import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Paint;
import java.awt.Color;
import java.awt.BasicStroke;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

public class PresencePlot extends JFrame {

	public PresencePlot(final String title, String xaxis, String yaxis,
			boolean[][] data) {
		super(title);
		DefaultXYDataset dataset = createDataset(data);
		JFreeChart chart = createChart(dataset, title, xaxis, yaxis);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}

	private DefaultXYDataset createDataset(boolean[][] data) {
		int t = 0;
		int f = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j]) {
					t++;
				} else {
					f++;
				}
			}
		}
		double[][] trueVals = new double[2][t];
		double[][] falseVals = new double[2][f];
		final DefaultXYDataset dataset = new DefaultXYDataset();
		int tCount = 0;
		int fCount = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j]) {
					trueVals[0][tCount] = j;
					trueVals[1][tCount] = i + 1;
					tCount++;
				} else {
					falseVals[0][fCount] = j;
					falseVals[1][fCount] = i + 1;
					fCount++;
				}
			}
		}
		dataset.addSeries("false", falseVals);
		dataset.addSeries("true", trueVals);
		return dataset;
	}

	private JFreeChart createChart(final DefaultXYDataset dataset,
			String title, String xaxis, String yaxis) {
		final JFreeChart chart = ChartFactory.createScatterPlot(title, xaxis,
				yaxis, dataset, PlotOrientation.VERTICAL, true, true, false);
		return chart;

	}
}
