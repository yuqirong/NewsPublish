package com.cjlu.newspublish.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjlu.newspublish.daos.impl.VisitorCounterDaoImpl;
import com.cjlu.newspublish.models.VisitorCounter;
import com.cjlu.newspublish.services.VisitorCounterService;

@Service("visitorCounterService")
public class VisitorCounterServiceImpl extends BaseServiceImpl<VisitorCounter>
		implements VisitorCounterService {

	@Autowired
	private VisitorCounterDaoImpl visitorCounterDao;

}
