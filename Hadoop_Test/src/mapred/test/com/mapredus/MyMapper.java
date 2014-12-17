package test.com.mapredus;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;



public class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
	
	@Override
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		String[] splied = value.toString().split(" ");
		for (int i = 0; i < splied.length; i++) {
			String lineWord = splied[i];
			context.write(new Text(lineWord), new LongWritable(1));
		}
	}
	
}
