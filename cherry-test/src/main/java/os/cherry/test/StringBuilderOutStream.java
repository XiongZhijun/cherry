/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.test;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class StringBuilderOutStream extends OutputStream {
	private StringBuilder stringBuilder = new StringBuilder();
	private Set<Filter> filters = new HashSet<Filter>();

	@Override
	public void write(int b) throws IOException {
		stringBuilder.append((char) b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		String str = new String(b, off, len);
		for (Filter filter : filters) {
			if (filter.ignore(str)) {
				return;
			}
		}
		stringBuilder.append(str);
	}

	public void addFilter(Filter filter) {
		this.filters.add(filter);
	}

	public void removeFilter(Filter filter) {
		this.filters.remove(filter);
	}

	public void reset() {
		stringBuilder.delete(0, stringBuilder.length());
	}

	public String toString() {
		return stringBuilder.toString();
	}

	public static interface Filter {
		boolean ignore(String str);
	}
}
