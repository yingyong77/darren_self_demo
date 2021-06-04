package com.darren.demo.esIndex;

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
 * 账户结构--附加信息
 *
 * @author : darren
 * @date : 2020/1/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "extension-feed")
public class ExtensionFeedIndex extends BaseAccountIndex {

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
     * targeted-keyword
     */
    @ApiModelProperty(value = "目标关键字", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String targetedKeyword;

    /**
     * status
     */
    @ApiModelProperty(value = "状态")
    @Field(type = FieldType.Keyword)
    private String status;

    /**
     * status
     */
    @ApiModelProperty(value = "附加信息类型")
    @Field(type = FieldType.Keyword)
    private String extensionType;

    /**
     * final-urls
     */
    @ApiModelProperty(value = "目标网址", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String finalUrls;

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
     * of-level
     */
    @ApiModelProperty(value = "所属层级", example = "广告系列")
    @Field(type = FieldType.Long)
    private int ofLevel;

    /**
     * start-time
     */
    @ApiModelProperty(value = "附加信息有效期开始时间", example = "2020-01-06")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm", timezone = "GMT+8")
    @Field(format = DateFormat.custom, pattern = "yyyy-MM-dd", type = FieldType.Date)
    private Date startTime;

    /**
     * end-time
     */
    @ApiModelProperty(value = "附加信息有效期结束时间", example = "2021-01-06", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm", timezone = "GMT+8")
    @Field(format = DateFormat.custom, pattern = "yyyy-MM-dd", type = FieldType.Date)
    private Date endTime;
}
