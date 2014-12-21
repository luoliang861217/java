package test.com.partitoner;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;

/**
 * 此实例必须导出jar仍是服务器 hadoop jar jar.jar执行
 * @author Liang
 *
 */
public class Test {
	
	static final String OUTPUT_DIR = "hdfs://hadoop-master:9000/partitioner/output/";
	static final String INPUT_DIR = "hdfs://hadoop-master:9000/partitioner/input/test.txt";
	
	public static void main(String[] args) throws Exception {
		
		FileSystem fs = getFileSystem();
		if(fs.exists(new Path(OUTPUT_DIR))){
			fs.delete(new Path(OUTPUT_DIR));
		}
		Configuration conf = new Configuration();
		Job job = new Job(conf,Test.class.getSimpleName());
		
		job.setJarByClass(Test.class);
		
		FileInputFormat.setInputPaths(job, INPUT_DIR);
		job.setInputFormatClass(TextInputFormat.class);
		
		job.setMapperClass(Mapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(MobileAPI.class);
		
		job.setPartitionerClass(MyPartition.class);
		job.setNumReduceTasks(3);
		
		job.setReducerClass(Reduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(MobileAPI.class);
		
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_DIR));
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.waitForCompletion(true);
	}
	
	static FileSystem getFileSystem() throws Exception{
		return FileSystem.get(new URI(INPUT_DIR), new Configuration());
	}
}
