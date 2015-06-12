package com.cjlu.newspublish.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cjlu.newspublish.models.State;
import com.cjlu.newspublish.services.StateService;

@Service("stateService")
public class StateServiceImpl extends BaseServiceImpl<State> implements StateService{

	@Override
	public List<State> getAllStates() {
		return this.findAllEntities();
	}

}
