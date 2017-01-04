package com.tzmb2c.utils.jfree;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

/**
 * 
 * @Title:柱状图创建类
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月8日
 * @Version:1.1.0
 */
public class JfreeBarCreator {
  /**
   * 生成柱状图
   */
  public JFreeChart makeBarChart(double[][] data, String[] rowKeys, String[] columnKeys,
      String x_title, String y_title, String title) {
    CategoryDataset dataset = getBarData(data, rowKeys, columnKeys);
    return createBarChart(dataset, x_title, y_title, title);
  }

  // 柱状图,折线图 数据集
  public CategoryDataset getBarData(double[][] data, String[] rowKeys, String[] columnKeys) {
    return DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);

  }

  /**
   * 柱状图
   * 
   * @param dataset 数据集
   * @param xName x轴的说明（如种类，时间等）
   * @param yName y轴的说明（如速度，时间等）
   * @param chartTitle 图标题
   * @param charName 生成图片的名字
   * @return
   */
  public JFreeChart createBarChart(CategoryDataset dataset, String xName, String yName,
      String chartTitle) {
    JFreeChart chart = ChartFactory.createBarChart(chartTitle, // 图表标题
        xName, // 目录轴的显示标签
        yName, // 数值轴的显示标签
        dataset, // 数据集
        PlotOrientation.VERTICAL, // 图表方向：水平、垂直
        true, // 是否显示图例(对于简单的柱状图必须是false)
        false, // 是否生成工具
        false // 是否生成URL链接
        );
    Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
    /*
     * VALUE_TEXT_ANTIALIAS_OFF表示将文字的抗锯齿关闭, 使用的关闭抗锯齿后，字体尽量选择12到14号的宋体字,这样文字最清晰好看
     */
    // chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
    chart.setTextAntiAlias(false);
    chart.setBackgroundPaint(Color.white);
    // create plot
    CategoryPlot plot = chart.getCategoryPlot();
    // 设置横虚线可见
    plot.setRangeGridlinesVisible(true);
    // 虚线色彩
    plot.setRangeGridlinePaint(Color.gray);

    // 数据轴精度
    NumberAxis vn = (NumberAxis) plot.getRangeAxis();
    // vn.setAutoRangeIncludesZero(true);
    DecimalFormat df = new DecimalFormat("#0.00");
    vn.setNumberFormatOverride(df); // 数据轴数据标签的显示格式
    // x轴设置

    CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setLabelFont(labelFont);// 轴标题

    domainAxis.setTickLabelFont(labelFont);// 轴数值

    // Lable（Math.PI/3.0）度倾斜
    // domainAxis.setCategoryLabelPositions(CategoryLabelPositions
    // .createUpRotationLabelPositions(Math.PI / 3.0));

    domainAxis.setMaximumCategoryLabelWidthRatio(0.6f);// 横轴上的 Lable 是否完整显示

    // 设置距离图片左端距离
    domainAxis.setLowerMargin(0.1);
    // 设置距离图片右端距离
    domainAxis.setUpperMargin(0.1);
    // 设置 columnKey 是否间隔显示
    // domainAxis.setSkipCategoryLabelsToFit(true);

    plot.setDomainAxis(domainAxis);
    // 设置柱图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）
    plot.setBackgroundPaint(new Color(255, 255, 204));

    // y轴设置
    ValueAxis rangeAxis = plot.getRangeAxis();
    rangeAxis.setLabelFont(labelFont);
    rangeAxis.setTickLabelFont(labelFont);
    // 设置最高的一个 Item 与图片顶端的距离
    rangeAxis.setUpperMargin(0.15);
    // 设置最低的一个 Item 与图片底端的距离
    rangeAxis.setLowerMargin(0.15);
    plot.setRangeAxis(rangeAxis);

    BarRenderer renderer = new BarRenderer();
    // 设置柱子宽度
    renderer.setMaximumBarWidth(0.05);
    // 设置柱子高度
    renderer.setMinimumBarLength(0.2);
    // 设置柱子边框颜色
    renderer.setBaseOutlinePaint(Color.BLACK);
    // 设置柱子边框可见
    renderer.setDrawBarOutline(true);

    // // 设置柱的颜色
    renderer.setSeriesPaint(0, new Color(204, 255, 255));
    renderer.setSeriesPaint(1, new Color(153, 204, 255));
    renderer.setSeriesPaint(2, new Color(51, 204, 204));

    // 设置每个地区所包含的平行柱的之间距离
    renderer.setItemMargin(0.0);

    // 显示每个柱的数值，并修改该数值的字体属性
    renderer.setIncludeBaseInRange(true);
    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    renderer.setBaseItemLabelsVisible(true);

    plot.setRenderer(renderer);
    // 设置柱的透明度
    plot.setForegroundAlpha(1.0f);

    return chart;
  }

}
