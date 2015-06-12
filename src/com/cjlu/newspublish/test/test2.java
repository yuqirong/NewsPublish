package com.cjlu.newspublish.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import com.cjlu.newspublish.utils.ValidateUtils;

public class test2 {

	@Test
	public void stringToDate() throws ParseException {
		String str = "2015/12/26 12:52:26";
		if (ValidateUtils.isValid(str)) {
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = (Date) format.parse(str);
			System.out.println(date);
		}
	}
	
	@Test
	public void stringToDate2() throws ParseException {
		String str = "2015/12/26 12:52:26";
		if (ValidateUtils.isValid(str)) {
			FastDateFormat format = FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss");
			Date parseObject = (Date) format.parseObject(str);
			System.out.println(parseObject);
		}
	}

	@Test
	public void clientTest() throws IOException {
		InetAddress net = InetAddress.getByName("127.0.0.1");
		Socket socket = new Socket(net, 8989);
		InputStreamReader input = new InputStreamReader(socket.getInputStream());
		BufferedReader buffer = new BufferedReader(input);
		int t = 0;
		String str = null;
		while ((str = buffer.readLine()) != null) {
			System.out.println(str);
		}
		buffer.close();
		input.close();
		socket.close();
	}

	@Test
	public void severTest() throws IOException {
		ServerSocket server = new ServerSocket(8989);
		Socket socket = server.accept();
		OutputStreamWriter output = new OutputStreamWriter(
				socket.getOutputStream());

		FileReader re = new FileReader("abc.txt");
		BufferedReader reader = new BufferedReader(re);
		int t = 0;
		String str = null;
		BufferedWriter buffer = new BufferedWriter(output);
		while ((str = reader.readLine()) != null) {
			if (t != 0) {
				buffer.write("\n");
			}
			buffer.write(str);
			t++;
		}
		re.close();
		reader.close();
		buffer.close();
		output.close();
		socket.close();
		server.close();

	}
}
