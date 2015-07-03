package com.neeraj.core.spring;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext springContext)
			throws BeansException {

		context = springContext;
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public static String getMessage(MessageSourceResolvable resolvable,
			Locale locale) {
		return context.getMessage(resolvable, locale);
	}

	public static String getMessage(String code, Object[] args, Locale locale) {
		return context.getMessage(code, args, locale);
	}

	public static String getMessage(String code, Object[] args,
			String defaultMessage, Locale locale) {
		return context.getMessage(code, args, defaultMessage, locale);
	}

}
