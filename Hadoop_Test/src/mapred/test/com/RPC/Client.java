package test.com.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.VersionedProtocol;

public class Client {
	
	public static void main(String[] args) throws Exception  {
		OperatorAble proxy = (OperatorAble)RPC.waitForProxy(OperatorAble.class, conf.version, new InetSocketAddress(conf.ADDR, conf.PORT), new Configuration());
		for (int i = 0; i < 100000; i++) {
			String talk = proxy.Talk("luoliang");
			System.out.println(talk);
		}
		
	
	}
	
}
