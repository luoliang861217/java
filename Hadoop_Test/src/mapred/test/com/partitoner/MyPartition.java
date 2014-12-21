package test.com.partitoner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

/**
 * �Զ��������
 * @author Liang
 *
 */
public class MyPartition extends HashPartitioner<Text, MobileAPI> {
	@Override
	public int getPartition(Text key, MobileAPI value, int numReduceTasks) {
		return (isMobileNO(key.toString())) ? 0 : (isIPNO(key.toString()) ? 1 : 2);
	}

	/**
	 * �Ƿ��ֻ�����
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles){
		boolean result = false;
		Pattern p = Pattern.compile("^0?1[3458]\\d{9}$");
		Matcher m = p.matcher(mobiles);
		result = m.matches();
		System.out.println("key:" + mobiles +" = " + result);
		return result;
	}
	
	/**
	 * �Ƿ�IP��ַ
	 * @param mobiles
	 * @return
	 */
	public static boolean isIPNO(String mobiles){
		boolean result = false;
		Pattern p = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");
		Matcher m = p.matcher(mobiles);
		result = m.matches();
		System.out.println("key:" + mobiles +" = " + result);
		return result;
	}	
}
