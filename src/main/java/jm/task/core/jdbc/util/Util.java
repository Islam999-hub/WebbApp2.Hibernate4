package jm.task.core.jdbc.util;
//import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.*;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;*/


import jm.task.core.jdbc.model.User;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class Util {

    private static Connection connection;
    public static Connection getConnection() {
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
        } catch (SQLException e) {
            System.err.println("Нет подключения к системе");


    }
        return connection;

    }

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        try{
            if (sessionFactory == null) {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mysql");
                settings.put(Environment.USER, "Root5");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, " ");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            }
        }

        catch (Exception e) {
            System.err.println("Подключение к сети отсутствует");
        }
        return sessionFactory;
    }












    }










