package test.com.RPC;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class server {

	
	public static void main(String[] args) throws IOException {
		
		Server server = RPC.getServer(new bll(), conf.ADDR, conf.PORT, new Configuration());
		server.start();
		
	}
	
	
}
