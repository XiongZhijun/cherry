/*
 * 香港摩比科技有限公司 版权所有
 *
 * www.mobi-inf.com
 */
package os.cherry.android.lbs;

/**
 * @author Xiong Zhijun
 * 
 */
public class LocationTools {
	static double DEF_PI = 3.14159265359; // PI
	static double DEF_2PI = 6.28318530712; // 2*PI
	static double DEF_PI180 = 0.01745329252; // PI/180.0
	static double DEF_R = 6370693.5; // radius of earth

	/**
	 * 计算地图上两个点之间的距离
	 * 
	 * @param location1
	 * @param location2
	 * @return
	 */
	public static double calculateDistance(Location location1,
			Location location2) {
		if (location1 == null || location2 == null) {
			return Double.MAX_VALUE;
		}
		return calculateDistance(location1.getLatitude(),
				location1.getLongitude(), location2.getLatitude(),
				location2.getLongitude());
	}

	/**
	 * 计算地图上两个点之间的距离
	 * 
	 * @param latitude1
	 *            点1的纬度
	 * @param longitude1
	 *            点1的经度
	 * @param latitude2
	 *            点2的纬度
	 * @param longitude2
	 *            点2的经度
	 * @return
	 */
	public static double calculateDistance(double latitude1, double longitude1,
			double latitude2, double longitude2) {
		double ew1, ns1, ew2, ns2;
		double distance;
		// 角度转换为弧度
		ew1 = longitude1 * DEF_PI180;
		ns1 = latitude1 * DEF_PI180;
		ew2 = longitude2 * DEF_PI180;
		ns2 = latitude2 * DEF_PI180;
		// 求大圆劣弧与球心所夹的角(弧度)
		distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1)
				* Math.cos(ns2) * Math.cos(ew1 - ew2);
		// 调整到[-1..1]范围内，避免溢出
		if (distance > 1.0)
			distance = 1.0;
		else if (distance < -1.0)
			distance = -1.0;
		// 求大圆劣弧长度
		distance = DEF_R * Math.acos(distance);
		return distance;
	}

}
