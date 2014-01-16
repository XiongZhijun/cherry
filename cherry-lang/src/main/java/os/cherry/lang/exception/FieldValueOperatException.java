/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang.exception;

import java.lang.reflect.Field;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class FieldValueOperatException extends ObjectException {

	private Object target;
	private Field field;
	private Object value;
	private Type type;

	private FieldValueOperatException(Object target, Field field, Object value,
			Type type, Throwable cause) {
		super(toMessage(target, field, value, type), cause);
		this.target = target;
		this.field = field;
		this.value = value;
		this.type = type;
	}

	public Object getTarget() {
		return target;
	}

	public Field getField() {
		return field;
	}

	public Object getValue() {
		return value;
	}

	public Type getType() {
		return type;
	}

	private static String toMessage(Object target, Field field, Object value,
			Type type) {
		return join(type.name(), "value by target", target, "(class",
				target.getClass(), ")'s field", field.getName(), "failed.",
				"target");
	}

	public static FieldValueOperatException createGetException(Object target,
			Field field, Object value, Throwable cause) {
		return new FieldValueOperatException(target, field, value, Type.Get,
				cause);
	}

	public static FieldValueOperatException createSetException(Object target,
			Field field, Object value, Throwable cause) {
		return new FieldValueOperatException(target, field, value, Type.Set,
				cause);
	}

	public static enum Type {
		Get, Set
	}

	private static final long serialVersionUID = -7911126818620210589L;
}
