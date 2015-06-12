package com.cjlu.newspublish.services;

import java.util.List;

import com.cjlu.newspublish.models.Comment;
import com.cjlu.newspublish.models.User;

public interface CommentService extends BaseService<Comment> {

	public List<Comment> getViewNewsAllComment(Integer newsId);

	public void publishComment(String content, String ipAddress,
			Integer newsId, User user);

	public void deleteComment(Integer commentId);

}
