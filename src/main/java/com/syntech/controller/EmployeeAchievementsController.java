package com.syntech.controller;

import com.syntech.model.EmployeeAchievements;
import com.syntech.repository.EmployeeAchievementsRepository;
import com.syntech.util.ValidationUtil;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author bipan
 */
public class EmployeeAchievementsController {

    private EmployeeAchievementsRepository employeeAchievementsRepository;
    private ValidationUtil validationUtil;

    public EmployeeAchievementsController() {
        validationUtil = new ValidationUtil();
    }

    public void showMenu(EmployeeAchievementsRepository employeeAchievementsRepository) {
        this.employeeAchievementsRepository = employeeAchievementsRepository;
        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("Employee Achievements");
            System.out.println("Press 7.1 to create Employee Achievements");
            System.out.println("Press 7.2 to edit Employee Achievements");
            System.out.println("Press 7.3 to delete Employee Achievements");
            System.out.println("Press 7.4 to findAll Employee Achievements");
            System.out.println("Press 7.5 to findById Employee Achievements");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "7.1":
                    create();
                    break;

                case "7.2":
                    edit();
                    break;

                case "7.3":
                    delete();
                    break;

                case "7.4":
                    findAll();
                    break;

                case "7.5":
                    findById();
                    break;

                case "*":
                    MainController.showMenu();
                    break;

                default:
                    System.out.println("Invalid number");
                    break;
            }
        } while (!num.equals("0"));
    }

    public void create() {
        Long id = null;
        Long employeeId = null;
        Long criteriaId = null;
        String achievement = null;

        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        while (!validationUtil.validatesLong(id)) {
            System.out.println("Enter Employee Achievements id");
            id = sc.nextLong();
        }

        while (!validationUtil.validatesLong(employeeId)) {
            System.out.println("Enter Employee Id of Employee Achievement");
            employeeId = sc.nextLong();
        }

        while (!validationUtil.validatesLong(criteriaId)) {
            System.out.println("Enter Criteria Id of Employee Achievement");
            criteriaId = sc.nextLong();
        }

        while (!validationUtil.validateString(achievement)) {
            System.out.println("Enter Employee Achievements");
            achievement = scanner.nextLine();
        }

        EmployeeAchievements employeeAchievement = new EmployeeAchievements(id, employeeId, criteriaId, achievement);
        employeeAchievementsRepository.create(employeeAchievement);
        System.out.println("Created Successfully!");

    }

    public void edit() {
        Long id = null;
        Long employeeId = null;
        Long criteriaId = null;
        String achievement = null;

        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee Achievements id to edit");
        id = sc.nextLong();

        EmployeeAchievements employeeAchievement = employeeAchievementsRepository.findById(id);
        if (employeeAchievement == null) {
            System.out.println("Employee Achievements with id: " + id + " not found");
        } else {
            while (!validationUtil.validatesLong(employeeId)) {
                System.out.println("Enter Employee Id of Employee Achievement");
                employeeId = sc.nextLong();
            }

            while (!validationUtil.validatesLong(criteriaId)) {
                System.out.println("Enter Criteria Id of Employee Achievement");
                criteriaId = sc.nextLong();
            }

            while (!validationUtil.validateString(achievement)) {
                System.out.println("Enter Employee Achievements");
                achievement = scanner.nextLine();
            }
            EmployeeAchievements eAchievement = new EmployeeAchievements(id, employeeId, criteriaId, achievement);
            employeeAchievementsRepository.edit(eAchievement);
            System.out.println("Edited Successfully!");

        }
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee Achievements id to delete");
        Long id = sc.nextLong();

        EmployeeAchievements employeeAchievements = employeeAchievementsRepository.findById(id);
        if (employeeAchievements == null) {
            System.out.println("Employee Achievements with id: " + id + " not found");
        } else {
            employeeAchievementsRepository.delete(employeeAchievements);
            System.out.println("Employee Achievements removed");
        }
    }

    public void findAll() {
        Iterator<EmployeeAchievements> i = employeeAchievementsRepository.findAll().iterator();
        while (i.hasNext()) {
            EmployeeAchievements employeeAchievements = i.next();
            System.out.println(employeeAchievements);
        }
    }

    public void findById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee Achievements id to find");
        Long id = sc.nextLong();

        EmployeeAchievements employeeAchievements = employeeAchievementsRepository.findById(id);
        if (employeeAchievements == null) {
            System.out.println("Employee Achievemnts with id: " + id + " not found");
        } else {
            System.out.println(employeeAchievements);
        }
    }

}
