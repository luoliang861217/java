package test.com.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class client {

	public static void main(String[] args) throws IOException {
		
		bllAble proxy = (bllAble)RPC.waitForProxy(bllAble.class, conf.version, new InetSocketAddress(conf.ADDR,conf.PORT), new Configuration());
		
		int i = 0;
		while (true) {
			i++;
			String talk = proxy.Talk("luoliang.me");
			System.out.println(talk);
			if(i>100000){
				break;
			}
		}
		RPC.stopProxy(proxy);
		
	}
	
}
