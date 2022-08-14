package DiaryMVC;
import org.springframework.web.bind.annotation.*;
@RestController
public class DiaryController {
	@DeleteMapping("/diaryentry/{Id}")
	void deleteEntry(@PathVariable Long Id) {
		
	}
}

