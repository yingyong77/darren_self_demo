package com.darren.demo.esIndex;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * 账户结构--受众群体
 *
 * @author : darren
 * @date : 2020/1/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "audience")
public class AudienceIndex extends BaseAccountIndex {

    /**
     * audience-id
     */
    @ApiModelProperty(value = "受众群体id", required = true)
    @Field(type = FieldType.Keyword)
    private String audienceId;

    /**
     * audience-name
     */
    @ApiModelProperty(value = "受众群体名称")
    @Field(type = FieldType.Text)
    private String audienceName;
    /**
     * audience-status
     */
    @ApiModelProperty(value = "受众群体状态", example = "有效")
    @Field(type = FieldType.Keyword)
    private String audienceStatus;

    /**
     * of-level
     */
    @ApiModelProperty(value = "所属层级结构", example = "广告系列")
    @Field(type = FieldType.Long)
    private int ofLevel;

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
     * 用户列表-ids
     */
    @ApiModelProperty(value = "用户列表")
    @Field(type = FieldType.Keyword)
    private List<String> userList;

    /**
     * 用户群体类型
     */
    @ApiModelProperty(value = "用户群体类型")
    @Field(type = FieldType.Keyword)
    private String userType;

    /**
     * 用户群体-size
     */
    @ApiModelProperty(value = "用户群体大小", example = "10")
    @Field(type = FieldType.Keyword)
    private String sizeForSearch;

    /**
     * 搜索广告的大小范围
     */
    @ApiModelProperty(value = "搜索广告的大小范围")
    private String userListRangeForSearch;

    /**
     * users-membership-status
     */
    @ApiModelProperty(value = "用户群体状态")
    @Field(type = FieldType.Keyword)
    private String usersMembershipStatus;

    /**
     * final-urls
     */
    @ApiModelProperty(value = "最终到达网址")
    @Field(type = FieldType.Keyword)
    private String finalUrls;
}
