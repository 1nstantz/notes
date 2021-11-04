package com.itext;


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.sun.deploy.net.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 会出现中文符号出现在句首的问题，jar包已修改
 * @author qinhao
 * @date 2021/11/4 - 11:02
 */
@Slf4j
public class XhtmlToPdfUtils {

    static final String DEFAULTFONT="simsun.ttf";

    private static BaseFont bf;

    public static String getFontPath(String font) {
        String path="";
        // 1.兼容linux和windows
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = (classLoader == null) ? null : classLoader.getResource("/");
        String threadCurrentPath = (url == null) ? "" : url.getPath();
        // 2.如果线程路径为null,则使用当前类加载路径
        if(StringUtils.isBlank(threadCurrentPath)) {
            path = HttpUtils.class.getClass().getResource("/").getPath();
        }
        // 3.拼接字体路径
        StringBuffer stringBuffer = new StringBuffer(path);
        stringBuffer.append("fonts/" + font);
        path = stringBuffer.toString();
        log.info("getFontPath threadCurrentPath: {} path:{}",threadCurrentPath,path);
        return path;
    }


    /**
     *
     * @Title: convertXhtmlToPdf
     * @Description:
     * @param htmlContent html格式内容
     * @param file 输出文件
     * @param document 设置页面大小和边距，可以自定义
     */
    public static void convertXhtmlToPdf(String htmlContent, File file, Document document) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        PdfWriter writer = null;
        try {
            //获取生成pdf的html内容
            inputStream = new ByteArrayInputStream(htmlContent.getBytes("utf-8"));
            outputStream = new FileOutputStream(file);
            writer = PdfWriter.getInstance(document, outputStream);
            //设置页眉和页脚
            HeaderFoot headerFoot = new HeaderFoot();
            writer.setPageEvent(headerFoot);
            document.open();
            //添加字体
            XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
            String fontPath = getFontPath(DEFAULTFONT);
            fontImp.register(fontPath);
            //设置编码
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, inputStream, Charset.forName("utf-8"), fontImp);
            //关闭,(不关闭则会生成无效pdf)
            document.close();
        }catch(Exception ex) {
            log.info("PDF导出异常",ex);
        }finally {
            try {
                if(writer != null) writer.close();
                if(outputStream != null)  outputStream.close();
                if(inputStream != null) inputStream.close();
            }catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * 设置页眉和页脚
     */
    static class HeaderFoot extends PdfPageEventHelper {
        public String  header = "";
        public int fontSize = 12;
        public PdfTemplate total;
        private static Font textFont;

        static {
            try {
                //设置字体样式
                bf = BaseFont.createFont(getFontPath(DEFAULTFONT),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
                textFont = new Font(bf,12, Font.NORMAL);//正常
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 文档打开时创建模板
         *
         * @see PdfPageEventHelper#onOpenDocument(PdfWriter, Document)
         */
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(50, 50);// 共 页 的矩形的长宽高
        }

        /**
         * 关闭每页的时候，写入页眉，写入'第几页共'这几个字。
         *
         * @see PdfPageEventHelper#onEndPage(PdfWriter, Document)
         */
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            // 1.写入页眉
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(header, textFont), document.right(), document.top() + 20, 0);
            // 2.写入前半部分的 第 X页/共
            int pageS = writer.getPageNumber();
            String foot1 = "第"+pageS + "页/共";
            Phrase footer = new Phrase(foot1, new Font(bf,12));
            // 3.计算前半部分的foot1的长度，后面好定位最后一部分的'Y页'这俩字的x轴坐标，字体长度也要计算进去 = len
            float len = bf.getWidthPoint(foot1, fontSize);
            // 4.拿到当前的PdfContentByte
            PdfContentByte cb = writer.getDirectContent();
            // 5.写入页脚1，x轴就是(右margin+左margin + right() -left()- len)/2.0F 再给偏移20F适合人类视觉感受，否则肉眼看上去就太偏左了 ,y轴就是底边界-20,否则就贴边重叠到数据体里了就不是页脚了；注意Y轴是从下往上累加的，最上方的Top值是大于Bottom好几百开外的。
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer, (document.rightMargin() + document.right() + document.leftMargin() - document.left() - len) / 2.0F + 20F, document.bottom() - 20, 0);
            // 6.写入页脚2的模板（就是页脚的Y页这俩字）添加到文档中，计算模板的和Y轴,X=(右边界-左边界 - 前半部分的len值)/2.0F + len ， y 轴和之前的保持一致，底边界-20
            cb.addTemplate(total, (document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0F + 20F, document.bottom() - 20); // 调节模版显示的位置
        }

        /**
         * 关闭文档时，替换模板，完成整个页眉页脚组件
         *
         * @see PdfPageEventHelper#onCloseDocument(PdfWriter, Document)
         */
        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            //关闭文档的时候，将模板替换成实际的 Y 值
            total.beginText();
            total.setFontAndSize(bf, fontSize);// 生成的模版的字体、颜色
            String foot = "" + (writer.getPageNumber());
            total.showText(foot+"页");// 模版显示的内容
            total.endText();
            total.closePath();
        }

    }
}
