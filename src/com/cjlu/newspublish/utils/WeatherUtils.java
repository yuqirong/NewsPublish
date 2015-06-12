package com.cjlu.newspublish.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.cjlu.newspublish.models.Weather;

/**
 * 天气工具类
 * 
 * @author Anyway
 *
 */
public class WeatherUtils {

	private WeatherUtils() {

	}

	public static Weather getWeather(Weather model,
			HttpServletResponse httpResponse) throws IOException, ParseException {
		List<String> strings = null;
		String area = model.getCounty();
		if (area.length() > 2) {
			if (area.endsWith("县") || area.endsWith("市")) {
				area = area.substring(0, area.length() - 1);
			}
		}
		String data = "<soap:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>"
				+ "<soap:Body><getWeather xmlns='http://WebXml.com.cn/'><theCityCode>"
				+ area
				+ "</theCityCode><theUserID>"
				+ ""
				+ "</theUserID> </getWeather></soap:Body></soap:Envelope>";
		String path = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx";
		URL url = new URL(path);
		HttpURLConnection openConnection = (HttpURLConnection) url
				.openConnection();
		openConnection.setDoInput(true);
		openConnection.setDoOutput(true);
		openConnection.setRequestProperty("Content-Type",
				"text/xml;charset=utf-8");
		OutputStream outputStream = openConnection.getOutputStream();
		outputStream.write(data.getBytes("utf-8"));
		outputStream.flush();
		int responseCode = openConnection.getResponseCode();
		StringBuilder stringBuilder = new StringBuilder();
		if (responseCode == 200) {
			InputStream inputStream = openConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "utf-8"));
			ServletOutputStream os = httpResponse.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					os, "utf-8"));
			char[] buffer = new char[1024];
			int length = 0;
			while ((length = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, length);
				stringBuilder.append(buffer, 0, length);
			}
			writer.flush();
			writer.close();
			os.close();
			reader.close();
			inputStream.close();
			try {
				strings = DomReadXmlUtils.readStringXml(stringBuilder
						.toString());
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			model = new Weather(model.getProvince(), model.getCity(),
					model.getCounty(), strings.get(7), strings.get(8),
					strings.get(12), strings.get(13), strings.get(17),
					strings.get(18), DateUtils.stringToDate(strings.get(3)));
		}
		return model;

	}

}
