package com.sky.demo.elasticsearch.esIndex;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * Model基类
 *
 * @author Sky.
 */
@Data
public class BaseIndex implements Serializable {

    @ApiModelProperty(value = "主键Id")
    @Id
    private String id;

    @ApiModelProperty(value = "创建时间", example = "2017-01-01 11:11:11")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Field(format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss", type = FieldType.Date)
    private Date createTime;

}