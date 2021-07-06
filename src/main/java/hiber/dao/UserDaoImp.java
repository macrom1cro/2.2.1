package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User getUserByCar(String model, int series){
      String HQL="FROM User u LEFT JOIN FETCH u.car WHERE u.car.model=:carModel and u.car.series=:carSeries";
      Query<User> query = sessionFactory.getCurrentSession().createQuery(HQL, User.class);
      query.setParameter("carModel", model);
      query.setParameter("carSeries", series);
      return query.uniqueResult();
   }

}
