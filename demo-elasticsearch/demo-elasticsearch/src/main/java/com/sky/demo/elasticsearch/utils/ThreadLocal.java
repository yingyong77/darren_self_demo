package com.sky.demo.elasticsearch.utils;

import com.sky.demo.elasticsearch.common.dto.ComputeDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : darren
 * @date : 2020/9/28
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ThreadLocal {

    private java.lang.ThreadLocal<SimpleDateFormat> dateFormat = java.lang.ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));


    /**
     * convert to computeDTOS
     *
     * @param computeDTOS computeDTOS
     * @return {@link List<ComputeDTO>}
     */
    @NotNull
    @SneakyThrows
    private List<ComputeDTO> convertToComputeDTOS(List<ComputeDTO> computeDTOS) {

        return computeDTOS.stream().peek(i -> i.setDate(dateFormat.get().parse("2020-09-28"))).collect(Collectors.toList());
    }

}
