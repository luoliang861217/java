package test.com.grouper;

import java.util.Arrays;

import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparator;

public class MyGrouper implements RawComparator<SortAPI> {
	@Override
	public int compare(SortAPI o1, SortAPI o2) {
		return (int)(o1.first - o2.first);
	}
	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		int compareBytes = WritableComparator.compareBytes(b1, s1, 8, b2, s2, 8);
		return compareBytes;
	}
}
