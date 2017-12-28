package com.fruit.web.model;

import com.fruit.web.model.base.BaseTypeGroup;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class TypeGroup extends BaseTypeGroup<TypeGroup> {
	public static final TypeGroup dao = new TypeGroup().dao();

	public static final int STATUS_ENABLE = 1;
	public static final int STATUS_DISABLE = 0;

}