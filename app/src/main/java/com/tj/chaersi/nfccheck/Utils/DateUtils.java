package com.tj.chaersi.nfccheck.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chaersi on 17/1/11.
 */
public class DateUtils {

    /**
     * 格式化时间  MM/dd
     * @param stamp
     * @return
     */
    public static String convertTime(long stamp) {
        SimpleDateFormat time=new SimpleDateFormat("MM/dd");
        return time.format(new Date(stamp));
    }

    public static String convertDate(long stamp) {
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
        return time.format(new Date(stamp));
    }

}
