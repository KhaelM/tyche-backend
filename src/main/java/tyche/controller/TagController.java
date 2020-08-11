package tyche.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tyche.model.Tag;
import tyche.service.TagService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tags")
public class TagController {
	@Autowired
	TagService tagService;
	
	@GetMapping("")
	public List<Tag> getAllTags() {
		return tagService.findAllTags();
	}
}
