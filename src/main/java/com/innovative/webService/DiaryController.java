package com.innovative.webService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class DiaryController {

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/entries")
	public List<DiaryEntry> viewAllEntries() {
		List<DiaryEntry> list = DiaryDAO.getAllEntries();
		// for(DiaryEntry de:list) {
		// System.out.println(" ");
		// System.out.println(de.getContent());
		// System.out.println(de.getDate());
		// System.out.println(de.getId());
		// System.out.println(de.getTitle());
		// }
		return list;
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@DeleteMapping("/diaryentry/{Id}")
	public int deleteEntry(@PathVariable(value = "Id") int Id) {
		DiaryDAO dao = new DiaryDAO();
		int status = dao.delete(Id);
		return status;
		// if (status==1) {
		// //Something
		// return status;
		// }
		// else {
		// //Something
		// }
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PutMapping("/diaryentry/{Id}")
<<<<<<< Updated upstream
	public int updateEntry(@PathVariable(value = "Id") int Id, String title, String content, String date) {
=======
<<<<<<< HEAD
	public int updateEntry(@PathVariable(value  = "Id") int Id, @PathVariable(value ="title") String title, @PathVariable(value ="content")String content,@PathVariable(value ="date") String date ) {
=======
	public int updateEntry(@PathVariable(value = "Id") int Id, String title, String content, String date) {
>>>>>>> a21006a82815e3cac7b701216e648a6b0247d6dc
>>>>>>> Stashed changes
		DiaryEntry de = new DiaryEntry();
		de.setId(Id);
		de.setTitle(title);
		de.setContent(content);
		de.setDate(date);
		int status = DiaryDAO.update(de);
		return status;
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/entry/{Id}")
	public DiaryEntry fetchEntryByID(@PathVariable(value = "Id") int Id) {
		DiaryEntry de = DiaryDAO.getEntryByID(Id);
		return de;
	}
}
