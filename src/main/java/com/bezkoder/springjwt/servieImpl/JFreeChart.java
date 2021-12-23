//package com.bezkoder.springjwt.servieImpl;
//
//import java.awt.Graphics2D;
//import java.awt.geom.Rectangle2D;
//
//import com.lowagie.text.Rectangle;
//import com.lowagie.text.pdf.PdfContentByte;
//import com.lowagie.text.pdf.PdfGraphics2D;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPCellEvent;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfTemplate;
//
//public class JFreeChart implements PdfPCellEvent{
//    private JFreeChart chart;
//    
//    public JFreeChart(final JFreeChart chart) {
//        this.chart = chart;
//    }
//    
//    @Override
//    public void cellLayout(PdfPCell arg0, Rectangle arg1, PdfContentByte[] arg2) {
//        PdfContentByte cb = arg2[PdfPTable.TEXTCANVAS]; //optional, can be other canvas
//        PdfTemplate pie = cb.createTemplate(arg1.getWidth(), arg1.getHeight());
//        Graphics2D g2d1 = new PdfGraphics2D(pie, arg1.getWidth(), arg1.getHeight());
//        Rectangle2D r2d1 = new Rectangle2D.Double(0, 0, arg1.getWidth(), arg1.getHeight());
//        chart.draw(g2d1, r2d1);
//        g2d1.dispose();
//        cb.addTemplate(pie, arg1.getLeft(), arg1.getBottom());
//    }
//}
