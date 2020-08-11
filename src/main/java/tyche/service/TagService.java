package tyche.service;

import org.springframework.stereotype.Service;

import tyche.dao.TagDao;
import tyche.model.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service("tagService")
public class TagService {
	@Autowired
	TagDao tagDao;
	
	public List<Tag> findAllTags() {
		return tagDao.findAllTags();
	}
}
