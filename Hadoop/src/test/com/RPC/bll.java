package test.com.RPC;

import java.io.IOException;

public class bll implements bllAble {
	@Override
	public long getProtocolVersion(String protocol, long clientVersion) throws IOException {
		return conf.version;
	}
	
	public String Talk(String name) {
		System.out.println("bll is call");
		return "hello " + name;
	};
}
