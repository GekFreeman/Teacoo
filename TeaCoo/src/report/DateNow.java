package report;
import java.util.*;
import java.text.*;

public class DateNow {
	public static int[] DatePrint() {
		int[] a =new int[3];
		GregorianCalendar calendar = new GregorianCalendar();
		// ȡ����ǰ���꣬�£���
		 a[0] = calendar.get(calendar.YEAR);
		// �µ���ֵ��1��ʹ֮���ϰ�ߵ��·ݴ�С��1��12�£�
		a[1] = calendar.get(calendar.MONTH) + 1;
		a[2] = calendar.get(calendar.DAY_OF_MONTH);

		// �����ǰ����,��,�ռ����ڵĸ�ʽ����
		return a;
	}

//	public static String TimePrint() {
//		Date d = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");// ʱ:��
//		return (sdf.format(d));
//	}

}
