package tyche.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tyche.model.Tag;

@Transactional
@Repository
public class TagDao extends AbstractDao<String, Tag> {
	public List<Tag> findAllTags() {
		Session session = getSession();
		TypedQuery<Tag> query = session.createQuery("from Tag", Tag.class);
		return query.getResultList();
	}
}
