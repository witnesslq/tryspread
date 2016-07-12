import org.joda.time.DateTime;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package PACKAGE_NAME
 * @date 2016/7/12
 * @Description: ${todo}
 */
public class DateToolTest {
    @Test
    public void demo1() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(date);
        System.out.println(s);

        Date date1 = new DateTime().minusDays(1).toDate();
        String s1 = dateFormat.format(date1);
        System.out.println(s1);
    }
}
