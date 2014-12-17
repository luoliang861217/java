package test.com.mapredus.Mobile;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, MobileAPI, Text, MobileAPI> {
	protected void reduce(Text key, java.lang.Iterable<MobileAPI> values,Context context) throws java.io.IOException ,InterruptedException {
		long UpPackNum = 0L;
		long UpDataNum = 0L;
		long DownPackNum = 0L;
		long DownDataNum = 0L;
		
		for (MobileAPI model : values) {
			UpPackNum += model.UpPackNum;
			UpDataNum += model.UpDataNum;
			DownPackNum += model.DownPackNum;
			DownDataNum += model.DownDataNum;
		}
		MobileAPI model = new MobileAPI();
		model.UpPackNum = UpPackNum;
		model.UpDataNum = UpDataNum;
		model.DownPackNum = DownPackNum;
		model.DownDataNum = DownDataNum;
		System.out.println(key);
		context.write(key, model);
	};
}
