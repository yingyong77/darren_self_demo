package com.util.word;


import com.aspose.words.DocumentBuilder;
import com.aspose.words.OoxmlSaveOptions;
import com.aspose.words.SaveFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.misc.BASE64Encoder;
import wordUtil.LicenseLoad;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class HttpsDownloadUtils {

    public static String encodeImageToBase64(String fileUrl) throws Exception {
        String type = fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
        String head = "data:image/" + type + ";base64,";

        SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
        sslcontext.init(null, new TrustManager[]{new X509TrustUtiil()}, new java.security.SecureRandom());
        URL url = new URL(fileUrl);
        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslsession) {
                System.out.println("Hostname is not matched for cert.");
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        int code = urlCon.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
            throw new Exception("文件读取失败");
        }

        //通过输入流获取图片数据
        InputStream inStream = urlCon.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        byte[] data = outStream.toByteArray();
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(data);
        System.out.println("base64编码==================");
        System.out.println(base64);

        return head + base64;
    }

    public static void doc2pdf() {
        LicenseLoad.getLicense(); //验证License 若不验证则转化出的pdf文档会有水印产生
        try {
            String sourcerFile = "C:\\Users\\gxf\\Desktop\\test\\str1.docx";
            String targetFile = "C:\\Users\\gxf\\Desktop\\test\\str1.pdf";
            long old = System.currentTimeMillis();
            File file = new File(targetFile);  //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            com.aspose.words.Document doc = new com.aspose.words.Document(sourcerFile);                    //sourcerFile是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean htmlToWord() {
        LicenseLoad.getLicense();
        try {
//            String html = "这是富文本编辑的html文字，<b>加粗的文字</b>";
            String html = readTxtFile();
            System.out.println(html);
            String wordPath = "D://temp44.docx";

            String password = "123456";

            com.aspose.words.Document doc = new com.aspose.words.Document();
            // Protect with a protection type

            DocumentBuilder builder = new DocumentBuilder(doc);
            builder.insertHtml(html);
            //生成doc文件
            //SaveOptions saveOptions = SaveOptions.createSaveOptions(SaveFormat.DOCX);

            OoxmlSaveOptions ooxmlSaveOptions = new OoxmlSaveOptions();
            ooxmlSaveOptions.setPassword("111");

            //saveOptions.

            doc.save(wordPath, ooxmlSaveOptions);
            System.out.println("转换成功=========================yes=========");  //转化用时
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String readTxtFile() {
        String str = "";
        try {
            String filePath = "D://121.html";
            String encoding = "utf-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    str += lineTxt;
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }

            Document doc = Jsoup.parse(str, "utf-8");
            Elements imgs = doc.getElementsByTag("img");

            for (Element img : imgs) {
                String src = img.attr("src");
                img.attr("src", encodeImageToBase64(src));
            }
            // str = doc.getElementsByTag("body").html();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * X509Trust
     */
    static class X509TrustUtiil implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

    public static void main(String[] args) throws Exception {
        htmlToWord();
//        doc2pdf();
//        encodeImageToBase64("https://tartanacademy.com/gpc-service/images/1622218539197_preview_TEGbundle.jpg");
        System.out.println("===============成功===========");
    }
}
