package com.sky.demo.elasticsearch.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author : darren
 * @date : 2020/9/28
 */
@Data
@Accessors
@EqualsAndHashCode(callSuper = true)
public class ComputeDTO extends BaseModel<Long> {

    @ApiModelProperty(value = "生产日期", example = "2020-09-28", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull
    private Date date;
}
