package report;

import java.math.*;

//根据等级计算升级所需要的积分值的方法
public class Mi {
	public static double a(int x, int n) {
		if (n == 0)
			return 1;
		else if (n == 1)
			return x;
		else if (isEven(n))
			return a(x * x, n / 2);
		else
			return a(x * x, (n - 1) / 2) * x;

	}

	private static boolean isEven(int n) {
		if (n % 2 == 0)
			return true;
		else
			return false;
	}
}
