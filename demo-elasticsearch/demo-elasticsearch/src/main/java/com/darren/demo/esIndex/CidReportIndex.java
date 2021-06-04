package com.darren.demo.esIndex;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 每日报表
 *
 * @author sky
 */
@Data
@Document(indexName = "cid-report")
public class CidReportIndex {

    @ApiModelProperty(value = "主键Id")
    @Id
    private String id;

    @ApiModelProperty(value = "创建时间", example = "2017-01-01 11:11:11")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Field(format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss", type = FieldType.Date)
    private Date createTime;

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
     * 客户ID
     */
    @ApiModelProperty(value = "employeeId", example = "00", required = true)
    @Field(type = FieldType.Keyword)
    private Long employeeId;


    @ApiModelProperty(value = "cid 媒体名称", example = "00")
    @Field(index = false, type = FieldType.Keyword)
    private String cidName;

    @ApiModelProperty(value = "覆盖人数")
    @Field(index = false, type = FieldType.Long)
    private int covers;


    @ApiModelProperty(value = "业务线", example = "00", required = true)
    @Field(type = FieldType.Keyword)
    private String businessId;

    @ApiModelProperty(value = "公司", example = "00", required = true)
    @Field(type = FieldType.Keyword)
    private String companyId;

    @ApiModelProperty(value = "mcc", example = "0", required = true)
    @Field(type = FieldType.Keyword)
    private String mccId;


    @ApiModelProperty(value = "归属主MCC Id", example = "0", required = true)
    @Field(type = FieldType.Keyword)
    private String mainMCCId;

    @ApiModelProperty(value = "归属主MCC", example = "skytech_mcc@skytecn.cn", required = true)
    @Field(type = FieldType.Keyword)
    private String mainMCC;

    @ApiModelProperty(value = "cid", required = true)
    @Field(type = FieldType.Keyword)
    private String cid;

    @ApiModelProperty(value = "报表时间", example = "2017-07-07", required = true)
    @Field(format = DateFormat.year_month_day, type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    @ApiModelProperty(value = "展现量")
    @Field(index = false, type = FieldType.Long)
    private int impressions;

    @ApiModelProperty(value = "点击量")
    @Field(index = false, type = FieldType.Long)
    private int clicks;

    @ApiModelProperty(value = "消耗")
    @Field(index = false, type = FieldType.Double)
    private double cost;

    @ApiModelProperty(value = "汇率 折算消耗")
    @Field(index = false, type = FieldType.Double)
    private double exchangeRateCost;

    @ApiModelProperty(value = "转化")
    @Field(index = false, type = FieldType.Double)
    private double conversion;

    @ApiModelProperty(value = "币种")
    @Field(index = false, type = FieldType.Keyword)
    private String currency;

}