/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.EmployeeModel;
import view.LoginView;
import willy.linkedlist.doubly.LinkedList;

/**
 *
 * @author Willyperic0
 */
public class LoginController {
    static LoginView loginView;
    public boolean authenticate(String username, String password, LinkedList<EmployeeModel> employees) {
        int size = employees.size();
        for (int i = 0; i < size; i++) {
            EmployeeModel employee = employees.get(i);
            if (employee.getUser().equals(username) && employee.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public int getLoggedUserDNI(String username, LinkedList<EmployeeModel> employees) {
        int size = employees.size();
        for (int i = 0; i < size; i++) {
            EmployeeModel employee = employees.get(i);
            if (employee.getUser().equals(username)) {
                return employee.getDni();
            }
        }
        return -1; // Retornar un valor negativo si el usuario no se encuentra o si hay un problema
    }

    public static int loggedDNI() {
        int cc =  loginView.getLoggedUserDNI();
        return cc;
    }
}