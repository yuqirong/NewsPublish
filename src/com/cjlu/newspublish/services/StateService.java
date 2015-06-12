package com.cjlu.newspublish.services;

import java.util.List;

import com.cjlu.newspublish.models.State;

public interface StateService extends BaseService<State>{

	public List<State> getAllStates();

}
