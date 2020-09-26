package com.sky.demo.elasticsearch.esIndex;

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
 * @author Sky.
 */
@Data
@Document(indexName = "cid-daily-settlement")
public class CidDailySettlementIndex {

    @ApiModelProperty(value = "主键Id")
    @Id
    private String id;

    @ApiModelProperty(value = "创建时间", example = "2017-01-01 11:11:11")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Field(format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss", type = FieldType.Date)
    private Date createTime;


    @ApiModelProperty(value = "业务线Code", example = "02", required = true)
    @Field(type = FieldType.Keyword)
    private String businessCode;

    @ApiModelProperty(value = "cid", example = "101010101010", required = true)
    @Field(type = FieldType.Keyword)
    private String cid;

    @ApiModelProperty(value = "cid 主键ID", example = "101010101010", required = true)
    @Field(type = FieldType.Keyword)
    private Long cidId;

    @ApiModelProperty(value = "充值", example = "100.01", required = true)
    @Field(type = FieldType.Double)
    private Double recharge;

    @ApiModelProperty(value = "退款", example = "100.01", required = true)
    @Field(type = FieldType.Double)
    private Double refund;

    @ApiModelProperty(value = "消耗", example = "100.01", required = true)
    @Field(type = FieldType.Double)
    private Double cost;

    @ApiModelProperty(value = "other", example = "100.01", required = true)
    @Field(type = FieldType.Double)
    private Double other;

    @ApiModelProperty(value = "日终结余", example = "100.01", required = true)
    @Field(type = FieldType.Double)
    private Double balance;

    @ApiModelProperty(value = "统计时间", example = "2019-01-01", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Field(format = DateFormat.custom, pattern = "yyyy-MM-dd", type = FieldType.Date)
    private Date statDate;

    @ApiModelProperty(value = "删除状态")
    @Field(type = FieldType.Boolean)
    private boolean delete;

}
