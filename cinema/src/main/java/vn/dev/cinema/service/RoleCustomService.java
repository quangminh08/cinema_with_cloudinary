package vn.dev.cinema.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Service;

import vn.dev.cinema.entity.Role;
import vn.dev.cinema.entity.User;

@Service
public class RoleCustomService{
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Role> getRoles(User user){
		StringBuilder sql = new StringBuilder().append("select r.name as name from cinimakhoa.tbl_user u join tbl_user_role ur\n"
						+ " on u.id=ur.user_id join tbl_role r on r.id=ur.role_id ");
		sql.append("where 1=1 ");
		if (user.getUsername() != null) {
			sql.append(" and username = :username");
		}
		
		NativeQuery<Role> query = ((Session)entityManager.getDelegate()).createNativeQuery(sql.toString());
		
		if(user.getUsername() != null) {
			query.setParameter("username", user.getUsername());
		}
		
		query.addScalar("name", StandardBasicTypes.STRING);
		query.setResultTransformer(Transformers.aliasToBean(Role.class));
		System.out.println("Query get roles: " + sql);
		return query.list();
		
//		String sql = "select r.name as name from cinimakhoa.tbl_user u join tbl_user_role ur\n"
//				+ " on u.id=ur.user_id join tbl_role r on r.id=ur.role_id ";
//		sql += "where 1=1 ";
//		if (user.getUsername() != null) {
//			sql += " and username = '" + user.getUsername() +"' ";
//		}
//		
//		System.out.println("Query get roles: " + sql);
//		try {
//			Query query = entityManager.createNativeQuery(sql, Role.class);
//			return query.getResultList();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ArrayList<Role>();
//		}
		
	}

	
	
	
}
