package com.sky.demo.elasticsearch.esIndex;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * 账户结构--广告
 *
 * @author : darren
 * @date : 2020/1/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "advertisement")
public class AdvertisementIndex extends BaseAccountIndex {

    /**
     * ad-id
     */
    @ApiModelProperty(value = "广告id", required = true)
    @Field(type = FieldType.Keyword)
    private String adId;

    /**
     * ad-name
     */
    @ApiModelProperty(value = "广告名称")
    @Field(type = FieldType.Text)
    private String adName;

    /**
     * type
     */
    @ApiModelProperty(value = "所属类型")
    @Field(type = FieldType.Keyword)
    private String type;

    /**
     * text-ad-description1
     */
    @ApiModelProperty(value = "文本类型广告描述1")
    @Field(type = FieldType.Keyword)
    private String textAdDescription1;

    /**
     * text-ad-description2
     */
    @ApiModelProperty(value = "文本类型广告描述2")
    @Field(type = FieldType.Keyword)
    private String textAdDescription2;

    /**
     * text-ad-headline
     */
    @ApiModelProperty(value = "文本类型广告标题")
    @Field(type = FieldType.Keyword)
    private String textAdHeadline;

    /**
     * expanded-text-ad-description
     */
    @ApiModelProperty(value = "加大型文本类型广告描述")
    private String expandAdTextAdDescription;

    /**
     * expanded-text-ad-description2
     */
    @ApiModelProperty(value = "加大型文本类型广告描述2")
    private String expandAdTextAdDescription2;

    /**
     * expanded-text-ad-headline-part1
     */
    @ApiModelProperty(value = "加大型文本类型广告标题1")
    private String expandAdTextAdHeadlinePart1;

    /**
     * expanded-text-ad-headline-part1
     */
    @ApiModelProperty(value = "加大型文本类型广告标题2")
    private String expandAdTextAdHeadlinePart2;

    /**
     * expanded-text-ad-headline-part3
     */
    @ApiModelProperty(value = "加大型文本类型广告标题3")
    private String expandAdTextAdHeadlinePart3;

    /**
     * expanded-text-ad-path1
     */
    @ApiModelProperty(value = "加大型文本类型path1")
    private String expandAdTextAdPath1;

    /**
     * expanded-text-ad-path1
     */
    @ApiModelProperty(value = "加大型文本类型path2")
    private String expandAdTextAdPath2;

    /**
     * responsive-search-ad-description
     */
    @ApiModelProperty(value = "自适应搜索广告描述")
    @Field(type = FieldType.Keyword)
    private List<String> responsiveSearchAdDescriptions;

    /**
     * responsive-search-ad-headlines
     */
    @ApiModelProperty(value = "自适应搜索广告标题")
    @Field(type = FieldType.Keyword)
    private List<String> responsiveSearchAdHeadlines;

    /**
     * responsive-search-ad-path1
     */
    @ApiModelProperty(value = "自适应搜索广告path1")
    @Field(type = FieldType.Keyword)
    private String responsiveSearchAdPath1;
    /**
     * responsive-search-ad-path2
     */
    @ApiModelProperty(value = "自适应搜索广告path2")
    @Field(type = FieldType.Keyword)
    private String responsiveSearchAdPath2;

    /**
     * 是否自动化添加
     */
    @ApiModelProperty(value = "是否自动化添加")
    @Field(type = FieldType.Boolean)
    private boolean addedByGoogleAds;

    /**
     * final-urls
     */
    @ApiModelProperty(value = "最终到达网址")
    @Field(type = FieldType.Keyword)
    private List<String> finalUrls;

    /**
     * display-urls
     */
    @ApiModelProperty(value = "显示的url")
    @Field(type = FieldType.Keyword)
    private String displayUrl;

    /**
     * ad-status
     */
    @ApiModelProperty(value = "广告状态")
    @Field(type = FieldType.Keyword)
    private String adStatus;

    /**
     * cpc-bid-micros
     */
    @ApiModelProperty(value = "最高每次点击费用出价")
    @Field(type = FieldType.Double)
    private Double cpcBidMicros;

    /**
     * approval-status
     */
    @ApiModelProperty(value = "审批状态")
    @Field(type = FieldType.Keyword)
    private String approvalStatus;

    /**
     * policy-review-status
     */
    @ApiModelProperty(value = "策略审查状态")
    @Field(type = FieldType.Keyword)
    private String policyReviewStatus;

    /**
     * ad-group-id
     */
    @ApiModelProperty(value = "ad group id")
    @Field(type = FieldType.Keyword)
    private String adGroupId;

    /**
     * ad-group-name
     */
    @ApiModelProperty(value = "ad group id")
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
}
