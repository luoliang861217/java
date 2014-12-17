package test.com.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 * hdfs API��ʽ����hadoop
 * @author Administrator
 *
 */
public class HdfsTest {

	static final String ROOT = "hdfs://hadoop-master:9000/";
	static final Configuration config = new Configuration();
	
	/**
	 * �����ļ���
	 * @param path	���·������"/test/"
	 * @return �Ƿ񴴽��ɹ�
	 * @throws Exception
	 */
	public static boolean Mkdir(String path) throws Exception {
		FileSystem fileSystem = getFileSystem();
		boolean isCreate = fileSystem.mkdirs(new Path(path));
		return isCreate;
	}
	
	/**
	 * ɾ���ļ���
	 * @param path	�ļ���·��
	 * @return	�Ƿ�ɹ�ɾ��
	 */
	public static boolean Removedir(String path) {
		boolean result = false;
		Path strPath = new Path(path);
		try {
			FileSystem fileSystem = getFileSystem();
			if(fileSystem.isDirectory(strPath)){
				result = fileSystem.delete(strPath);
			}
			else{
				throw new NullPointerException("�߼��쳣��ɾ���ļ������Գ���δ��ɾ���ļ��У����ɾ���ļ�Ӧʹ��deleteFile(String path)");
			}
		} catch (Exception e) {
			System.out.println("�쳣��" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * ɾ���ļ�
	 * @param path	�ļ�·��
	 * @return	�Ƿ�ɹ�ɾ��
	 */
	public static boolean deleteFile(String path) {
		boolean result = false;
		Path strPath = new Path(path);
		try {
			FileSystem fileSystem = getFileSystem();
			if(fileSystem.isFile(strPath)){
				result = fileSystem.delete(strPath);
			}
			else{
				throw new NullPointerException("�߼��쳣��ɾ���ļ����Գ���δ��ɾ���ļ������ɾ���ļ���Ӧʹ��Removedir(String path)");
			}
		} catch (Exception e) {
			System.out.println("�쳣��" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * ���ļ����ļ����б�
	 * @param path	�ļ����ļ���·��
	 * @return	����FileStatus[]
	 */
	public static FileStatus[] List(String path){		
		return List(path,false);		
	}
	
	/**
	 * ���ļ����ļ����б�
	 * @param path
	 * @param isFull	true:�ݹ�����ļ���·����ȡ��false������ȡ��һ�㲻���еݹ����
	 * @return
	 */
	public static FileStatus[] List(String path,boolean isFull){
		List<FileStatus> arrayList = new ArrayList<FileStatus>();
		Path strPath = new Path(path);
		try {
			FileSystem fileSystem = getFileSystem();
			FileStatus[] result = fileSystem.listStatus(strPath);
			for (int i = 0; i < result.length; i++) {
				FileStatus item = result[i];
				arrayList.add(item);
				if(item.isDir()){
					FileStatus[] results = List(item.getPath().toString(),true);
					for (int j = 0; j < results.length; j++) {
						FileStatus fileStatus = results[j];
						arrayList.add(fileStatus);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("�쳣��" + e.getMessage());
		}
		FileStatus[] ll = new FileStatus[arrayList.size()] ;arrayList.toArray(ll);
		return ll;
	}
	
	/**
	 * �ϴ��ļ�
	 * @param src	���ش����ļ�
	 * @param tar	�ϴ��ļ���HDFS·��
	 * @return		�Ƿ�ɹ��ϴ�
	 */
	public static boolean PutFile(String src,String tar) {
		boolean result = false;
		Path srcPath = new Path(src);
		Path tarPath = new Path(tar);
		try {
			FileSystem fileSystem = getFileSystem();
			fileSystem.copyFromLocalFile(srcPath, tarPath);
			result = true;
		} catch (Exception e) {
			System.out.println("�쳣��" + e.getMessage());
		}
		return result;
	}
	
	/**
	 * �����ļ������ش���
	 * @param path	HDFS·��
	 * @return		���ر��ش���·��
	 */
	public static String DownFile(String path){	
		int index = path.lastIndexOf('.') > 0 ? path.lastIndexOf('.') : 0;
		String name = path.substring(index);
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String target =String.format("{0}\\{1}{2}", System.getProperty("user.dir"),format.format(new Date()), name);
		Path strPath = new Path(path);
		Path tarPath = new Path(target);
		try {
			FileSystem fileSystem = getFileSystem();
			fileSystem.copyToLocalFile(strPath, tarPath);
		} catch (Exception e) {
			System.out.println("�쳣��" + e.getMessage());
		}
		return target;
	}
	
	/**
	 * ��ȡFileSystemʵ��
	 * @return
	 * @throws Exception
	 */	
	public  static FileSystem getFileSystem() throws Exception{
		return FileSystem.get(new URI(ROOT), config);
	}
	
}
