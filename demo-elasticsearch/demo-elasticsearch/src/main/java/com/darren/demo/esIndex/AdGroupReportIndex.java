package com.darren.demo.esIndex;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * ad-report
 *
 * @author darren
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "ad-group-report")
public class AdGroupReportIndex extends BaseAccountIndex {


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

    @ApiModelProperty(value = "目标 ps:facebook", example = "目标")
    @Field(type = FieldType.Keyword)
    private String facebookObjective;

    @ApiModelProperty(value = "成效")
    @Field(index = false, type = FieldType.Double)
    private double effect;

    @ApiModelProperty(value = "覆盖人数")
    @Field(index = false, type = FieldType.Long)
    private int covers;

}