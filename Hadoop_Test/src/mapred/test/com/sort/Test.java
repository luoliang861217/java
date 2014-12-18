package test.com.sort;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;

public class Test {
	static final String OUTPUT_DIR = "hdfs://hadoop-master:9000/output/";
	static final String INPUT_DIR = "hdfs://hadoop-master:9000/input/sort.txt";
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = new Job(conf, Test.class.getSimpleName());		
		deleteOutputFile(OUTPUT_DIR);
		
		//1��������Ŀ¼
		FileInputFormat.setInputPaths(job, INPUT_DIR);
		//2���������ʽ����
		job.setInputFormatClass(TextInputFormat.class);
		//3�����Զ���Mapper�Լ���ֵ����
		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(SortAPI.class);
		job.setMapOutputValueClass(LongWritable.class);
		//4����
		job.setPartitionerClass(HashPartitioner.class);
		job.setNumReduceTasks(1);
		
		//5�������
		//6������һ��Reduce�Լ���ֵ����
		job.setReducerClass(MyReduce.class);
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(LongWritable.class);
		//7�������Ŀ¼
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_DIR));
		//8�ύjob
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

