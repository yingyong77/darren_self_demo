package com.darren.demo.esIndex;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 文件
 *
 * @author Sky.
 */

@Data
public class FileNested {


    @ApiModelProperty(value = "文件名", example = "xxx.avi")
    @Field(type = FieldType.Keyword)
    private String name;

    @ApiModelProperty(value = "oss文件名", example = "学习资料.avi")
    @Field(type = FieldType.Keyword)
    private String ossName;
}
