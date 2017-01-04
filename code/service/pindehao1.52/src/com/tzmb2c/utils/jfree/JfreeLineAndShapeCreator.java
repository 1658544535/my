package com.tzmb2c.utils.jfree;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

/**
 * 
 * @Title:折线图创建类
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月8日
 * @Version:1.1.0
 */
public class JfreeLineAndShapeCreator {

  /**
   * 生成折线图
   */
  public JFreeChart makeLineAndShapeChart(double[][] data, String[] rowKeys, String[] columnKeys,
      String title, String x_title, String y_title) {
    CategoryDataset dataset = getBarData(data, rowKeys, columnKeys);
    return createTimeXYChar(title, x_title, y_title, dataset);

  }

  // 柱状图,折线图 数据集
  private CategoryDataset getBarData(double[][] data, String[] rowKeys, String[] columnKeys) {
    return DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);

  }

  /**
   * 折线图
   * 
   * @param chartTitle
   * @param x
   * @param y
   * @param xyDataset
   * @param charName
   * @return
   */
  private JFreeChart createTimeXYChar(String chartTitle, String x, String y,
      CategoryDataset xyDataset) {

    JFreeChart chart =
        ChartFactory.createLineChart(chartTitle, x, y, xyDataset, PlotOrientation.VERTICAL, true,
            true, false);

    chart.setTextAntiAlias(false);
    chart.setBackgroundPaint(Color.WHITE);
    // 设置标题的字体，重新设置title
    Font font = new Font("宋体", Font.BOLD, 25);
    TextTitle title = new TextTitle(chartTitle);
    title.setFont(font);
    chart.setTitle(title);
    // 设置面板字体
    Font labelFont = new Font("宋体", Font.TRUETYPE_FONT, 12);

    chart.setBackgroundPaint(Color.WHITE);

    CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
    // x轴 // 分类轴网格是否可见
    categoryplot.setDomainGridlinesVisible(true);
    // y轴 //数据轴网格是否可见
    categoryplot.setRangeGridlinesVisible(true);

    categoryplot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩

    categoryplot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩

    categoryplot.setBackgroundPaint(Color.lightGray);

    // 设置轴和面板之间的距离
    // categoryplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));

    CategoryAxis domainAxis = categoryplot.getDomainAxis();

    domainAxis.setLabelFont(labelFont);// 轴标题

    domainAxis.setTickLabelFont(labelFont);// 轴数值

    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的
    // Lable
    // 45度倾斜
    // 设置距离图片左端距离
    domainAxis.setLowerMargin(0.0);
    // 设置距离图片右端距离
    domainAxis.setUpperMargin(0.0);

    NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
    numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    numberaxis.setAutoRangeIncludesZero(true);

    // 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
    LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();

    lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见

    lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见

    // 显示折点数据
    // lineandshaperenderer.setBaseItemLabelGenerator(new
    // StandardCategoryItemLabelGenerator());
    // lineandshaperenderer.setBaseItemLabelsVisible(true);

    // 将报表保存为png文件
    // ChartUtilities.writeChartAsPNG(fos_jpg, chart, 500, 510);

    return chart;
  }

}
