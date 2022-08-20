package com.innovative.webService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class DiaryController {

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/entries")
	public List<DiaryEntry> viewAllEntries() {
		List<DiaryEntry> list = DiaryDAO.getAllEntries();
		return list;
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@DeleteMapping("/diaryentry/{Id}")
	public int deleteEntry(@PathVariable(value = "Id") int Id) {
		DiaryDAO dao = new DiaryDAO();
		int status = dao.delete(Id);
		return status;
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PutMapping("/diaryentry/{Id}")
	public int updateEntry(@RequestBody DiaryEntry de, @PathVariable(value  = "Id") int Id) {
		DiaryEntry de1 = DiaryDAO.getEntryByID(Id);
		de1.setId(Id);
		de1.setTitle(de.getTitle());
		de1.setContent(de.getContent());
		int status = DiaryDAO.update(de1);
		return status;
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/diaryentry/{title}")
	public DiaryEntry fetchEntryByTitle( @PathVariable(value  = "title") String title) {
		DiaryEntry de = DiaryDAO.getEntryByTitle(title);

		return de;
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/diaryentry")
	public int addEntry(@RequestBody DiaryEntry de)
	{
		int status = DiaryDAO.insert(de);
		return status;
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/entry/{Id}")
	public DiaryEntry fetchEntryByID(@PathVariable(value = "Id") int Id) {
		DiaryEntry de = DiaryDAO.getEntryByID(Id);
		return de;
	}
}
