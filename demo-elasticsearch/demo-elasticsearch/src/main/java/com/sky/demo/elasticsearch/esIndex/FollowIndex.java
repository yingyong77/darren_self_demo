package com.sky.demo.elasticsearch.esIndex;

/**
 * @author Sky.
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * 跟进
 *
 * @author Sky.
 */
@Data
@Document(indexName = "follow")
public class FollowIndex extends BaseIndex {


    @ApiModelProperty(value = "客户Id", example = "101010101010", required = true)
    @Field(type = FieldType.Keyword)
    private Long customerId;

    @ApiModelProperty(value = "客户名称", example = "我是客户名称")
    @Field(type = FieldType.Text)
    private String customerName;

    @ApiModelProperty(value = "联系人组", example = "我是联系人名称")
    @Field(type = FieldType.Nested)
    private List<ContactsNested> contactsList;

    @ApiModelProperty(value = "业务归类Code", example = "01", required = true)
    @Field(type = FieldType.Keyword)
    private List<String> businessCode;

    @ApiModelProperty(value = "跟进内容（Id）", required = true)
    @Field(type = FieldType.Keyword)
    private List<Integer> content;

    @ApiModelProperty(value = "跟进方式（Id）", required = true)
    @Field(type = FieldType.Keyword)
    private List<Integer> way;

    @ApiModelProperty(value = "跟进类型（Id）", example = "134563", required = true)
    @Field(type = FieldType.Keyword)
    private Integer type;

    @ApiModelProperty(value = "跟进时间", example = "2019-01-01 11:11:11", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm", timezone = "GMT+8")
    @Field(format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss", type = FieldType.Date)
    private Date followTime;

    @ApiModelProperty(value = "跟进反馈、回馈", required = true)
    @Field(type = FieldType.Keyword)
    private String feedback;

    @ApiModelProperty(value = "归属Id", example = "101010101010", required = true)
    @Field(type = FieldType.Keyword)
    private List<Long> affiliationId;

    @ApiModelProperty(value = "创建人Id", example = "101010101010", required = true)
    @Field(type = FieldType.Keyword)
    private Long createUserId;

    @ApiModelProperty(value = "创建人姓名", example = "隔壁翠花")
    @Field(type = FieldType.Keyword)
    private String createUserName;

    @ApiModelProperty(value = "部门Id", example = "101010101010", required = true)
    @Field(type = FieldType.Keyword)
    private Long departmentId;

    @ApiModelProperty(value = "部门名称", example = "XXX部门")
    @Field(type = FieldType.Keyword)
    private String departmentName;

    @ApiModelProperty(value = "跟进文件")
    @Field(type = FieldType.Nested)
    private List<FileNested> fileList;

    @ApiModelProperty(value = "删除状态")
    @Field(type = FieldType.Boolean)
    private boolean delete;

}
