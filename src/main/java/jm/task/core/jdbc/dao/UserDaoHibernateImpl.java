package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = getSessionFactory();
    private Session session;




    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS user, (id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(64) NULL, lastName VARCHAR(64) NULL, age BIGINT NULL)" +
                    "PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.err.println("Такая таблица уже существует");

        }




    }

    @Override
    public void dropUsersTable() {
        try(Session session = getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS user";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Произошла ошибка при удалении таблицы");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();

        } catch (Exception e) {
            System.err.println("Произошла ошибка при добавлении данных");
        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Произошла ошибка при удалении даннго пользователя");
        }

    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Ошибка при выводе сотрудников из базы данных");
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM user").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Ошибка при удалении данных из таблицы");
        }

    }
}
