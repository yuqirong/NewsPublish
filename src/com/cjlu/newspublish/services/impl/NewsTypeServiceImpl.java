package com.cjlu.newspublish.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjlu.newspublish.daos.impl.AdminDaoImpl;
import com.cjlu.newspublish.daos.impl.CommentDaoImpl;
import com.cjlu.newspublish.daos.impl.NewsDaoImpl;
import com.cjlu.newspublish.daos.impl.NewsTypeDaoImpl;
import com.cjlu.newspublish.models.News;
import com.cjlu.newspublish.models.NewsType;
import com.cjlu.newspublish.models.security.Admin;
import com.cjlu.newspublish.services.NewsTypeService;

@Service("newsTypeService")
public class NewsTypeServiceImpl extends BaseServiceImpl<NewsType> implements
		NewsTypeService {

	@Autowired
	private NewsTypeDaoImpl newsTypeDao;
	@Autowired
	private NewsDaoImpl newsDao;
	@Autowired
	private CommentDaoImpl commentDao;
	@Autowired
	private AdminDaoImpl adminDao;
	private List<NewsType> allNewsTypes = null;
	private NewsType newsType = null;
	private Admin admin = null;

	@Override
	public List<NewsType> getAllNewsTypes() {
		allNewsTypes = this.findAllEntities();
		for (NewsType newsType : allNewsTypes) {
			if (newsType.getAdmin() != null) {
				newsType.getAdmin().getUsername();
			}
		}
		return allNewsTypes;
	}

	@Override
	public void deleteNewsType(Integer id) {
		List<News> allnews = newsDao.getAllNewsByNewsType(id);
		if (allnews != null) {
			for (News news : allnews) {
				commentDao.deleteCommentByNewsId(news.getId());
				newsDao.deleteEntity(news);
			}
		}
		newsType = this.getEntity(id);
		this.deleteEntity(newsType);
	}

	@Override
	public void saveNewsType(NewsType model, Integer adminId) {
		admin = adminDao.getEntity(adminId);
		if (model.getId() != null && model.getId() > 0) {
			model.setAdmin(admin);
		} else if (model.getId() == null) {
			model.setCreateTime(new Date());
			model.setAdmin(admin);
		}
		this.saveOrUpdateEntity(model);
	}

}
