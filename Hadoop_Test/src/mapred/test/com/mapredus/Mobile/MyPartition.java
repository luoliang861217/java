package test.com.mapredus.Mobile;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class MyPartition extends HashPartitioner<Text, MobileAPI> {
	@Override
	public int getPartition(Text key, MobileAPI value, int numReduceTasks) {
		return (key.toString().length() == 11) ? 0 : 1;
	}
}
