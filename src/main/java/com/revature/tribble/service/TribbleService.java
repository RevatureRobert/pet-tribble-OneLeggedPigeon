package com.revature.tribble.service;

import com.revature.tribble.dao.LabDao;
import com.revature.tribble.dao.TribbleDao;
import com.revature.tribble.model.Lab;
import com.revature.tribble.model.Tribble;

import java.util.List;

/**
 * Calls UserDao methods to perform persistence actions and handles business logic
 * related to info being persisted to or retrieved from the database.
 */
public class TribbleService implements GenericService<Tribble> {
	private TribbleDao dao;

	public TribbleService() {
		dao = new TribbleDao();
	}

	@Override
	public List<Tribble> getList() {
		return dao.getList();
	}

	@Override
	public Tribble getById(int id) {
		return dao.getById(id);
	}

	@Override
	public void create(Object o) {
		dao.insert((Tribble) o);
	}

	@Override
	public void createOrUpdate(Object o) {
		dao.insertOrUpdate((Tribble) o);
	}

	@Override
	public void delete(Object o) {
		dao.delete((Tribble) o);
	}
}
