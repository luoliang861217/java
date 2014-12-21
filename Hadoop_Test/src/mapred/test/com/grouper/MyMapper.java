package test.com.grouper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;



public class MyMapper extends Mapper<LongWritable, Text, SortAPI, LongWritable> {
	
//	3 3
//	3 2
//	3 1
//	2 2
//	2 1
//	1 1
	
	@Override
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		String[] splied = value.toString().split("\t");
		try {
			long first = Long.parseLong(splied[0]);
			long second = Long.parseLong(splied[1]);
			context.write(new SortAPI(first,second), new LongWritable(1));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
