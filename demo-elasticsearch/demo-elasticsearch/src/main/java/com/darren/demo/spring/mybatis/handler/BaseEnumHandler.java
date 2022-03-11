package com.darren.demo.spring.mybatis.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author Sky.
 */
public class BaseEnumHandler<E extends BaseEnum> extends BaseTypeHandler<E> {

    private Class<E> type;

    public BaseEnumHandler() {
    }

    public BaseEnumHandler(Class<E> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getKey());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getEnum(rs.getString(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getEnum(rs.getString(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getEnum(cs.getString(columnIndex));
    }

    private E getEnum(String value) {

        if (StringUtils.isEmpty(value)) {
            return null;
        }

        return Arrays.stream(type.getEnumConstants())
                .filter(item -> value.equals(item.getKey().toString()))
                .findFirst()
                .orElse(null);
    }


}
