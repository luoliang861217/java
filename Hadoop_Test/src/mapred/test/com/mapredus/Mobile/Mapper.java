package test.com.mapredus.Mobile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class Mapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, MobileAPI> {
	protected void map(LongWritable key, Text value, Context context) throws java.io.IOException ,InterruptedException {
		if(!isPartition){
			String[] splited = value.toString().split("\t");
			String mobile =	splited[2];
			if(mobile.indexOf(".") < 0 && isMobileNO(mobile) && splited.length > 26){
				try {
					String UpPackNum = splited[21];
					String UpDataNum = splited[23];
					String DownPackNum = splited[22];
					String DownDataNum = splited[24];
					String status = splited[25];
					
					if(status.equals("200")){
						System.out.println("status��" + status);
						MobileAPI model = new MobileAPI(UpPackNum, UpDataNum, DownPackNum, DownDataNum);
						context.write(new Text(mobile), model);
					}
				} catch (Exception e) {
					System.out.println("Map�����쳣��" + e.getMessage());
				}
			}
			else{
				System.out.println("Map�����쳣���������Ȳ�����" + splited.length + ";mobile:" + mobile);
			}
		}
		else{
			String[] splited = value.toString().split("\t");
			String mobile =	splited[2];
			if(splited.length > 26){
				try {
					String UpPackNum = splited[21];
					String UpDataNum = splited[23];
					String DownPackNum = splited[22];
					String DownDataNum = splited[24];
					String status = splited[25];
					
					if(status.equals("200")){
						System.out.println("status��" + status);
						MobileAPI model = new MobileAPI(UpPackNum, UpDataNum, DownPackNum, DownDataNum);
						context.write(new Text(mobile), model);
					}
				} catch (Exception e) {
					System.out.println("Map�����쳣��" + e.getMessage());
				}
			}
			else{
				System.out.println("Map�����쳣���������Ȳ�����" + splited.length + ";mobile:" + mobile);
			}
		}

		

		
	};
	
	public static boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches()+"---");
		return m.matches();
	}
	
	private boolean isPartition = false;
}
