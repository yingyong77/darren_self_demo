package com.sky.demo.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.demo.elasticsearch.esIndex.CidDailySettlementIndex;
import com.skytech.edata.repo.common.model.AdsReport;
import com.skytech.edata.repo.common.model.GEOReport;
import com.skytech.edata.repo.common.model.KeywordsReport;
import com.skytech.edata.repo.common.model.base.Paging;
import com.skytech.edata.repo.common.model.queue.DiggerDataMessage;
import com.skytech.edata.repo.common.model.search.AdsReportSearch;
import com.skytech.edata.repo.common.model.search.GEOReportSearch;
import com.skytech.edata.repo.common.model.search.KeywordsReportSearch;
import com.skytech.edata.repo.mysql.dao.GEOReportDao;
import com.skytech.edata.repo.mysql.dao.KeywordsReportDao;
import com.skytech.edata.repo.mysql.service.AdsReportMysql;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sky.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AnalyticsControllerTest {


    @Autowired
    private AdsReportMysql adsReportMysql;

    @Autowired
    private GEOReportDao geoReportDaol;

    @Autowired
    private KeywordsReportDao keywordsReportDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void cidReport() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        Calendar startDate = new GregorianCalendar(2019, Calendar.OCTOBER, 1);
        Calendar endDate = new GregorianCalendar(2019, Calendar.DECEMBER, 1);


        for (Calendar itemStartDate = (Calendar) startDate.clone(); itemStartDate.getTime().getTime() < endDate.getTime().getTime(); itemStartDate.add(Calendar.DAY_OF_MONTH, 1)) {

            AdsReportSearch search = new AdsReportSearch();
            search.setStartReportDate(itemStartDate.getTime());
            search.setEndReportDate(itemStartDate.getTime());
            search.setIncludeZero(false);

            List<AdsReport> adsReportList1 = adsReportMysql.getBySearch(search);

            log.info(search.toString());
            log.info(dateFormat.format(search.getStartReportDate()) + "  " + adsReportList1.size() + "");

            adsReportList1.forEach(item -> {

                try {
                    item.setId(null);
                    if (item.getMccParentId() == null) {
                        item.setMccParentId(0L);
                    }

//                    DiggerDataMessage dataMessage = new DiggerDataMessage();
//                    dataMessage.setAdsReport(item);
//
//                    String itemJson = objectMapper.writeValueAsString(dataMessage);
//
//                    rabbitTemplate.convertAndSend("insert-data-ads-report", itemJson);

                    String itemJson = objectMapper.writeValueAsString(item);

                    rabbitTemplate.convertAndSend("digger.data.topic", "data.cid.adsReport.report", itemJson);


                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        }

        log.info("end");

    }

    @Test
    public void geoReport() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        Calendar startDate = new GregorianCalendar(2019, Calendar.JANUARY, 1);

        Calendar endDate = new GregorianCalendar(2019, Calendar.AUGUST, 20);


        for (Calendar itemStartDate = (Calendar) startDate.clone(); itemStartDate.getTime().getTime() < endDate.getTime().getTime(); itemStartDate.add(Calendar.DAY_OF_MONTH, 1)) {

            Calendar itemEndDate = (Calendar) itemStartDate.clone();
            itemEndDate.add(Calendar.DAY_OF_MONTH, 1);

            GEOReportSearch search = new GEOReportSearch();
            search.setStartReportDate(itemStartDate.getTime());
            search.setEndReportDate(itemEndDate.getTime());
            search.setIncludeZero(false);

            Paging paging = new Paging();
            paging.setOffset(0);
            paging.setRows(10000);
            search.setPaging(paging);

            List<GEOReport> geoReportList;

            do {
                geoReportList = geoReportDaol.getBySearch(search);

                log.info(search.toString());
                log.info(dateFormat.format(search.getStartReportDate()) + "  " + geoReportList.size() + "");

                geoReportList.forEach(item -> {

                    try {
                        item.setId(null);
                        if (item.getMccParentId() == null) {
                            item.setMccParentId(0L);
                        }

                        String itemJson = objectMapper.writeValueAsString(item);

                        rabbitTemplate.convertAndSend("digger.data.topic", "data.geo.repository", itemJson);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
                paging.setOffset(paging.getOffset() + 10000);
            }
            while (!CollectionUtils.isEmpty(geoReportList));
        }

        log.info("end");

    }

    @Test
    public void keywordsReport() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        Calendar startDate = new GregorianCalendar(2019, Calendar.JANUARY, 1);
        Calendar endDate = new GregorianCalendar(2019, Calendar.AUGUST, 21);


        for (Calendar itemStartDate = (Calendar) startDate.clone(); itemStartDate.getTime().getTime() < endDate.getTime().getTime(); itemStartDate.add(Calendar.DAY_OF_MONTH, 1)) {

            Calendar itemEndDate = (Calendar) itemStartDate.clone();
            itemEndDate.add(Calendar.DAY_OF_MONTH, 1);


            KeywordsReportSearch search = new KeywordsReportSearch();
            search.setStartReportDate(itemStartDate.getTime());
            search.setEndReportDate(itemEndDate.getTime());
            search.setIncludeZero(false);

            Paging paging = new Paging();
            paging.setOffset(0);
            paging.setRows(10000);


            search.setPaging(paging);

            List<KeywordsReport> keywordsReportList;

            do {
                keywordsReportList = keywordsReportDao.getBySearch(search);

                log.info(search.toString());
                log.info(dateFormat.format(search.getStartReportDate()) + "  " + keywordsReportList.size() + "");

                keywordsReportList.forEach(item -> {

                    try {
                        item.setId(null);
                        if (item.getMccParentId() == null) {

                            item.setMccParentId(0L);
                        }

                        String itemJson = objectMapper.writeValueAsString(item);

                        rabbitTemplate.convertAndSend("digger.data.topic", "data.keywords.repository", itemJson);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
                paging.setOffset(paging.getOffset() + 10000);
            }
            while (!CollectionUtils.isEmpty(keywordsReportList));
        }

        log.info("end");

    }

    @Test
    public void getById_Create() {
        elasticsearchTemplate.createIndex(CidDailySettlementIndex.class);
        elasticsearchTemplate.putMapping(CidDailySettlementIndex.class);
    }

    @Test
    @SneakyThrows(IOException.class)
    public void cidReport_Create() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        Calendar startDate = new GregorianCalendar(2019, Calendar.AUGUST, 1);
        Calendar endDate = new GregorianCalendar(2020, Calendar.JANUARY, 1);

        Random random = new Random();

        for (Calendar itemStartDate = (Calendar) startDate.clone(); itemStartDate.getTime().getTime() < endDate.getTime().getTime(); itemStartDate.add(Calendar.DAY_OF_MONTH, 1)) {

            log.info(itemStartDate.getTime().toString());


            AdsReport adsReport = new AdsReport();
            adsReport.setCompany("01");
            adsReport.setBusiness("13");
            adsReport.setDate(itemStartDate.getTime());
            adsReport.setMccParentId(402865006175064064L);
            adsReport.setMcc("1");
            adsReport.setCurrency("CNY");
            adsReport.setAccountName("技术部测试");

            adsReport.setAccount("9521159");
            adsReport.setImpressions(random.nextInt(11));
            adsReport.setCost((double) random.nextInt(11));
            adsReport.setClicks(random.nextInt(11));


            DiggerDataMessage dataMessage = new DiggerDataMessage();
            dataMessage.setAdsReport(adsReport);

            String itemJson = objectMapper.writeValueAsString(dataMessage);

            rabbitTemplate.convertAndSend("insert-data-ads-report", itemJson);
        }

        log.info("end");

    }
}