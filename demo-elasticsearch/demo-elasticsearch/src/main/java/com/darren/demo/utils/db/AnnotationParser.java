package com.darren.demo.utils.db;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author : darren
 * @date : 2021/6/4
 */
public class AnnotationParser {

    /**
     * 通过注解来组装查询语句,生成查询语句
     * <p>
     * 如果标注的值中带有分隔符号,标识 按集合处理 进行in 区间查分
     *
     * @param object
     * @return
     */
    public static String assembleSqlFromObj(Object object) {

        Table table = object.getClass().getAnnotation(Table.class);

        StringBuffer sbSql = new StringBuffer();
        String tableName = table.value();

        //通用
        sbSql.append("select * from " + tableName + " where 1=1 ");
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            //逐一获取字段的值
            String fieldName = field.getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Column column = field.getAnnotation(Column.class);
                if (!Objects.isNull(column)) {
                    Method method = object.getClass().getMethod(methodName);
                    Object fieldValue = method.invoke(object);
                    if (!Objects.isNull(fieldValue)) {
                        if (fieldValue instanceof String) {
                            String value = fieldValue.toString().trim();
                            //判断参数是不是in 类型参数1,2,3
                            if (value.contains(",")) {
                                String sqlParams = value.replace(",", "").trim();
                                //value 中都是纯数字
                                if (isNum(sqlParams)) {
                                    sbSql.append(" and " + column.value() + " in (" + value + ")");
                                    //如果不是纯数字则需要
                                } else {
                                    String[] spilt = value.split(",");
                                    value = "";
                                    for (int i = 0; i < spilt.length - 1; i++) {
                                        value += "'" + spilt[i] + "',";
                                    }
                                    value += "'" + spilt[spilt.length - 1] + "'";
                                    sbSql.append(" and " + column.value() + " in(" + value + ") ");
                                }
                            } else {
                                if (value.length() > 0) {
                                    sbSql.append(" and " + column.value() + " like '%" + value + "%' ");
                                }
                            }
                        } else {
                            sbSql.append(" and " + column.value() + "=" + fieldValue.toString() + " ");
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return sbSql.toString();
    }


    /**
     * 判断给定的值是不是id类型
     * 1:检查字段名称
     * 2：检查字段值
     *
     * @param target
     * @return
     */
    public static boolean isNum(String target) {
        boolean isNum = false;
        if (target.toLowerCase().contains("id")) {
            isNum = true;
        }
        if (target.matches("\\d+")) {
            isNum = true;
        }
        return isNum;
    }


    public static void main(String[] args) {

        DbDemo demo = new DbDemo().setId("123").setName("34").setPrice(Long.valueOf("1"));
        DbDemo demo2 = new DbDemo().setId("123").setName("test1");
        DbDemo demo3 = new DbDemo().setId("").setName("test1,test2,test3,test4");

        String sqlOfMapping1 = assembleSqlFromObj(demo);
        String sqlOfMapping2 = assembleSqlFromObj(demo2);
        String sqlOfMapping3 = assembleSqlFromObj(demo3);

        System.out.println(sqlOfMapping1);
        System.out.println(sqlOfMapping2);
        System.out.println(sqlOfMapping3);
    }

}
