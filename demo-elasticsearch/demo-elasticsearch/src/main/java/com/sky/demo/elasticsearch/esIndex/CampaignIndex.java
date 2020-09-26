package com.sky.demo.elasticsearch.esIndex;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 账户结构--广告系列
 *
 * @author : darren
 * @date : 2020/1/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "campaign")
public class CampaignIndex extends BaseAccountIndex {

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
     * campaign-status
     */
    @ApiModelProperty(value = "广告系列状态")
    @Field(type = FieldType.Keyword)
    private String campaignStatus;

    /**
     * campaign-serving-status
     */
    @ApiModelProperty(value = "投放状态")
    @Field(type = FieldType.Keyword)
    private String campaignServingStatus;

    /**
     * campaign-channel-type
     */
    @ApiModelProperty(value = "主要投放目标")
    @Field(type = FieldType.Keyword)
    private String campaignChannelType;


    /**
     * campaign-channel-sub-type
     */
    @ApiModelProperty(value = "子类型")
    @Field(type = FieldType.Keyword)
    private String campaignChannelSubType;

    /**
     * bidding-strategy-id
     */
    @ApiModelProperty(value = "出价策略id")
    @Field(type = FieldType.Keyword)
    private Long biddingStrategyId;

    /**
     * bidding-strategy-type
     */
    @ApiModelProperty(value = "出价策略类型")
    @Field(type = FieldType.Keyword)
    private String biddingStrategyType;

    /**
     * start-time
     */
    @ApiModelProperty(value = "广告系列有效期开始时间", example = "2020-01-06")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm", timezone = "GMT+8")
    @Field(format = DateFormat.custom, pattern = "yyyy-MM-dd", type = FieldType.Date)
    private Date startTime;

    /**
     * end-time
     */
    @ApiModelProperty(value = "广告系列有效期结束时间", example = "2021-01-06", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm", timezone = "GMT+8")
    @Field(format = DateFormat.custom, pattern = "yyyy-MM-dd", type = FieldType.Date)
    private Date endTime;

    /**
     * content-network
     */
    @ApiModelProperty(value = "网络设置")
    @Field(type = FieldType.Keyword)
    private String contentNetwork;

    /**
     * payment-mode
     */
    @ApiModelProperty(value = "付款方式")
    @Field(type = FieldType.Keyword)
    private String paymentMode;

    /**
     * experiment-type
     */
    @ApiModelProperty(value = "广告活动类型")
    @Field(type = FieldType.Keyword)
    private String experimentType;

    /**
     * tracking-url
     */
    @ApiModelProperty(value = "跟踪设置")
    @Field(type = FieldType.Keyword)
    private String trackingUrl;

    /**
     * tracking-url-template
     */
    @ApiModelProperty(value = "URL模板")
    @Field(type = FieldType.Keyword)
    private String trackingUrlTemplate;

    /**
     * url-custom-parameters
     */
    @ApiModelProperty(value = "参数标签映射列表")
    @Field(type = FieldType.Keyword)
    private String urlCustomParameters;

    /**
     * budget-amount-micros
     */
    @ApiModelProperty(value = "预算金额")
    @Field(type = FieldType.Double)
    private Double budgetAmountMicros;


}
