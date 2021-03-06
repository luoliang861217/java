package test.com.sort;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;


public class Test {
	static final String OUTPUT_DIR = "hdfs://hadoop-master:9000/sort/output/";
	static final String INPUT_DIR = "hdfs://hadoop-master:9000/sort/input/test.txt";
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = new Job(conf, Test.class.getSimpleName());		
		deleteOutputFile(OUTPUT_DIR);
		
		//1设置输入目录
		FileInputFormat.setInputPaths(job, INPUT_DIR);
		//2设置输入格式化类
		job.setInputFormatClass(TextInputFormat.class);
		//3设置自定义Mapper以及键值类型
//		job.setMapperClass(MyMapper.class);
//		job.setMapOutputKeyClass(SortAPI.class);
//		job.setMapOutputValueClass(LongWritable.class);
//		//4分区
//		job.setPartitionerClass(HashPartitioner.class);
//		job.setNumReduceTasks(1);
//		
//		//5排序分组
//		//6设置在一定Reduce以及键值类型
//		job.setReducerClass(MyReduce.class);
//		job.setOutputKeyClass(LongWritable.class);
//		job.setOutputValueClass(LongWritable.class);
		
		
		//3设置自定义Mapper以及键值类型
		job.setMapperClass(TestMapper.class);
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);
		//4分区
		job.setPartitionerClass(HashPartitioner.class);
		job.setNumReduceTasks(1);
		
		//5排序分组
		//6设置在一定Reduce以及键值类型
		job.setReducerClass(TestReduce.class);
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(LongWritable.class);
		
		//7设置输出目录
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_DIR));
		//8提交job
		job.waitForCompletion(true);
	}
	
	static void deleteOutputFile(String path) throws Exception{
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI(INPUT_DIR),conf);
		if(fs.exists(new Path(path))){
			fs.delete(new Path(path));
		}
	}
}

