package test.com.RPC;

import java.io.IOException;

import org.apache.hadoop.ipc.*;

public interface bllAble extends VersionedProtocol {

	public String Talk(String name);
	
}
