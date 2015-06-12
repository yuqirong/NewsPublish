package com.cjlu.newspublish.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

public final class DomReadXmlUtils {

	private DomReadXmlUtils() {

	}

	/**
	 * 
	 * @param xml
	 * @return
	 * @throws DocumentException
	 * 把xml转换为List<String>
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public static List<String> readStringXml(String xml) throws DocumentException {
		List<String> strs = new ArrayList<String>();
		Document document = null;
		document = DocumentHelper.parseText(xml); // 将字符串转为XML
		Element rootElt = document.getRootElement(); // 获取根节点
		System.out.println("根节点：" + rootElt.getName());// 拿到根节点的名称
		Element element = rootElt.element("Body").element("getWeatherResponse").element("getWeatherResult");
		Iterator iter = element.elementIterator("string");
        while (iter.hasNext()) {
            Element recordEle = (Element) iter.next();
            String str = recordEle.getStringValue();
            strs.add(str);
            System.out.println(str);
	}
		return strs;
	}

}
