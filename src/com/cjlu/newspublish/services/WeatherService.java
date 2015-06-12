package com.cjlu.newspublish.services;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;

import com.cjlu.newspublish.models.Weather;

public interface WeatherService extends BaseService<Weather> {

	public Weather getWeather(Weather weather, HttpServletResponse httpResponse)
			throws IOException, ParseException;
}
