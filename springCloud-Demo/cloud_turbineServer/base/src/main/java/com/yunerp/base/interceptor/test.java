package com.yunerp.base.interceptor;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class test extends InternalResourceViewResolver {

	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		setPrefix("1111111");
		return super.buildView(viewName);
	}
}
