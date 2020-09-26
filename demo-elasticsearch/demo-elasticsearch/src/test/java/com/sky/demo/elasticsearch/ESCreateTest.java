package com.sky.demo.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : darren
 * @date : 2020/1/9
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ESCreateTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void createESIndex() {
//        elasticsearchTemplate.createIndex(AdReportIndex.class);
//        elasticsearchTemplate.putMapping(AdReportIndex.class);
//
//        elasticsearchTemplate.createIndex(AdGroupReportIndex.class);
//        elasticsearchTemplate.putMapping(AdGroupReportIndex.class);
//
//        elasticsearchTemplate.createIndex(AdGroupReportIndex.class);
//        elasticsearchTemplate.putMapping(AdGroupReportIndex.class);
//
//        elasticsearchTemplate.createIndex(AudienceIndex.class);
//        elasticsearchTemplate.putMapping(AudienceIndex.class);
//
//        elasticsearchTemplate.createIndex(AudienceReportIndex.class);
//        elasticsearchTemplate.putMapping(AudienceReportIndex.class);
//
//        elasticsearchTemplate.createIndex(ExtensionFeedIndex.class);
//        elasticsearchTemplate.putMapping(ExtensionFeedIndex.class);
//
//        elasticsearchTemplate.createIndex(ExtensionFeedReportIndex.class);
//        elasticsearchTemplate.putMapping(ExtensionFeedReportIndex.class);
//
//        elasticsearchTemplate.createIndex(KeywordsIndex.class);
//        elasticsearchTemplate.putMapping(KeywordsIndex.class);
//
//        elasticsearchTemplate.createIndex(NegativeKeywordsIndex.class);
//        elasticsearchTemplate.putMapping(NegativeKeywordsIndex.class);
//
//        elasticsearchTemplate.createIndex(SearchTermIndex.class);
//        elasticsearchTemplate.putMapping(SearchTermIndex.class);

//        elasticsearchTemplate.createIndex(SearchTermReportIndex.class);
//        elasticsearchTemplate.putMapping(SearchTermReportIndex.class);
    }


}
