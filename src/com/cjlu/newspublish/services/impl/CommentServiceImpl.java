package com.cjlu.newspublish.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjlu.newspublish.daos.impl.CommentDaoImpl;
import com.cjlu.newspublish.daos.impl.NewsDaoImpl;
import com.cjlu.newspublish.models.Comment;
import com.cjlu.newspublish.models.News;
import com.cjlu.newspublish.models.User;
import com.cjlu.newspublish.services.CommentService;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements
		CommentService {

	@Autowired
	private CommentDaoImpl commentDao;
	@Autowired
	private NewsDaoImpl newsDao;
	private News news = null;
	private Comment comment = null;

	@Override
	public List<Comment> getViewNewsAllComment(Integer newsId) {
		if (newsId != null) {
			List<Comment> allComments = commentDao
					.getViewNewsAllComment(newsId);
			for (Comment comment : allComments) {
				comment.getNews().setContent("");
			}
			return allComments;
		}
		return null;
	}

	@Override
	public void publishComment(String content, String ipAddress,
			Integer newsId, User user) {
		if (newsId != null && newsId > 0) {
			news = newsDao.getEntity(newsId);
		}
		comment = new Comment(content, new Date(), user, ipAddress, news);
		this.saveEntity(comment);
	}

	@Override
	public void deleteComment(Integer commentId) {
		if (commentId != null) {
			Comment comment = this.getEntity(commentId);
			this.deleteEntity(comment);
		}
	}

}
