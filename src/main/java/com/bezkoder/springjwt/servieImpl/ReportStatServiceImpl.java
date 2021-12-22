package com.bezkoder.springjwt.servieImpl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.poi.xddf.usermodel.chart.XDDFArea3DChartData.Series;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.hibernate.internal.build.AllowSysOut;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.StatDisk;
import com.bezkoder.springjwt.models.StatDiskTot;
import com.bezkoder.springjwt.models.StatNetwork;
import com.bezkoder.springjwt.models.StatSys;
import com.bezkoder.springjwt.repository.StatDiskRepository;
import com.bezkoder.springjwt.repository.StatDiskTotRepository;
import com.bezkoder.springjwt.repository.StatNetworkRepository;
import com.bezkoder.springjwt.repository.StatSysRepository;
import com.bezkoder.springjwt.service.ReportStatService;
import com.itextpdf.text.pdf.PdfGraphics2D;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Row;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;

import aj.org.objectweb.asm.Type;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportStatServiceImpl implements ReportStatService {
    
    private final StatSysRepository statSysRepository;
    private final StatDiskRepository statDiskRepository;
    private final StatDiskTotRepository statDiskTotRepository;
    private final StatNetworkRepository statNetworkRepository;
    
    @Override
    public List<Object> getSysCpuMemory(String id,Integer cpu,Integer network,Integer disk, String startDate,String endDate) {
        System.out.println("aa" + id);
        String[] arrayId = id.split("\\|");
        
        int[] ids=Arrays.stream(arrayId).mapToInt(Integer::parseInt).toArray();
        System.out.println(ids);
        
        DateTimeFormatter aformatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime startDateTimeChange= LocalDateTime.parse(startDate.substring(0,14),aformatter);
        LocalDateTime endDateTimeChange= LocalDateTime.parse(endDate.substring(0,14),aformatter);
        String stringStartDate = startDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String stringEndDate = endDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime firstDates = LocalDateTime.parse(stringStartDate,formatter);
        LocalDateTime secondDates = LocalDateTime.parse(stringEndDate, formatter);
        
        List<Object> resultList = new ArrayList<Object>();
        
        if(cpu == 1) {
            List<StatSys> statSys = statSysRepository.getSysCpuMemory(ids, firstDates, secondDates);
            resultList.add(statSys); 
        } 
        if(network == 1) {
            List<StatNetwork> statNetworks = statNetworkRepository.getSysNetwork(ids, firstDates, secondDates);
            resultList.add(statNetworks); 
        }
        if(disk == 1) {
            List<StatDisk> statDisks = statDiskRepository.getSysCpuDisk(ids, firstDates, secondDates);   
            resultList.add(statDisks); 
        }
        if(disk == 1) {
            List<StatDiskTot> statDiskTotks = statDiskTotRepository.getSysCpuDiskTotal(ids, startDateTimeChange, endDateTimeChange);
            resultList.add(statDiskTotks);
        }
        
         
         return resultList;
    }
    
    @Override
    public Map<String, Object> getStatistics(String id, Integer sys, Integer disk, Integer nic, String startDate, String endDate) {
        String[] arrayId = id.split("\\|");
        
        int[] ids=Arrays.stream(arrayId).mapToInt(Integer::parseInt).toArray();
        
        DateTimeFormatter aformatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime startDateTimeChange= LocalDateTime.parse(startDate.substring(0,14),aformatter);
        LocalDateTime endDateTimeChange= LocalDateTime.parse(endDate.substring(0,14),aformatter);
        String stringStartDate = startDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String stringEndDate = endDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime firstDates = LocalDateTime.parse(stringStartDate,formatter);
        LocalDateTime secondDates = LocalDateTime.parse(stringEndDate, formatter);
        
        Map<String, Object> resultList = new HashMap<String, Object>();
        
        if(sys == 1) {
            List<StatSys> statSys = statSysRepository.getSysCpuMemory(ids, firstDates, secondDates);
            resultList.put("sys", statSys);
        } 
        
        if(disk == 1) {
            List<StatDisk> statDisks = statDiskRepository.getSysCpuDisk(ids, firstDates, secondDates);   
            List<StatDiskTot> statDiskTotks = statDiskTotRepository.getSysCpuDiskTotal(ids, startDateTimeChange, endDateTimeChange);
            resultList.put("disk", statDisks);
            resultList.put("diskTot", statDiskTotks);
        }
        
        if(nic == 1) {
            List<StatNetwork> statNetworks = statNetworkRepository.getSysNetwork(ids, firstDates, secondDates);
            resultList.put("nic", statNetworks);
        }
         
         return resultList;
    }

    @Override
    public ByteArrayInputStream getReportDownloadPdf(String chartData) throws IOException {
        System.out.println(chartData);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream(); 
        String fontname = "C:\\Users\\sysone\\Downloads\\fonts\\malgun.ttf";
        SimpleDateFormat outFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateFormat= DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        Date time = new Date();
        String outTime = outFormat.format(time);
        
        JSONObject jObject = new JSONObject(chartData);
        System.out.println(jObject);
        String users = jObject.getString("user");
        String startDate = jObject.getString("startDate");
        String endDate = jObject.getString("endDate");
        LocalDateTime startDateTimeChange= LocalDateTime.parse(startDate.substring(0,14),dateFormat);
        LocalDateTime endDateTimeChange= LocalDateTime.parse(endDate.substring(0,14),dateFormat);
        String stringStartDate = startDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String stringEndDate = endDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String device = jObject.getString("device");
        String[] deviceArray = device.split("\\|");
        device = device.replace("|"," , ");
        
        JSONArray iObject = jObject.getJSONArray("chart");
        JSONObject zObject = new JSONObject(iObject.get(0));
        
        
        
        
        try {
            BaseFont bFont = BaseFont.createFont(fontname,BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(bFont,15,Font.BOLD);
            Font contentFont = new Font(bFont,10);
            Document doc = new Document(PageSize.A4, 30, 30, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(doc, out);
            doc.open();
            
            Paragraph title = new Paragraph("AiWACS Report",titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(title);
            Paragraph outDate = new Paragraph("출력일시 : "+outTime, contentFont);
            outDate.setAlignment(Element.ALIGN_RIGHT);
            doc.add(outDate);
            Paragraph deviceName = new Paragraph("조회대상 : "+device,contentFont);
            deviceName.setAlignment(Element.ALIGN_LEFT);
            deviceName.setSpacingAfter(10);
            doc.add(deviceName);
            Paragraph selectTime = new Paragraph("조회기간 : "+stringStartDate+' '+'~'+' '+stringEndDate,contentFont);
            selectTime.setAlignment(Element.ALIGN_LEFT);
            selectTime.setSpacingAfter(10);
            doc.add(selectTime);
            Paragraph userName = new Paragraph("사용자 : "+users,contentFont);
            userName.setAlignment(Element.ALIGN_LEFT);
            userName.setSpacingAfter(10);
            doc.add(userName);
            
            float width = PageSize.A4.getWidth();  // 595
            float height = PageSize.A4.getHeight();   // 842
            
            
            DefaultCategoryDataset mychartData=new DefaultCategoryDataset();
            float y = writer.getPageSize().getTop();
            System.out.println(y);
            
            for(int z=0; z< iObject.length(); z++) {
                JSONObject imsi = (JSONObject) iObject.get(z);
                String resourceName = imsi.getString("resourceName");
                JSONObject option = imsi.getJSONObject("option");
                JSONObject xAxis = option.getJSONObject("xAxis");
                JSONArray series = option.getJSONArray("series");
                JSONArray categoriesList = xAxis.getJSONArray("categories");
                JSONObject seriesData = (JSONObject) series.get(0);
                JSONArray seriesList = seriesData.getJSONArray("data");
                
                List<Object> testa = new ArrayList<>();
                testa.add(series.get(0));
                
                List<String> categories = new ArrayList<String>();
                String[] stringArray = null;
                Integer[] seriesArray = null;
                stringArray = new String[categoriesList.length()];
                seriesArray = new Integer[seriesList.length()];
                
                for(int a=0; a<categoriesList.length(); a++) {
                    stringArray[a] = categoriesList.optString(a);
                    seriesArray[a] = seriesList.optInt(a);
                    
                    Date dates = outFormat.parse(stringArray[a]);
                    SimpleDateFormat cateDateFormat = new SimpleDateFormat("MM-dd HH:mm");
                    stringArray[a] = cateDateFormat.format(dates);
                    mychartData.setValue(seriesArray[a], "Test",stringArray[a]);
                }
                JFreeChart chart=ChartFactory.createLineChart(resourceName,null,null,mychartData,PlotOrientation.VERTICAL,true,true,false);
                chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
                chart.getTitle().setFont(new java.awt.Font(resourceName, java.awt.Font.BOLD, 12));
                chart.getPlot().setBackgroundPaint(new Color(236,236,236));
                chart.getLegend().setItemFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 10));  // legend 한글 아직 안먹음
                chart.getLegend().setFrame(BlockBorder.NONE);
                
                PdfContentByte Add_Chart_Content = writer.getDirectContent();  // 차트 내용 선언
                PdfTemplate Template_Chart_Holder = Add_Chart_Content.createTemplate(700, 950);  // 차트 크기설정
                Graphics2D Graphics_Chart = Template_Chart_Holder.createGraphics(650, 880, new DefaultFontMapper()); // 차트 x,y 조절
                
                PdfPTable table = new PdfPTable(3); // 3 columns.

                PdfPCell cell1 = new PdfPCell(new Paragraph("Time"));
                PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));

                table.addCell(cell1);
                table.addCell(cell2);
                doc.add(table);
                
                
                
                CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
                domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));
                domainAxis.setTickLabelFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 10));
                CategoryPlot plot = chart.getCategoryPlot();
                plot.getDomainAxis().setLabelFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 5));
                plot.getRangeAxis().setLabelFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 5));
                plot.getLegendItems().get(0).setLabelFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 10));
                
                if(z == 0) {
                    Rectangle2D Chart_Region = new Rectangle2D.Double(30, 200, 520, 300); // margin(x,y,w,h)
//                    chart.draw(Graphics_Chart, Chart_Region);
                } else {
                    Rectangle2D Chart_Region = new Rectangle2D.Double(30, 80, 520, 300); // margin(x,y,w,h)
//                    chart.draw(Graphics_Chart, Chart_Region);
                }
                
                Graphics_Chart.dispose();
                Add_Chart_Content.addTemplate(Template_Chart_Holder, 0, 0);
                
               
                
                doc.newPage();
            }
            doc.close();
        } catch (Exception e) {
            System.out.println(e);
        } 
        return new ByteArrayInputStream(out.toByteArray());
    }
    private JFreeChart getBarChart() {
        DefaultCategoryDataset  dataset = new DefaultCategoryDataset();
        
        JFreeChart chart = null;
        String title = "장바구니 통계";
        try {
            chart = ChartFactory.createLineChart(title, "상품명", "금액", dataset,  PlotOrientation.VERTICAL, false, false, false);
        } catch (Exception e) {
        }
        return chart;
    }
}















