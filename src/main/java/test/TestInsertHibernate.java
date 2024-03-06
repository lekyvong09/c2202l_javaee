package test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ray.config.HibernateSessionFactoryConfig;
import com.ray.entity.User;

public class TestInsertHibernate {

	public static void main(String[] args) {
		Transaction transaction = null;
		
		try (Session session = HibernateSessionFactoryConfig.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			System.out.println("Create new object");
			User newUser = new User("ray@email.com", "ray le", "password");
			
			System.out.println("Saving user");
			System.out.println(newUser);
			session.save(newUser);
			
			System.out.println("Generated id: " + newUser.getUserId());
			
			User user = session.get(User.class, newUser.getUserId());
			System.out.println(user);
			
			System.out.println("Done");
			
			transaction.commit();

			
		} catch (Exception ex) {
			if (transaction != null) 
			{
				transaction.rollback();
			}
			
			ex.printStackTrace();
		}

	}

}
