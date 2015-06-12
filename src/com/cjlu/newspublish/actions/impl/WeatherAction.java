package com.cjlu.newspublish.actions.impl;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.webxml.WeatherWSSoap;

import com.cjlu.newspublish.actions.BaseAction;
import com.cjlu.newspublish.models.Weather;
import com.cjlu.newspublish.services.WeatherService;

@Controller
@Scope("prototype")
public class WeatherAction extends BaseAction<Weather> {

	private static final long serialVersionUID = -4666074413671189428L;

	@Autowired
	private WeatherWSSoap weatherWSSoap;
	@Autowired
	private WeatherService weatherService;

	public void getWeather() throws IOException, ParseException {
		model = weatherService.getWeather(model, httpResponse);
		sessionMap.put("weather", model);
		httpResponse.setContentType("text/xml;charset=utf-8");
	}

}
