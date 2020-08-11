/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyche.dao;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tyche.model.Login;

/**
 *
 * @author michael
 */
@Transactional
@Repository
public class LoginDao extends AbstractDao<String, Login> {
    public Login findLogin(String username, String password) {
    	Session session = getSession();
		TypedQuery<Login> query = session.createQuery("from Login login where login.username = :username and login.password = :password", Login.class);
		query.setParameter("username", username);
		query.setParameter("password", password);

		return query.getSingleResult();
    }
}
