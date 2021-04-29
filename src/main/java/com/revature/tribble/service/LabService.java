package com.revature.tribble.service;

import com.revature.tribble.dao.LabDao;
import com.revature.tribble.model.Lab;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Calls ReimbursementDao methods to perform persistence actions and handles business logic
 * related to info being persisted to or retrieved from the database.
 */
public class LabService implements GenericService<Lab> {
	private LabDao dao;

	public LabService() {
		dao = new LabDao();
	}

	@Override
	public List<Lab> getList() {
		return dao.getList();
	}

	@Override
	public Lab getById(int id) {
		return dao.getById(id);
	}

	@Override
	public void create(Object o) {
		dao.insert((Lab) o);
	}

	@Override
	public void createOrUpdate(Object o) {
		dao.insertOrUpdate((Lab) o);
	}

	@Override
	public void delete(Object o) {
		dao.delete((Lab) o);
	}
}
