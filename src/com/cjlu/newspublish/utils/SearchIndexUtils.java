package com.cjlu.newspublish.utils;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.cjlu.newspublish.constant.WebConstant;
import com.cjlu.newspublish.models.News;
import com.cjlu.newspublish.models.Page;

/**
 * 搜索工具类
 */
@SuppressWarnings("deprecation")
public final class SearchIndexUtils {

	public static SearchIndexUtils Instance() {
		return new SearchIndexUtils();
	}

	private SearchIndexUtils() {

	}

	public int pageSize = WebConstant.PAGE_SIZE - 5;
	public int pageNo;

	/**
	 * 创建索引
	 */
	public static void createIndex(News news) {

		Directory directory = null;
		Analyzer analyzer = null;
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;
		Document document = null;
		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			directory = FSDirectory.open(WebConstant.FILE_PATH);
			analyzer = new StandardAnalyzer();
			indexWriterConfig = new IndexWriterConfig(analyzer);
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			document = new Document();
			document.add(new Field("id", news.getId() + "", Store.YES,
					Index.NO, TermVector.NO));
			document.add(new Field("title", news.getTitle(), Store.YES,
					Index.ANALYZED, TermVector.WITH_POSITIONS_OFFSETS));
			document.add(new Field("content", news.getContent(), Store.YES,
					Index.ANALYZED, TermVector.WITH_POSITIONS_OFFSETS));
			document.add(new Field("createTime", format.format(news
					.getCreateTime()), Field.Store.YES, Index.NO, TermVector.NO));
			indexWriter.addDocument(document);
			indexWriter.close();
		} catch (IOException e) {
			System.out.println("创建索引出错");
			e.printStackTrace();
		}
	}

	/**
	 * 搜索索引
	 */
	public Page<News> searchIndex(String searchWhich, String keyword,
			boolean flag) {
		Directory directory = null;
		IndexReader reader = null;
		IndexSearcher searcher = null;
		Analyzer analyzer = null;
		TopDocs topDocs = null;
		int count = 0;
		ScoreDoc[] scoreDocs = null;
		int index = 0;
		Document document = null;
		Query query = null;
		QueryParser parser = null;
		TermQuery termQuery = null;
		Page<News> page = null;
		List<News> allNews = new ArrayList<News>();
		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			directory = FSDirectory.open(WebConstant.FILE_PATH);
			reader = DirectoryReader.open(directory);
			searcher = new IndexSearcher(reader);
			if (flag) {
				termQuery = new TermQuery(new Term(searchWhich, keyword));
				SortField sortField = new SortField("createTime", Type.SCORE,
						false);
				topDocs = searcher.search(termQuery, 100, new Sort(sortField));
			} else {
				analyzer = new StandardAnalyzer();
				parser = new QueryParser(searchWhich, analyzer);
				query = parser.parse(keyword);
				SortField sortField = new SortField("createTime", Type.SCORE,
						false);
				topDocs = searcher.search(query, 100, new Sort(sortField));
			}
			count = topDocs.totalHits;
			System.out.println("搜索结果总数 : " + count);
			scoreDocs = topDocs.scoreDocs;
			// 查询起始记录位置
			int begin = pageSize * (pageNo - 1);
			// 查询终止记录位置
			int end = Math.min(begin + pageSize, scoreDocs.length);
			for (int i = begin; i < end; i++) {
				News news = new News();
				index = scoreDocs[i].doc;
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
				if (searchWhich.equals("title")) {
					String bestFragment = highlighter.getBestFragment(analyzer,
							searchWhich, title);
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
							searchWhich, content);
					// 取得高亮内容并设置
					news.setContent(bestFragment + "...");
					// 设置标题，全部设置
					news.setTitle(title);
				}
				news.setId(Integer.parseInt(id));
				news.setCreateTime((Date) format.parseObject(createTime));
				allNews.add(news);
			}
			reader.close();
			page = new Page<News>(pageNo, pageSize, count, allNews);
			if (count == 0) {
				page.setPageCount(1);
			} else {
				if (count % pageSize == 0) {
					page.setPageCount(count / pageSize);
				} else {
					page.setPageCount(count / pageSize + 1);
				}
			}
		} catch (IOException | ParseException | InvalidTokenOffsetsException
				| java.text.ParseException e) {
			System.out.println("搜索索引出错");
			e.printStackTrace();
		}
		return page;
	}
}
