package com.darren.demo.utils.db;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 分表下标工具类(按月划分)
 *
 * @author lizhiqiang
 * @date 2020年12月23日 14:32
 */
public class SubTableIndexUtils {

    // 分表个数
    private static final int TABLE_COUNT = 1 << 2;

    // 判断是否是2^n
    private static final boolean IS_TABLE_COUNT_SQUARE_OF_TWO = (TABLE_COUNT & (TABLE_COUNT - 1)) == 0;

    /**
     * 获取全部的分表下标集合(下标按数据时间顺序排列)
     *
     * @author lizhiqiang
     * @date 2020/12/23 14:37
     */
    public static List<Integer> getTotalIndexList() {
        int currentIndex = getCurrentIndex();

        List<Integer> indexList = new ArrayList<>();
        for (int i = 1; i <= TABLE_COUNT; i++) {
            indexList.add(calRemainder(currentIndex + i));
        }

        return indexList;
    }

    /**
     * 获取指定时间范围的分表下标(下标按数据时间顺序排列)
     *
     * @author lizhiqiang
     * @date 2020/12/23 14:37
     */
    public static List<Integer> getIndexList(Date beginDate, Date endDate) {

//        int currentMonth = LocalDate.now().getMonthValue();
//
//        int startMonth = currentMonth - TABLE_COUNT + 1;
//
//        int endMonth = currentMonth;
//
//        if (beginDate != null) {
//            int queryBeginMonth = LocalDateTime.ofInstant(beginDate.toInstant(), ZoneId.systemDefault()).getMonthValue();
//            if (queryBeginMonth > startMonth) {
//                startMonth = queryBeginMonth;
//            }
//        }
//
//        if (endDate != null) {
//            int queryEndMonth = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()).getMonthValue();
//            if (queryEndMonth < endMonth) {
//                endMonth = queryEndMonth;
//            }
//        }
//
//        List<Integer> indexList = new ArrayList<>();
//
//        for (int i = startMonth; i <= endMonth; i++) {
//            indexList.add(getIndex(i));
//        }

        LocalDateTime end = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime start = end.minus(TABLE_COUNT - 1, ChronoUnit.MONTHS);


        if (!Objects.isNull(beginDate)) {
            LocalDateTime localDateBegin = LocalDateTime.ofInstant(beginDate.toInstant(), ZoneId.systemDefault());
            if (start.isBefore(localDateBegin)) {
                start = localDateBegin;
            }
        }

        if (!Objects.isNull(endDate)) {
            LocalDateTime localDateEnd = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
            if (localDateEnd.isBefore(end)) {
                end = localDateEnd;
            }
        }

        List<Integer> indexList = new ArrayList<>();
        for (LocalDateTime date = start; (date.isBefore(end) || (date.getYear() == end.getYear() && date.getMonthValue() == end.getMonthValue())); date = date.plusMonths(1)) {

            indexList.add(getIndex(date.getMonthValue()));
        }
        return indexList;

    }

    /**
     * 获取分表下标
     *
     * @author lizhiqiang
     * @date 2020/12/23 14:37
     */
    public static int getCurrentIndex() {
        return calRemainder(LocalDate.now().getMonthValue());
    }

    /**
     * 获取指定月份的分表下标
     *
     * @param month 指定月份
     * @author lizhiqiang
     * @date 2020/12/23 14:42
     */
    public static int getIndex(int month) {
        return calRemainder(month);
    }

    /**
     * 求余
     *
     * @param number 待求余的数
     * @author lizhiqiang
     * @date 2020/12/23 14:42
     */
    private static int calRemainder(int number) {
        // 2^n
        if (IS_TABLE_COUNT_SQUARE_OF_TWO) {
            return number & (TABLE_COUNT - 1);
        }

        return number % TABLE_COUNT;
    }

}
