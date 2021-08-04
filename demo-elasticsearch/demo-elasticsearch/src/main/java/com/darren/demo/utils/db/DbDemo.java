package com.darren.demo.utils.db;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : darren
 * @date : 2021/6/4
 */
@Data
@Accessors(chain = true)
@Table("db_demo")
public class DbDemo {

    @Column("id")
    private String id;

    @Column("name")
    private String name;

    @Column("price")
    private Long price;

}
