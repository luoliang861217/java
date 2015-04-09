package test.com.sort;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class TestReduce extends Reducer<LongWritable, Text, LongWritable, LongWritable> {

	Long num = 1L;
	
	@Override
	protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		System.out.println( "reduce:"+ key.toString());
		context.write(new LongWritable(num), key);
		num++;
	}
	
}
