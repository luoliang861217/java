package test.com.RPC;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.ipc.RPC;

public class Server {

	public static void main(String[] args) throws Exception {
		org.apache.hadoop.ipc.RPC.Server server = RPC.getServer(new Operator(), conf.ADDR, conf.PORT, new Configuration());
		server.start();
	}
	
		

}
