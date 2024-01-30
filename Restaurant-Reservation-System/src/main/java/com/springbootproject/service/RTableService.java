package com.springbootproject.service;

import java.util.List;

import com.springbootproject.model.RTable;



public interface RTableService {
	
	//it return list of RTable
	List<RTable> getListTables();

	//save the data to database(RTable data)
	void saveTableDetails(RTable table);

	//select particular id from the table(find by id)
	RTable getTableDetailsById(Long id);

	//update 
	void updateRTable(RTable rTable);

	void deleteTableById(Long id);

	//void saveTableData(RTable table);

	void deleteUserTableById(Long id);
	
	//Return list of table
//	List<RTable> saveTable();
}
