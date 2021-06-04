package com.darren.demo.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : darren
 * @date : 2020/9/28
 */
@Data
public class BaseModel<T> implements Serializable {

    @ApiModelProperty(value = "主键Id", example = "123456")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private T id;

    @ApiModelProperty(value = "创建时间,只读字段", example = "2017-01-01 11:11:11", readOnly = true)
    @JsonIgnore
    private Date createTime;

    @ApiModelProperty(value = "删除状态")
    private boolean deleted;
}
