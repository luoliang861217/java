package test.com.mapredus.Mobile;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.WriteAbortedException;

import org.apache.hadoop.io.Writable;

public class MobileAPI implements Writable {

	public long UpPackNum;
	public long UpDataNum;
	
	public long DownPackNum;
	public long DownDataNum;
	
	public MobileAPI(){

	}
	
	public MobileAPI(String UpPackNum,String UpDataNum,String DownPackNum,String DownDataNum){
		try {
			this.UpPackNum = Long.parseLong(UpPackNum);
			this.UpDataNum = Long.parseLong(UpDataNum);
			this.DownPackNum = Long.parseLong(DownPackNum);
			this.DownDataNum = Long.parseLong(DownDataNum);
		} catch (Exception e) {
			System.out.println("“Ï≥££∫" + e.getMessage());
		}
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(this.UpPackNum);
		out.writeLong(this.UpDataNum);
		out.writeLong(this.DownPackNum);
		out.writeLong(this.DownDataNum);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.UpPackNum = in.readLong();
		this.UpDataNum = in.readLong();
		this.DownPackNum = in.readLong();
		this.DownDataNum = in.readLong();
		
	}

	@Override
	public String toString() {
		return this.UpPackNum + "\t" + this.UpDataNum+ "\t" + this.DownPackNum+ "\t" +this.DownDataNum;
	}

}
