package com.darren.demo.utils;

import com.darren.demo.common.dto.ComputeDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.ParseException;
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
    //another写法 java8.0 DateTimeFormatter
    private java.lang.ThreadLocal<DateFormat> dateFormatThreadLocal = new java.lang.ThreadLocal<DateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
      
    private java.lang.ThreadLocal<SimpleDateFormat> dateFormat = java.lang.ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));


    /**
     * convert to computeDTOS
     *
     * @param computeDTOS computeDTOS
     * @return {@link List< ComputeDTO >}
     */
    @NotNull

    private List<ComputeDTO> convertToComputeDTOS(List<ComputeDTO> computeDTOS) {

        return computeDTOS.stream().peek(i -> {
            try {
                i.setDate(dateFormat.get().parse("2020-09-28"));

                String date = dateFormat.get().format(i.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }).collect(Collectors.toList());
    }

}
