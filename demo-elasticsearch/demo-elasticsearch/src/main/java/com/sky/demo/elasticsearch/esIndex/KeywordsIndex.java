package com.sky.demo.elasticsearch.esIndex;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 账户结构--关键词
 *
 * @author : darren
 * @date : 2020/1/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "keywords")
public class KeywordsIndex extends BaseAccountIndex {

    /**
     * keywords-id
     */
    @ApiModelProperty(value = "关键字id", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String keywordsId;

    /**
     * extension-feed-item-name
     */
    @ApiModelProperty(value = "关键字名称", example = "主推词")
    @Field(type = FieldType.Text)
    private String keywordsName;

    /**
     * keyword-match-type
     */
    @ApiModelProperty(value = "关键字匹配类型")
    @Field(type = FieldType.Keyword)
    private String keywordMatchType;

    /**
     * quality-score
     */
    @ApiModelProperty(value = "质量得分")
    @Field(type = FieldType.Double)
    private Double qualityScore;

    /**
     * status
     */
    @ApiModelProperty(value = "关键字状态")
    @Field(type = FieldType.Keyword)
    private String status;

    /**
     * serving-status
     */
    @ApiModelProperty(value = "标准服务状态")
    @Field(type = FieldType.Keyword)
    private String servingStatus;

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
     * approval-status
     */
    @ApiModelProperty(value = "审批状态", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String approvalStatus;

    /**
     * effective-cpc-bid-micros
     */
    @ApiModelProperty(value = "最高 每次点击费用")
    @Field(type = FieldType.Double)
    private Double effectiveCpcBidMicros;

    /**
     * effective-cpc-bid-source
     */
    @ApiModelProperty(value = "最高每次点击费用来源")
    @Field(type = FieldType.Keyword)
    private String effectiveCpcBidSource;

    /**
     * final-urls
     */
    @ApiModelProperty(value = "最终网址", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String finalUrls;

    /**
     * first-page-cpc-micros
     */
    @ApiModelProperty(value = "页首出价估算值", example = "1666282296")
    @Field(type = FieldType.Double)
    private Double firstPageCpcMicros;
}
