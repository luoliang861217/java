package test.com.combiner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Counters.Counter;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;

public class MyMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable> {

	public void map(LongWritable key, Text value, org.apache.hadoop.mapred.OutputCollector<Text,LongWritable> output, org.apache.hadoop.mapred.Reporter reporter) throws java.io.IOException {
		if(!value.toString().isEmpty()){
			System.out.println("MyMapper:<" + key+ "," + value +">");
			String[] splited = value.toString().split("\t");
			for (int i = 0; i < splited.length; i++) {
				String word = splited[i];
				output.collect(new Text(word), new LongWritable(1L));
				System.out.println("\tMyMapper.map:<" + word+ "," + 1 +">");
			}
		}
	};
	
}
