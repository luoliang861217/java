package test.com.grouper;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SortAPI implements WritableComparable<SortAPI> {

	public Long first;
	public Long second;
	
	public SortAPI(){
		
	}
	
	public SortAPI(long first,long second){
		this.first = first;
		this.second = second;
	}

	@Override
	public int compareTo(SortAPI o) {
		long mis = (this.first - o.first) * -1;
		if(mis != 0 ){
			return (int)mis;
		}
		else{
			return (int)(this.second - o.second);
		}
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(first);
		out.writeLong(second);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.first = in.readLong();
		this.second = in.readLong();
		
	}

	@Override
	public int hashCode() {
		return this.first.hashCode() + this.second.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SortAPI){
			SortAPI o = (SortAPI)obj;
			return this.first == o.first && this.second == o.second;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.first + ";" + this.second;
	}
}
