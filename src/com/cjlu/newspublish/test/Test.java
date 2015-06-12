package com.cjlu.newspublish.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymMap.Parser;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.cjlu.newspublish.constant.WebConstant;
import com.cjlu.newspublish.models.News;
import com.cjlu.newspublish.utils.StringUtils;

public class Test {

	@org.junit.Test
	public void str() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse("2002-03-26 13:31:40");
			Date d2 = df.parse("2015-04-19 21:47:24");
			System.out.println(System.currentTimeMillis());
			System.out.println(d2.getTime());
			long diff = System.currentTimeMillis() - d2.getTime();
			long days = diff / (1000 * 60 * 60 * 24);
			System.out.println(days);
		} catch (Exception e) {
		}
	}

	@org.junit.Test
	public void getWeather() throws IOException {
		String data = "<soap:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>"
				+ "<soap:Body><getWeather xmlns='http://WebXml.com.cn/'><theCityCode>"
				+ "象山"
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
		if (responseCode == 200) {
			InputStream inputStream = openConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "utf-8"));
			// ServletOutputStream os = httpResponse.getOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0;
			String str = null;
			while ((str = reader.readLine()) != null) {
				System.out.println(str);
			}

		}

		// while ((length = inputStream.read(buffer)) > 0) {
		// System.out.println(inputStream.read(buffer, 0, length));
		// os.write(buffer, 0, length);
		// }
		// os.flush();
		// }
		// httpResponse.setContentType("text/xml;charset=utf-8");

	}

	@org.junit.Test
	public void test2() {
		String s = "";
		System.out.println(s.toLowerCase());

	}

	@SuppressWarnings("deprecation")
	@org.junit.Test
	public void testCreateIndex() throws Exception {
		for (int i = 3; i < 18; i++) {
			String title = null;
			String id = null;
			String content = null;
			String createTime = null;
			try {
				String sql = "SELECT id, title, content, create_time FROM news_table WHERE id = ? and state = 1";
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager
						.getConnection(
								"jdbc:mysql://localhost:3306/newspublish?useUnicode=true&characterEncoding=utf-8",
								"root", "mysql");
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);
				preparedStatement.setInt(1, i);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet != null) {
					while (resultSet.next()) {
						id = resultSet.getString(1);
						title = resultSet.getString(2);
						content = resultSet.getString(3);
						createTime = resultSet.getString(4);
					}
				}
				resultSet.close();
				preparedStatement.close();
				connection.close();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			Directory directory = FSDirectory.open(WebConstant.FILE_PATH);
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			IndexWriter indexWriter = new IndexWriter(directory, config);
			Document document = new Document();
			if (title == null || title.trim() == "") {
				indexWriter.close();
				continue;
			}
			document.add(new Field("id", id + "", Store.YES, Index.NO,
					TermVector.NO));
			document.add(new Field("title", title, Store.YES, Index.ANALYZED,
					TermVector.WITH_POSITIONS_OFFSETS));
			document.add(new Field("content", content, Store.YES,
					Index.ANALYZED, TermVector.WITH_POSITIONS_OFFSETS));
			document.add(new Field("createTime", createTime, Field.Store.YES,
					Index.NO, TermVector.NO));
			indexWriter.addDocument(document);
			indexWriter.close();
			System.out.println("+");
		}

	}

	@org.junit.Test
	public void testQuery() {
		Directory directory = null;
		IndexReader reader = null;
		IndexSearcher searcher = null;
		Analyzer analyzer = null;
		String category = "title";
		String keyword = "黑";
		TopDocs topDocs = null;
		int count = 0;
		ScoreDoc[] scoreDocs = null;
		float score = 0f;
		int index = 0;
		Document document = null;
		List<News> allNews = new ArrayList<News>();
		long time1 = 0l;
		Term term = null;
		TermQuery termQuery = null;
		QueryParser parser = null;
		Query query = null;
		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			directory = FSDirectory.open(WebConstant.FILE_PATH);
			reader = DirectoryReader.open(directory);
			searcher = new IndexSearcher(reader);
			analyzer = new StandardAnalyzer();
			parser = new QueryParser(category, analyzer);
			query = parser.parse(keyword);
			SortField sortField = new SortField("createTime", Type.SCORE, true);
			topDocs = searcher.search(query, 10, new Sort(sortField));
			count = topDocs.totalHits;
			System.out.println("搜索结果总数 : " + count);
			scoreDocs = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				News news = new News();
				index = scoreDoc.doc;
				document = searcher.doc(index);
				String id = document.get("id");
				String title = document.get("title");
				String content = document.get("content");
				String createTime = document.get("createTime");
				SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(
						"<b><font color='red'>", "</font></b>");
				// 高亮对象
				Highlighter highlighter = new Highlighter(simpleHTMLFormatter,
						new QueryScorer(query));
				if (category.equals("title")) {
					String bestFragment = highlighter.getBestFragment(analyzer,
							category, title);
					// 获得高亮后的标题内容
					news.setTitle(bestFragment);
					// 如果内容不足150个字，全部设置
					if (content.length() < 150) {
						news.setContent(content);
					} else {
						// 如果内容多于150个字，只取出前面150个字
						news.setContent(StringUtils.getDescString(content, 150)
								+ "...");
					}
				} else {
					// 如果查询的是内容字段
					String bestFragment = highlighter.getBestFragment(analyzer,
							category, content);
					// 取得高亮内容并设置
					news.setContent(bestFragment);
					// 设置标题，全部设置
					news.setTitle(title);
				}
				news.setId(Integer.parseInt(id));
				news.setCreateTime((Date) format.parseObject(createTime));
				allNews.add(news);
			}
			reader.close();
			for (News news : allNews) {
				System.out.println(news.getId());
				System.out.println(news.getTitle());
				System.out.println(news.getContent());
				System.out.println(news.getCreateTime());
			}
		} catch (IOException | ParseException | InvalidTokenOffsetsException
				| java.text.ParseException e) {
			System.out.println("搜索索引出错");
			e.printStackTrace();
		}
	}

}
