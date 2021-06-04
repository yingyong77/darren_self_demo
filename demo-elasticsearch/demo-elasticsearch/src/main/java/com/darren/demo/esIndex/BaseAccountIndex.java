package com.darren.demo.esIndex;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 账户结构-base-index
 *
 * @author Darren
 * @date 2020-01-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class BaseAccountIndex extends BaseIndex {

    /**
     * cid
     */
    @ApiModelProperty(value = "cid", required = true)
    @Field(type = FieldType.Keyword)
    private String account;

    /**
     * cid-name
     */
    @ApiModelProperty(value = "cid-name", required = true)
    @Field(type = FieldType.Text)
    private String accountName;

    /**
     * business-code
     */
    @ApiModelProperty(value = "业务线", example = "01", required = true)
    @Field(type = FieldType.Keyword)
    private String businessCode;

    /**
     * company-code
     */
    @ApiModelProperty(value = "公司", example = "01", required = true)
    @Field(type = FieldType.Keyword)
    private String companyCode;

    /**
     * mcc-parentId
     */
    @ApiModelProperty(value = "归属主MCC Id", example = "111112121212", required = true)
    @Field(type = FieldType.Keyword)
    private String mccParentId;

    /**
     * 客户ID
     */
    @ApiModelProperty(value = "customerId", example = "00", required = true)
    @Field(type = FieldType.Keyword)
    private Long customerId;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "customerName", example = "没错~我就是客户名称", required = true)
    @Field(type = FieldType.Keyword)
    private String customerName;

    /**
     * 客服ID
     */
    @ApiModelProperty(value = "employeeId", example = "00", required = true)
    @Field(type = FieldType.Keyword)
    private Long employeeId;

    /**
     * 币种
     */
    @ApiModelProperty(value = "币种")
    @Field(index = false, type = FieldType.Keyword)
    private String currency;

    /**
     * 备注
     */
    @ApiModelProperty(value = "remark", example = "备注")
    @Field(type = FieldType.Text)
    private String remark;

    /**
     * 快照时间
     */
    @ApiModelProperty(value = "快照时间", example = "2020-01-03", required = true)
    @Field(format = DateFormat.year_month_day, type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date snapshotDate;

    /**
     * 删除状态
     */
    @ApiModelProperty(value = "删除状态", required = true)
    @Field(type = FieldType.Boolean)
    private boolean delete;
}
