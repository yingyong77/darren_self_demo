package com.sky.demo.elasticsearch.esIndex;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 账户结构--否定关键词
 *
 * @author : darren
 * @date : 2020/1/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "negative-keywords")
public class NegativeKeywordsIndex extends BaseAccountIndex {

    /**
     * negative-keywords-id
     */
    @ApiModelProperty(value = "否定关键字id", example = "1666282296")
    @Field(type = FieldType.Keyword)
    private String negativeKeywordId;

    /**
     * negative-keywords-name
     */
    @ApiModelProperty(value = "否定关键字名称", example = "主推词")
    @Field(type = FieldType.Text)
    private String negativeKeywordName;

    /**
     * match-type
     */
    @ApiModelProperty(value = "匹配类型")
    @Field(type = FieldType.Keyword)
    private String matchType;

    /**
     * of-level
     */
    @ApiModelProperty(value = "所属层级", example = "广告系列")
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
    @ApiModelProperty(value = "ad group id")
    @Field(type = FieldType.Keyword)
    private String adGroupId;

    /**
     * ad-group-name
     */
    @ApiModelProperty(value = "ad group name")
    @Field(type = FieldType.Text)
    private String adGroupName;


}
