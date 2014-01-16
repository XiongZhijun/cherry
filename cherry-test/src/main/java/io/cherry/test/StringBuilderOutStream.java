/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package io.cherry.test;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class StringBuilderOutStream extends OutputStream {
	private StringBuilder stringBuilder = new StringBuilder();

	@Override
	public void write(int b) throws IOException {
		stringBuilder.append((char) b);
	}

	public void reset() {
		stringBuilder.delete(0, stringBuilder.length());
	}

	public String toString() {
		return stringBuilder.toString();
	}
}
