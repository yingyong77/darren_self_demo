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
@Document(indexName = "extension-feed-report")
public class ExtensionFeedReportIndex extends BaseAccountIndex {

    /**
     * feed-item-id
     */
    @ApiModelProperty(value = "附加信息id", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String feedItemId;

    /**
     * extension-feed-item-name
     */
    @ApiModelProperty(value = "附加信息名称", example = "主推词")
    @Field(type = FieldType.Text)
    private String extensionFeedItemName;

    /**
     * of-level
     */
    @ApiModelProperty(value = "所属层级结构", example = "广告系列")
    @Field(type = FieldType.Long)
    private int ofLevel;

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
