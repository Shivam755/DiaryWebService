package com.innovative.webService;
import org.springframework.web.bind.annotation.*;


@RestController
public class DiaryController {
	
	@GetMapping("/entries")
	public String entries() {
		
		return "hello world";
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