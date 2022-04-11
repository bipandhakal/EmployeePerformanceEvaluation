package com.syntech.controller;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Criteria;
import com.syntech.repository.CriteriaRepository;
import com.syntech.util.ValidationUtil;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author bipan
 */
public class CriteriaController {

    private CriteriaRepository criteriaRepository;
    private ValidationUtil validationUtil;

    public CriteriaController() {
        validationUtil = new ValidationUtil();
    }

    public void showMenu(CriteriaRepository criteriaRepository) {
        this.criteriaRepository = criteriaRepository;

        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("Criteria");
            System.out.println("Press 3.1 to create criteria");
            System.out.println("Press 3.2 to edit criteria");
            System.out.println("Press 3.3 to delete criteria");
            System.out.println("Press 3.4 to findAll criteria");
            System.out.println("Press 3.5 to findById criteria");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "3.1":
                    create();
                    break;

                case "3.2":
                    edit();
                    break;

                case "3.3":
                    delete();
                    break;

                case "3.4":
                    findAll();
                    break;

                case "3.5":
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
        Long categoryId = null;
        String name = null;
        Double marks = null;
        BigDecimal target = null;
        String calculatedBy = null;

        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        while (!validationUtil.validatesLong(id)) {
            System.out.println("Enter Criteria id");
            id = sc.nextLong();
        }

        do {
            System.out.println("Enter Category id for this criteria");
            categoryId = sc.nextLong();
        } while (!validationUtil.validatesLong(categoryId));

        while (!validationUtil.validateString(name)) {
            System.out.println("Enter Criteria Name");
            name = scanner.nextLine();
        }

        do {
            System.out.println("Enter Marks for Criteria");
            marks = sc.nextDouble();
        } while (!validationUtil.validatesDouble(marks));

        do {
            System.out.println("Enter Target for Criteria");
            target = sc.nextBigDecimal();
        } while (!validationUtil.validateBigDecimal(target));

        do {
            System.out.println("Enter Method to calculate Criteria");
            calculatedBy = sc.next();
        } while (validationUtil.validateCalculatedBy(calculatedBy) == null);

        CalculatedBy calcBy = CalculatedBy.valueOf(calculatedBy.toUpperCase());

        Criteria criteria = new Criteria(id, categoryId, name, marks, target, calcBy);

        criteriaRepository.create(criteria);
        System.out.println("Created Successfully!");

    }

    public void edit() {
        Long id = null;
        Long categoryId = null;
        String name = null;
        Double marks = null;
        BigDecimal target = null;
        String calculatedBy = null;

        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter criteria id to edit");
        id = sc.nextLong();

        Criteria criteria = criteriaRepository.findById(id);
        if (criteria == null) {
            System.out.println("Criteria with id: " + id + " not found");

        } else {

            while (!validationUtil.validatesLong(categoryId)) {
                System.out.println("Enter Category id for this criteria");
                categoryId = sc.nextLong();
            }
            do {
                System.out.println("Enter Criteria Name");
                name = scanner.nextLine();
            } while (!validationUtil.validateString(name));

            do {
                System.out.println("Enter Marks for Criteria");
                marks = sc.nextDouble();
            } while (!validationUtil.validatesDouble(marks));

            do {
                System.out.println("Enter Target for Criteria");
                target = sc.nextBigDecimal();
            } while (!validationUtil.validateBigDecimal(target));

            do {
                System.out.println("Enter Method to calculate Criteria");
                calculatedBy = sc.next();
            } while (validationUtil.validateCalculatedBy(calculatedBy) == null);

            CalculatedBy calcBy = CalculatedBy.valueOf(calculatedBy.toUpperCase());
            Criteria crt = new Criteria(id, categoryId, name, marks, target, calcBy);

            criteriaRepository.edit(crt);
            System.out.println("Edited Successfully!");
        }
    }

    public void findAll() {
        Iterator<Criteria> i = criteriaRepository.findAll().iterator();
        while (i.hasNext()) {
            Criteria criteria = i.next();
            System.out.println(criteria);
        }
    }

    public void findById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Criteria id to find");
        Long id = sc.nextLong();

        Criteria criteria = criteriaRepository.findById(id);
        if (criteria == null) {
            System.out.println("Criteria with id: " + id + " not found");
        } else {
            System.out.println(criteria);
        }
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Criteria id to delete");
        Long id = sc.nextLong();

        Criteria criteria = criteriaRepository.findById(id);
        if (criteria == null) {
            System.out.println("Criteria with id: " + id + " not found");
        } else {
            criteriaRepository.delete(criteria);
            System.out.println("Criteria removed");
        }
    }

}
