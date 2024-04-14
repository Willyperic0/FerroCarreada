/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.EmployeeModel;
import willy.linkedlist.doubly.LinkedList;

/**
 *
 * @author Willyperic0
 */
public class LoginController {
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

}


