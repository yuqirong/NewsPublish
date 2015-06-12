package com.cjlu.newspublish.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.cjlu.newspublish.models.security.Right;
import com.cjlu.newspublish.services.RightService;


@SuppressWarnings("rawtypes")
@Component
public class InitRightListener implements ApplicationListener,
		ServletContextAware {

	private ServletContext servletContext;
	@Autowired
	private RightService rightService;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// 上下文刷新事件
		if (arg0 instanceof ContextRefreshedEvent) {
			// 查出所有权限
			List<Right> rights = rightService.findAllEntities();
			Map<String, Right> map = new HashMap<String, Right>();
			for (Right r : rights) {
				map.put(r.getRightUrl(), r);
			}
			if (servletContext != null) {
				servletContext.setAttribute("all_rights_map", map);
			}
		}

	}

}
