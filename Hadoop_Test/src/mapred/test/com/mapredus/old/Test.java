package test.com.mapredus.old;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.lib.HashPartitioner;

public class Test {
	static final String ROOT_PATH = "hdfs://hadoop-master:9000/";
	static final String INPUT_PATH = "hdfs://hadoop-master:9000/oldmapreduce/input/test.txt";
	static final String OUTPUT_PATH = "hdfs://hadoop-master:9000/oldmapreduce/ouput/";
	static final Configuration conf = new Configuration();
	
	public static void main(String[] args) throws Exception {
		DeleteFile();
		
		//1.job
		JobConf job = new JobConf(conf, Test.class);
		//2.设置输入目录
		FileInputFormat.setInputPaths(job, INPUT_PATH);
		job.setInputFormat(TextInputFormat.class);
		
		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		//排序、分组、规约
		job.setPartitionerClass(HashPartitioner.class);
		job.setNumMapTasks(1);
		
		job.setCombinerClass(MyCombiner.class);
		
		job.setReducerClass(MyReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
		
		JobClient.runJob(job);
	}
	static FileSystem getFileSystem() throws Exception{
		return FileSystem.get(new URI(ROOT_PATH), conf);
	}
	
	static void DeleteFile()throws Exception {
		FileSystem fs = getFileSystem();
		if(fs.exists(new Path(OUTPUT_PATH))){
			fs.delete(new Path(OUTPUT_PATH));
		}
	}
	
}
