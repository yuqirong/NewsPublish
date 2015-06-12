package com.cjlu.newspublish.services;

import java.util.List;

import com.cjlu.newspublish.models.News;
import com.cjlu.newspublish.models.Page;

public interface NewsService extends BaseService<News> {

	public News getViewNews(Integer id);

	public void deleteNews(Integer id);

	public void saveNews(News model, Integer adminId);

	public Page<News> listAllNewsPage(int pageNo, int pageSize);

	public Page<News> listAllNotPassedNewsPage(int pageNo, int pageSize);

	public Page<News> getNews(Integer id, int pageNo, int pageSize);

	public List<News> getHotNews();

	public List<News> getRecentNews(int id, int MaxResult);

	public void createNewsIndex(News model);

}
