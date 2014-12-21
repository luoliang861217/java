package test.com.partitoner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class Mapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, MobileAPI> {
	protected void map(LongWritable key, Text value, Context context) throws java.io.IOException ,InterruptedException {
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
//					System.out.println("status：" + status);
					MobileAPI model = new MobileAPI(UpPackNum, UpDataNum, DownPackNum, DownDataNum);
					context.write(new Text(mobile), model);
				}
			} catch (Exception e) {
//				System.out.println("Map函数异常：" + e.getMessage());
			}
		}
		else{
//			System.out.println("Map函数异常：函数长度不够：" + splited.length + ";mobile:" + mobile);
		}		
	};
}
