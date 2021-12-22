package com.util.word;

import com.darren.demo.DemoElasticsearchApplication;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

/**
 * word convert PDF
 *
 * @author : darren
 * @date : 2021/11/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoElasticsearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WordConvertPDF {

    @Test
    public void docxToPdf() {

        File inputFile = new File("D://word.docx");
        File outputFile = new File("D://test.pdf");
        try {
            InputStream inputStream = new FileInputStream(inputFile);
            OutputStream outputStream = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.builder().build();
            converter.convert(inputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();

            outputStream.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}
