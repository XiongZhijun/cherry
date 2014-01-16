/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义一个OutputStream，其将所有的输出都顺序写入到一个{@link StringBuilder}中，这意味着所有的输入最终都会转换成字符串，
 * 写入完成后，可以通过 {@link #toString()}方法得到完整的{@link String}对象。 <br />
 * 另外，可以通过给StringBuilderOutStream对象添加{@link Filter}的方式来对输入的字符串（byte
 * array会转换成字符串）进行过滤。过滤掉的字符串将不会添加到{@link #stringBuilder}中。
 * 
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

	/**
	 * 添加过滤器
	 * 
	 * @param filter
	 */
	public void addFilter(Filter filter) {
		this.filters.add(filter);
	}

	/**
	 * 删除某一个过滤器
	 * 
	 * @param filter
	 */
	public void removeFilter(Filter filter) {
		this.filters.remove(filter);
	}

	/**
	 * 重置本OutputStream，也就是将{@link #stringBuilder}清空。
	 */
	public void reset() {
		stringBuilder.delete(0, stringBuilder.length());
	}

	/**
	 * 得到所有写入的字符串。
	 * 
	 * @return
	 */
	public String toString() {
		return stringBuilder.toString();
	}

	/**
	 * 定义了一个过滤器规范，在{@link StringBuilderOutStream}中应用时，对于{@link #ignore(String)}
	 * 方法返回为true的字符串都不会写入到{@link StringBuilder}中。
	 */
	public static interface Filter {
		/**
		 * 执行过滤算法。
		 * 
		 * @param str
		 *            待判断的字符串
		 * @return 返回true表示str字符串将不会写入到{@link StringBuilder}中。
		 */
		boolean ignore(String str);
	}
}
