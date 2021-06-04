package com.darren.demo.esIndex;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


/**
 * 账户结构--搜索词-报表
 *
 * @author : darren
 * @date : 2020/1/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "search-term-report")
public class SearchTermReportIndex extends BaseAccountIndex {

    /**
     * search-term-id
     */
    @ApiModelProperty(value = "搜索词id", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String searchTermId;

    /**
     * search-term-name
     */
    @ApiModelProperty(value = "搜索词名称", example = "led display")
    @Field(type = FieldType.Keyword)
    private String searchTermName;

    /**
     * match-keywords-id
     */
    @ApiModelProperty(value = "匹配到的关键词id", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String keywordsId;

    /**
     * match-keywords-name
     */
    @ApiModelProperty(value = "匹配到的关键词名称")
    @Field(type = FieldType.Keyword)
    private String keywordsName;

    /**
     * search-term-status
     */
    @ApiModelProperty(value = "搜索词状态", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String searchTermStatus;

    /**
     * search-term-status
     */
    @ApiModelProperty(value = "搜索词匹配类型")
    @Field(type = FieldType.Keyword)
    private String searchTermMatchType;

    /**
     * campaign-id
     */
    @ApiModelProperty(value = "广告组系列id", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String campaignId;

    /**
     * campaign-name
     */
    @ApiModelProperty(value = "广告组系列名称", example = "主推词")
    @Field(type = FieldType.Text)
    private String campaignName;

    /**
     * ad-group-id
     */
    @ApiModelProperty(value = "ad group id", required = true)
    @Field(type = FieldType.Keyword)
    private String adGroupId;

    /**
     * ad-group-name
     */
    @ApiModelProperty(value = "ad group name")
    @Field(type = FieldType.Text)
    private String adGroupName;

    /**
     * impressions
     */
    @ApiModelProperty(value = "展现量")
    @Field(index = false, type = FieldType.Long)
    private int impressions;

    /**
     * clicks
     */
    @ApiModelProperty(value = "点击量")
    @Field(index = false, type = FieldType.Long)
    private int clicks;

    /**
     * cost
     */
    @ApiModelProperty(value = "消耗")
    @Field(index = false, type = FieldType.Double)
    private double cost;

    /**
     * exchangeRateCost
     */
    @ApiModelProperty(value = "汇率 折算消耗")
    @Field(index = false, type = FieldType.Double)
    private double exchangeRateCost;

    /**
     * conversion
     */
    @ApiModelProperty(value = "转化")
    @Field(index = false, type = FieldType.Double)
    private double conversion;


}
