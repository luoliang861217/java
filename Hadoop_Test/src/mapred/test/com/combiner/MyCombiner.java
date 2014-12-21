package test.com.combiner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Reducer;

public class MyCombiner extends MapReduceBase implements Reducer<Text, LongWritable, Text, LongWritable> {

	public void reduce(Text key, java.util.Iterator<LongWritable> values, org.apache.hadoop.mapred.OutputCollector<Text,LongWritable> output, org.apache.hadoop.mapred.Reporter reporter) throws java.io.IOException {
		System.out.println("MyCombiner:<" + key+ ", ... >");
		long total = 0L;
		while(values.hasNext()){
			long temp = values.next().get();
			total += temp;
		}
		System.out.println("\tMyCombiner.reduce:<" + key+ "," + total +">");
		output.collect(key, new LongWritable(total));
	};
	
}
