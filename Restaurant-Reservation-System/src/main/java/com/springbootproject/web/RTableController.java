package com.springbootproject.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbootproject.model.RTable;
import com.springbootproject.repository.RTableRepo;
import com.springbootproject.service.RTableService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
public class RTableController {

	@Autowired
	private RTableService rTableService;
	
	@Autowired
	private RTableRepo repo;

	
	@GetMapping("/adminTableList")
	public String listOfTables(Model model) {
		model.addAttribute("tables", rTableService.getListTables());
		// in tables list of data is stored
		return "adminTableList";
	}
	
	@GetMapping("/userTableList")
	public String userTableList(Model model) {
		model.addAttribute("tables", rTableService.getListTables());
		return "userTableList";
	}

	@GetMapping("/add/table")
	public String createTableForm(Model model) {

		// create table object to hold table form data
		RTable rTable = new RTable();
		model.addAttribute("tableData", rTable);
		return "adminTable";

	}

	@PostMapping("/saveTable")
	public String saveTableDetails(@ModelAttribute("tableData") RTable table) {
	
		rTableService.saveTableDetails(table);

		return "adminTableList";


	}

	@PostMapping("/saveAdminTable")
	public String saveAdminTableDetails(@ModelAttribute("tableData") RTable table, Model model) {

		RTable rTable = new RTable();
		rTableService.saveTableDetails(rTable);
		
		model.addAttribute("tables", rTableService.getListTables());
		return "adminTableList";
	}

//	@GetMapping("/adminTableData")
//	public String adminTableList(Model model) {
//		model.addAttribute("tables", rTableService.getListTables());
//		return "adminTableList";
//	}

	@GetMapping("/edit/tables/{id}")
	public String editTable(@PathVariable Long id, Model model) {
		model.addAttribute("table", rTableService.getTableDetailsById(id));

		String time = rTableService.getTableDetailsById(id).getDuration();
		if (time == null) {

			return "edit_admin";
		} else {

			model.addAttribute("tables", rTableService.getListTables());
			model.addAttribute("reserveMsg", "Oops,Can't Update The Table Because It's already Reserved");
			return "redirect:/adminTableList";
		}

	}

	@GetMapping("/edit/userTable/{id}")
	public String editUserTable(@PathVariable Long id, Model model) {
		RTable table = rTableService.getTableDetailsById(id);
		model.addAttribute("table", table);
		String status = table.getStatus();

		if ("Available".equals(status)) {
			return "editUser";
		} else {
			model.addAttribute("tables", rTableService.getListTables());
			model.addAttribute("message", "Sorry,Table is Reserved Already. Please Choose another Table");

			return "userTableList";

		}

	}

	@PostMapping("/updateTableById/{id}")
	public String updateAdminTable(@PathVariable Long id, @ModelAttribute("table") RTable rTableData, Model model) {

		// get table from database by id
		RTable rTable = rTableService.getTableDetailsById(id);
		rTable.setId(id);
		rTable.setTableNumber(rTableData.getTableNumber());
		rTable.setSeatingCapacity(rTableData.getSeatingCapacity());
		// save updated table object
		rTableService.updateRTable(rTable);
		
		model.addAttribute("tables", rTableService.getListTables());
		return "adminTableList";
	}

	
	//reserve table(user)
	@PostMapping("/updateUserTableById/{id}")
	public String updateUserTable(@PathVariable Long id, @ModelAttribute("table") RTable rTableData,Model model) {

		// get table from database by id
		RTable rTable = rTableService.getTableDetailsById(id);

		String timev = rTableData.getDuration();

		// Parse the time string to a LocalTime object
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalTime time = LocalTime.parse(timev, formatter);

		// Define the time range for comparison
		LocalTime startTime = LocalTime.of(8, 0); // 8:00
		LocalTime endTime = LocalTime.of(21, 0); // 9:00pm

		// Check if the time is within the specified range
		if (time.isAfter(startTime) && time.isBefore(endTime)) {
			rTable.setId(id);
			rTable.setTableNumber(rTableData.getTableNumber());
			rTable.setSeatingCapacity(rTableData.getSeatingCapacity());
			rTable.setDuration(rTableData.getDuration());
			rTable.setStatus(rTableData.getStatus());
			rTable.setUserName(rTableData.getUserName());

			// save updated table object
			rTableService.updateRTable(rTable);
			model.addAttribute("tables", rTableService.getListTables());

			return "userTableList";

		} else {

			model.addAttribute("tables", rTableService.getListTables());
			model.addAttribute("message", "Sorry,Please Reserve Table with in Operating Timing(8:00 - 21:00)");

			return "userTableList";

		}
	}

	// handler method to handle delete table request
	//inorder to delete table,first we need to check is it reserved or not,by duration
	@GetMapping("/table/{id}")
	public String deleteTable(@PathVariable Long id,Model model) {

		String duration = repo.findById(id).get().getDuration();

		if (duration == null) {
			rTableService.deleteTableById(id);
			model.addAttribute("tables", rTableService.getListTables());

			return "adminTableList";
		} else {
			
			
			model.addAttribute("tables", rTableService.getListTables());
			model.addAttribute("errMsg", "Oops,Can't Delete Table .It's already Reserved.");
			return "adminTableList";
		}
		

	}
	
//	@GetMapping("/loginPage")
//	public String loginPage() {
//		return "login";
//	}

	@GetMapping("/usertable/{id}")
	public String deleteUserTable(@PathVariable Long id,Model model) {
		rTableService.deleteUserTableById(id);
		//model.addAttribute("cancelReservation", "reservation cancelled");
		return "redirect:/userTableList";
		
	}

//	@PostMapping("/saveTableData")
//	public void saveTableData(@RequestBody RTable table) {
//
//		rTableService.saveTableData(table);
//	}

	
}
