package test.com.hdfs;


import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

/**
 * url��ʽ����hadoop
 * @author Administrator
 *
 */
public class UrlTest {

	static final String PATH = "hdfs://hadoop-master:9000/data/test.txt";
	
	public static void  Test() throws Exception {

//		unknown protocol: hdfs
//		����취��ָ��urlΪhdfsurl
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
		
		URL url = new URL(PATH);
		InputStream in = url.openStream();
		IOUtils.copyBytes(in, System.out, 1024, true);
		
		
	}
	
}
