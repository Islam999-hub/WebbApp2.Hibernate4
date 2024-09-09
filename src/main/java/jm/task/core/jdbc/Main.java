package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userservice = new UserServiceImpl();

        User firstUser = new User("Islam", "Makhauri", (byte) 22);
        User secondUser = new User("Alik", "Muradov", (byte) 21);
        User thirdUser = new User("Aminat", "Zakrieva", (byte) 18);


        userservice.createUsersTable();


        userservice.saveUser(firstUser.getName(), firstUser.getLastName(), firstUser.getAge());
        System.out.println("Новый сотрудник " + ": " +  firstUser.getName() + " Добавлен в таблицу");
        userservice.saveUser(secondUser.getName(), secondUser.getLastName(), secondUser.getAge());
        System.out.println("Новый сотрудник " + ": " + secondUser.getName() + " Добавлен в таблицу");
        userservice.saveUser(thirdUser.getName(), thirdUser.getLastName(), thirdUser.getAge());
        System.out.println("Новый сотрудник " + thirdUser.getName() + " Добавлен в таблицу");

        List<User> users = userservice.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());

        }

        userservice.dropUsersTable();
        userservice.cleanUsersTable();







    }
}
