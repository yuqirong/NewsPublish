package com.cjlu.newspublish.actions.impl;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javassist.bytecode.stackmap.TypeData.ClassName;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cjlu.newspublish.actions.BaseAction;
import com.cjlu.newspublish.constant.WebConstant;
import com.cjlu.newspublish.models.News;
import com.cjlu.newspublish.models.NewsType;
import com.cjlu.newspublish.models.Page;
import com.cjlu.newspublish.services.NewsService;
import com.cjlu.newspublish.services.NewsTypeService;

@Controller
@Scope("prototype")
public class NewsTypeAction extends BaseAction<NewsType> {

	private static final long serialVersionUID = -3235298362946735728L;
	@Autowired
	private NewsTypeService newsTypeService;
	@Autowired
	private NewsService newsService;

	List<NewsType> allNewsTypes = null;
	private Integer adminId;
	private static Logger logger = Logger.getLogger(ClassName.class);
	private Page<News> p = null;
	private Integer page;
	private int pageSize = WebConstant.PAGE_MAX_SIZE;

	public String getNews() {
		p = new Page<News>();
		if (page == null) {
			p = newsService.getNews(model.getId(), 1, pageSize);
		} else {
			p = newsService.getNews(model.getId(), page, pageSize);
		}
		requestMap.put("page", p);
		requestMap.put("newsType", newsTypeService.getEntity(model.getId()));
		requestMap.put("hotNews", newsService.getHotNews());
		return "to_newscolumns";
	}

	public String addNewsType() {
		return "to_addNewsTypePage";
	}

	public String saveNewsType() {
		newsTypeService.saveNewsType(model, adminId);
		logger.info("管理员ID" + adminId + "保存新闻栏目：" + model.getTypeName());
		return "to_NewsTypeAction_getAllNewsTypes";
	}

	public String getAllNewsTypes() {
		allNewsTypes = newsTypeService.getAllNewsTypes();
		requestMap.put("allNewsTypes", allNewsTypes);
		return "to_newsTypesListPage";
	}

	public String updateNewsType() {
		if (model.getId() != null && model.getId() > 0) {
			model = newsTypeService.getEntity(model.getId());
		}
		return "to_updateNewsTypePage";
	}

	public String deleteNewsType() {
		try {
			if (model.getId() != null && model.getId() > 0) {
				newsTypeService.deleteNewsType(model.getId());
				logger.info("管理员删除新闻栏目：" + model.getTypeName());
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			}
		} catch (Exception e) {
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax_success";
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
