/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang.exception;


/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ClassMemberFindException extends ClassException {

	private Class<?> clazz;
	private String member;
	private Type type;

	/**
	 * @param clazz
	 * @param member
	 */
	private ClassMemberFindException(Class<?> clazz, String member, Type type) {
		super(toMessage(clazz, type, member));
		this.clazz = clazz;
		this.member = member;
		this.type = type;
	}

	/**
	 * @param clazz
	 * @param member
	 * @param cause
	 */
	private ClassMemberFindException(Class<?> clazz, String member, Type type,
			Throwable cause) {
		super(toMessage(clazz, type, member), cause);
		this.clazz = clazz;
		this.member = member;
		this.type = type;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getMember() {
		return member;
	}

	public Type getType() {
		return type;
	}

	private static String toMessage(Class<?> clazz, Type type, String member) {
		return join("Find", type.name(), "(", member, ") of class ",
				clazz.getName(), " failed.");
	}

	public static ClassMemberFindException createFieldException(Class<?> clazz,
			String fieldName, Throwable cause) {
		return new ClassMemberFindException(clazz, fieldName, Type.field, cause);
	}

	public static ClassMemberFindException createMethodException(
			Class<?> clazz, String methodName, Throwable cause) {
		return new ClassMemberFindException(clazz, methodName, Type.method,
				cause);

	}

	public static enum Type {
		field, method
	}

	private static final long serialVersionUID = 5005521360094621724L;

}
