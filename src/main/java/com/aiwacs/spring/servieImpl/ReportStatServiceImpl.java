package com.aiwacs.spring.servieImpl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiwacs.spring.common.HistoryUtils;
import com.aiwacs.spring.models.StatDisk;
import com.aiwacs.spring.models.StatDiskTot;
import com.aiwacs.spring.models.StatNetwork;
import com.aiwacs.spring.models.StatSys;
import com.aiwacs.spring.repository.StatDiskRepository;
import com.aiwacs.spring.repository.StatDiskTotRepository;
import com.aiwacs.spring.repository.StatNetworkRepository;
import com.aiwacs.spring.repository.StatSysRepository;
import com.aiwacs.spring.service.ReportStatService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

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

    @Transactional
    @Override
    public ByteArrayInputStream getReportDownloadPdf(String chartData) throws IOException, ParseException {
        System.out.println(chartData);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream(); 
        String fontname = "C:\\Users\\sysone\\Downloads\\fonts\\malgun.ttf";
        SimpleDateFormat outFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateFormat= DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        Date time = new Date();
        String outTime = outFormat.format(time);

        JSONObject jObject = new JSONObject(chartData);
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
            
            float width = PageSize.A4.getWidth() - 75;  // 595
            float height = 280;  // 842
            
            
            for(int z=0; z< iObject.length(); z++) {
                DefaultCategoryDataset mychartData=new DefaultCategoryDataset();
                JSONObject imsi = (JSONObject) iObject.get(z);
                String resourceName = imsi.getString("resourceName");
                String deviceNames = imsi.getString("deviceName");
                JSONObject option = imsi.getJSONObject("option");
                JSONArray gridData = imsi.getJSONArray("seriesData");
                
                JSONObject xAxis = option.getJSONObject("xAxis");
                JSONArray series = option.getJSONArray("series");
                JSONArray categoriesList = xAxis.getJSONArray("categories");
                
                System.out.println(resourceName);
                String[] categoryArray = null;
                
                // Chart
                if(resourceName.equals("CPU Processor (%)")     || resourceName.equals("CPU Context Switch") ||
                   resourceName.equals("CPU Run Queue")         || resourceName.equals("CPU Core") || 
                   resourceName.equals("Load Avg")              || resourceName.equals("Disk Total Used (%)") ||
                   resourceName.equals("Disk Total Used Bytes") || resourceName.equals("Memory Used (%)") ||
                   resourceName.equals("Memory Bytes")          || resourceName.equals("Memory Buffers (%)") ||
                   resourceName.equals("Memory Buffers Bytes")  || resourceName.equals("Memory Cached (%)") ||
                   resourceName.equals("Memory Cached Bytes")   || resourceName.equals("Memory Shared (%)")  ||
                   resourceName.equals("Memory Shared Bytes")   || resourceName.equals("Memory Swap (%)") ||
                   resourceName.equals("Memory Swap Bytes")     || resourceName.equals("Memory Pagefault")   ) {
                    
                    JSONObject seriesData = (JSONObject) series.get(0);
                    JSONArray seriesList = seriesData.getJSONArray("data");
                   
                    BigDecimal[] seriesArray = null;
                    categoryArray = new String[categoriesList.length()];
                    seriesArray = new BigDecimal[seriesList.length()];
                    
                    for(int a=0; a < seriesList.length(); a++) {
                        categoryArray[a] = categoriesList.optString(a);
                        seriesArray[a] = seriesList.optBigDecimal(a, null);
                        
                        Date dates = outFormat.parse(categoryArray[a]);
                        SimpleDateFormat cateDateFormat = new SimpleDateFormat("MM-dd HH:mm");
                        categoryArray[a] = cateDateFormat.format(dates);
                        mychartData.setValue(seriesArray[a], deviceNames  ,categoryArray[a]);  // Comparable 형태를 맞추기 위해 String[] 변환
                    } 
                } else if(resourceName.equals("Network Traffic")     || resourceName.equals("Network PPS") ||
                          resourceName.equals("NIC Discards")        || resourceName.equals("NIC Errors") ||
                          resourceName.equals("Disk Used (%)")       || resourceName.equals("Disk Used Bytes") ||
                          resourceName.equals("Disk I/O (%)")        || resourceName.equals("Disk I/O Count") ||
                          resourceName.equals("Disk I/O Bytes")      || resourceName.equals("Disk Queue")  ) {
                    BigDecimal[] partitionDataAry =  null;
                    
                    for(int w=0; w<series.length(); w++) {
                        JSONObject seriesData = (JSONObject) series.get(w);
                        JSONArray seriesList = seriesData.getJSONArray("data");
                        String seriesPartition = seriesData.getString("name");
                        
                        categoryArray = new String[categoriesList.length()];
                        partitionDataAry = new BigDecimal[seriesList.length()];
                        
                        for(int a=0; a < seriesList.length(); a++) {
                            categoryArray[a] = categoriesList.optString(a);
                            partitionDataAry[a] = seriesList.optBigDecimal(a,null);
                            
                            Date dates = outFormat.parse(categoryArray[a]);
                            SimpleDateFormat cateDateFormat = new SimpleDateFormat("MM-dd HH:mm");
                            categoryArray[a] = cateDateFormat.format(dates);
                            mychartData.setValue(partitionDataAry[a], seriesPartition , categoryArray[a]);
                        } 
                    }
                }
                
                
                JFreeChart chart=ChartFactory.createLineChart(resourceName,null,null,mychartData,PlotOrientation.VERTICAL,true,true,false);
                chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
                chart.getTitle().setFont(new java.awt.Font(resourceName, java.awt.Font.BOLD, 12));
                chart.getPlot().setBackgroundPaint(new Color(236,236,236));
                chart.getLegend().setItemFont(new java.awt.Font("돋움", java.awt.Font.PLAIN, 10)); 
                chart.getLegend().setFrame(BlockBorder.NONE);

                PdfContentByte Add_Chart_Content = writer.getDirectContent();  
                PdfTemplate Template_Chart_Holder = Add_Chart_Content.createTemplate(595, 300);  
                Graphics2D Graphics_Chart = Template_Chart_Holder.createGraphics(595, 300, new DefaultFontMapper()); 
                
                CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
                domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));
                domainAxis.setTickLabelFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 10));
                
                CategoryPlot plot = chart.getCategoryPlot();
                plot.getDomainAxis().setLabelFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 5));
                plot.getRangeAxis().setLabelFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 5));
                ValueAxis axis2 = plot.getRangeAxis();
                axis2.setTickLabelFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 8)); 
                
                
                Rectangle2D Chart_Region = new Rectangle2D.Double(10, 10, width, height); 
                chart.draw(Graphics_Chart, Chart_Region);  
                
                Graphics_Chart.dispose();
                Image chartImage = Image.getInstance(Template_Chart_Holder);
                if(!resourceName.equals("CPU Used (%)")) {
                    doc.add(chartImage);
                }
                writer.setStrictImageSequence(true);
                // Table
                if(gridData.length() != 0) {
                    JSONArray gridDataAryLength = (JSONArray) gridData.get(0);
                    PdfPTable table = new PdfPTable(gridDataAryLength.length()); 
                    table.setWidthPercentage(95);
                    Font gridFont = new Font(bFont,9, Font.NORMAL);
                    
                    for(int r=0; r<gridDataAryLength.length(); r++) {
                        PdfPCell c1 = new PdfPCell(new Phrase(gridDataAryLength.get(r).toString(),gridFont));
                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c1.setBackgroundColor(Color.LIGHT_GRAY);
                        c1.setFixedHeight(20f);
                        c1.setPadding(4f);
                        table.addCell(c1);
                    }
                    
                    for(int q=0; q< gridData.length(); q++) {
                        JSONArray gridDataAry = (JSONArray) gridData.get(q);
                        for(int b=0; b<gridDataAry.length(); b++) {
                            if(q != 0 ) {
                                Font gridFont1_1 = new Font(bFont,8, Font.NORMAL);
                                PdfPCell c1_1 = new PdfPCell(new Phrase(gridDataAry.get(b).toString(),gridFont1_1));
                                c1_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                c1_1.setFixedHeight(18f);
                                c1_1.setPadding(4f);
                                if(q == gridData.length() - 3) {
                                    Color myColor = WebColors.getRGBColor("#FFE5E5");
                                    c1_1.setBackgroundColor(myColor);
                                } else if(q == gridData.length() - 2) {
                                    Color myColor = WebColors.getRGBColor("#E7F8FF");
                                    c1_1.setBackgroundColor(myColor);
                                } else if(q == gridData.length() - 1) {
                                    Color myColor = WebColors.getRGBColor("#EDFFEF");
                                    c1_1.setBackgroundColor(myColor);
                                }
                                table.addCell(c1_1);
                            }
                        }
                    }
                    doc.add(table);
                }
            }
            doc.close();
        } catch (Exception e) {
            System.out.println(e);
        } 
        HistoryUtils.pdfDownloadReportHistory("AiWACS_Report.pdf");
        return new ByteArrayInputStream(out.toByteArray());
    }
   
}






























