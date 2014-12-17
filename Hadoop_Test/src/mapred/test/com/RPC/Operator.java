package test.com.RPC;

import java.io.IOException;

import org.apache.hadoop.ipc.VersionedProtocol;

public class Operator implements OperatorAble {
	/**
	 * ˵��
	 * @param name
	 * @return
	 */
	@Override
	public String Talk(String name){
		System.out.println("Operator is call......");
		return "hello:" + name;
	}
	@Override
	public long getProtocolVersion(String protocol, long clientVersion) throws IOException{
		  return conf.version;
	}
	
	
}
interface OperatorAble extends VersionedProtocol {
	/**
	 * ˵��
	 * @param name
	 * @return
	 */
	public String Talk(String name);
}
