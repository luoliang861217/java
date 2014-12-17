package test.com.hdfs;

import org.apache.hadoop.fs.FileStatus;

public class Test {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
//		UrlTest.Test();


		HDFSTest();

		
		
	}

	public static void HDFSTest() throws Exception {
		String strDir = "/data/";
		String strDirback = "/data/back";
		String strFile = "F:\\05±Ê¼Ç.txt";
		String strPutFilePath = "/data/05±Ê¼Ç.txt";
		if(HdfsTest.Mkdir(strDir)){
			if(HdfsTest.PutFile(strFile, strPutFilePath)){
				if(HdfsTest.Mkdir(strDirback)){
					FileStatus[] list = HdfsTest.List(strDir,true);
					for (int i = 0; i < list.length; i++) {
						FileStatus fileStatus = list[i];
						final String name = fileStatus.getPath().getName();
						final String path = fileStatus.getPath().toString();
						final long length = fileStatus.getLen();
						final String dir = fileStatus.isDir() ? "d" : "-";
						final short replication = fileStatus.getReplication();
						final String permission = fileStatus.getPermission().toString();
						final String group = fileStatus.getGroup();
						final String owner = fileStatus.getOwner();
						System.out.println(dir + permission + "\t" + replication  + "\t" + group + "\t" + owner + "\t" + path);
					}
					
				}
			}
		}
	}

}

