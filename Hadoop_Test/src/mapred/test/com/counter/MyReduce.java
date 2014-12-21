package test.com.counter;

import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.TestMapCollection.ValWritable;

public class MyReduce extends MapReduceBase implements Reducer<Text, LongWritable, Text, LongWritable> {

	public void reduce(Text key, java.util.Iterator<LongWritable> values, org.apache.hadoop.mapred.OutputCollector<Text,LongWritable> output, org.apache.hadoop.mapred.Reporter reporter) throws java.io.IOException {
		long total = 0L;
		while(values.hasNext()){
			long temp = values.next().get();
			total += temp;
		}
		output.collect(key, new LongWritable(total));
	};
	
}
