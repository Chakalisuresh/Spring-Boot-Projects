package com.springbootproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootproject.model.RTable;
import com.springbootproject.repository.RTableRepo;

@Service
public class RTableServiceImpl implements RTableService {

	@Autowired
	private RTableRepo rTableRepo;
	
	@Override
	public List<RTable> getListTables() {
		// TODO Auto-generated method stub
		return rTableRepo.findAll();
	}

	@Override
	public void saveTableDetails(RTable table) {
		rTableRepo.save(table);
		
	}

	@Override
	public RTable getTableDetailsById(Long id) {
		// TODO Auto-generated method stub
		return rTableRepo.findById(id).get();
	}

	@Override
	public void updateRTable(RTable rTable) {
		// TODO Auto-generated method stub
		rTableRepo.save(rTable);
		
	}

	@Override
	public void deleteTableById(Long id) {
		// TODO Auto-generated method stub
		rTableRepo.deleteById(id);
	}

//	@Override
//	public void saveTableData(RTable table) {
//		// TODO Auto-generated method stub
//		rTableRepo.save(table);
//		
//	}

	@Override
	public void deleteUserTableById(Long id) {
		//first we need to store the details of particular id.
		RTable data = rTableRepo.findById(id).get();
		RTable rTable=new RTable();
	rTableRepo.save(rTable);
	}

//	@Override
//	public List<RTable> getListTables() {
//		// TODO Auto-generated method stub
//		return rTableRepo.findAll();
//	}
	
	
}
