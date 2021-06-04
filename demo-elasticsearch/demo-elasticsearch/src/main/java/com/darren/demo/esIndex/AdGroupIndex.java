package com.darren.demo.esIndex;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 账户结构--广告组
 *
 * @author : darren
 * @date : 2020/1/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "ad-group")
public class AdGroupIndex extends BaseAccountIndex {

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
     * ad-group-status
     */
    @ApiModelProperty(value = "广告组状态", example = "有效")
    @Field(type = FieldType.Keyword)
    private String adGroupStatus;

    /**
     * type
     */
    @ApiModelProperty(value = "所属类型", example = "标准")
    @Field(type = FieldType.Keyword)
    private String type;

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
     * ad-rotation-mode
     */
    @ApiModelProperty(value = "广告轮播模式")
    @Field(type = FieldType.Keyword)
    private String adRotationMode;

    /**
     * tracking-url-template
     */
    @ApiModelProperty(value = "跟踪URL的URL模板")
    @Field(type = FieldType.Keyword)
    private String trackingUrlTemplate;

    /**
     * cpc-bid-micros
     */
    @ApiModelProperty(value = "最高每次点击费用出价")
    @Field(type = FieldType.Double)
    private Double cpcBidMicros;

    /**
     * final-url-suffix
     */
    @ApiModelProperty(value = "最终URL模板")
    @Field(type = FieldType.Keyword)
    private String finalUrlSuffix;

    /**
     * effective-cpc-bid-micros
     */
    @ApiModelProperty(value = "默认最大每次点击费用")
    @Field(type = FieldType.Double)
    private Double effectiveCpcBidMicros;

}
