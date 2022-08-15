package com.innovative.webService;
import org.springframework.web.bind.annotation.*;
@RestController
public class DiaryController {
	@DeleteMapping("/diaryentry/{Id}")
	void deleteEntry(@PathVariable(value  = "Id") int Id) {
		DiaryDAO dao = new DiaryDAO();
		int status = dao.delete(Id);
		if (status==1) {
			//Something
		}
		else {
			//Something
		}
	}
}

