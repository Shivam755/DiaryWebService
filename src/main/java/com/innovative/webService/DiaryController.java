package com.innovative.webService;
import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
public class DiaryController {
	
	@GetMapping("/entries")
	public List<DiaryEntry> viewAllEntries() {
		List<DiaryEntry> list = DiaryDAO.getAllEntries();
//		for(DiaryEntry de:list) {
//			System.out.println(" ");
//			System.out.println(de.getContent());
//			System.out.println(de.getDate());
//			System.out.println(de.getId());
//			System.out.println(de.getTitle());
//		}
		return list;
	}
	
	
	@DeleteMapping("/diaryentry/{Id}")
	public int deleteEntry(@PathVariable(value  = "Id") int Id) {
		DiaryDAO dao = new DiaryDAO();
		int status = dao.delete(Id);
		return status;
//		if (status==1) {
//			//Something
//			return status;
//		}
//		else {
//			//Something
//		}
	}
	
}