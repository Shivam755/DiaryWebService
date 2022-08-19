package com.innovative.webService;
import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
public class DiaryController {
	
	@GetMapping("/entries")
	public List<DiaryEntry> viewAllEntries() {
		List<DiaryEntry> list = DiaryDAO.getAllEntries();

//		}
		return list;
	}
	
	
	@DeleteMapping("/diaryentry/{Id}")
	public int deleteEntry(@PathVariable(value  = "Id") int Id) {
		DiaryDAO dao = new DiaryDAO();
		int status = dao.delete(Id);
		return status;
	
	}
	
	@PutMapping("/diaryentry/{Id}")
	public int updateEntry(@RequestBody DiaryEntry de, @PathVariable(value  = "Id") int Id) {
		DiaryEntry de1 = DiaryDAO.getEntryByID(Id);
		de1.setId(Id);
		de1.setTitle(de.getTitle());
		de1.setContent(de.getContent());
		de1.setDate(de.getDate());
		int status = DiaryDAO.update(de1);
		return status;	
	}
	}
