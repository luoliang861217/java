package test.com.grouper;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MyReduce extends Reducer<SortAPI, LongWritable, LongWritable, LongWritable> {

	@Override
	protected void reduce(SortAPI key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
		System.out.println(key.first + "," + key.second);
		context.write(new LongWritable(key.first), new LongWritable(key.second));
		System.out.println(key.first + "," + key.second);
	}
	
}
