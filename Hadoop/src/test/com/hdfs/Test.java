package test.com.hdfs;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

public class Test {

	static final String PATH = "hdfs://hadoop-master:9000/data/test.txt";
	
	public static void main(String[] args) throws Exception {
//		getHDFSwithURL();

	}

	private static void getHDFSwithURL() throws MalformedURLException,IOException {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
		URL url = new URL(PATH);
		InputStream in = url.openStream();
		IOUtils.copyBytes(in, System.out, 1024, true);//IOUtils.closeStream(in);
	}

}
