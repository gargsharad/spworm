package com.spworm.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController implements ApplicationContextAware {
	
	protected WebApplicationContext springCnxt = null;
	
	@RequestMapping(value="/web" ,  method={RequestMethod.GET, RequestMethod.GET})
	public ModelAndView goToMainPage(HttpServletRequest request, final ModelMap model){
		return new ModelAndView("home");
	}
	
	@Override
	public void setApplicationContext(ApplicationContext cnxt) throws BeansException {
		this.springCnxt = (WebApplicationContext)cnxt;
	}
}
