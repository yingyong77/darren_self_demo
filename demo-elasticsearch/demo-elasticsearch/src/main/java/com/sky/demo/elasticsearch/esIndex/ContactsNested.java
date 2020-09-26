package com.sky.demo.elasticsearch.esIndex;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 联系人
 *
 * @author Sky.
 */
@Data
public class ContactsNested {

    @ApiModelProperty(value = "主键Id")
    @Field(type = FieldType.Keyword)
    private String id;

    @ApiModelProperty(value = "联系人名称", example = "我是联系人名称")
    @Field(type = FieldType.Keyword)
    private String name;
}
