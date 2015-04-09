package test.com.sort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;



public class TestMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
		
	Text text = new Text("");
	@Override
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		System.out.println( "map:key:"+ key.toString() + ";value:" +  value.toString().trim());
		LongWritable val = new LongWritable(Long.valueOf(value.toString().trim()));
		context.write(val, text);
	}
}
