package report;
import java.util.*;
import java.text.*;

public class DateNow {
	public static int[] DatePrint() {
		int[] a =new int[3];
		GregorianCalendar calendar = new GregorianCalendar();
		// 取出当前的年，月，日
		 a[0] = calendar.get(calendar.YEAR);
		// 月的数值加1，使之变成习惯的月份大小（1～12月）
		a[1] = calendar.get(calendar.MONTH) + 1;
		a[2] = calendar.get(calendar.DAY_OF_MONTH);

		// 输出当前的年,月,日及星期的格式样本
		return a;
	}

//	public static String TimePrint() {
//		Date d = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");// 时:分
//		return (sdf.format(d));
//	}

}
