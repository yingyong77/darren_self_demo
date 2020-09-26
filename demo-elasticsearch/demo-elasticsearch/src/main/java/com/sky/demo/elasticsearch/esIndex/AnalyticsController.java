package com.sky.demo.elasticsearch.esIndex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sky.
 */
@RestController
@RequestMapping("/es")
public class AnalyticsController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @GetMapping("/a")
    public String getById() {

        elasticsearchTemplate.createIndex(CidDailySettlementIndex.class);
        elasticsearchTemplate.putMapping(CidDailySettlementIndex.class);

        return "Success";
    }

}
