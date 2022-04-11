package com.syntech.controller;

import com.syntech.model.CriteriaTrueFalse;
import com.syntech.repository.CriteriaTrueFalseRepository;
import com.syntech.util.ValidationUtil;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author bipan
 */
public class CriteriaTrueFalseController {

    private CriteriaTrueFalseRepository criteriaTrueFalseRepository;
    private ValidationUtil validationUtil;

    public CriteriaTrueFalseController() {
        validationUtil = new ValidationUtil();
    }

    public void showMenu(CriteriaTrueFalseRepository criteriaTrueFalseRepository) {
        this.criteriaTrueFalseRepository = criteriaTrueFalseRepository;

        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("Criteria True False");
            System.out.println("Press 5.1 to create criteria True False");
            System.out.println("Press 5.2 to edit criteria True False");
            System.out.println("Press 5.3 to delete criteria True False");
            System.out.println("Press 5.4 to findAll criteria True False");
            System.out.println("Press 5.5 to findById criteria True False");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "5.1":
                    create();
                    break;

                case "5.2":
                    edit();
                    break;

                case "5.3":
                    delete();
                    break;

                case "5.4":
                    findAll();
                    break;

                case "5.5":
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
        Long criteriaId = null;
        String status = null;
        Double marks = null;

        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        while (!validationUtil.validatesLong(id)) {
            System.out.println("Enter Criteria True False id");
            id = sc.nextLong();
        }
        while (!validationUtil.validatesLong(criteriaId)) {
            System.out.println("Enter Criteria id for Criteria True False");
            criteriaId = sc.nextLong();
        }

        do {
            System.out.println("Enter Criteria True False Status");
            status = scanner.nextLine();
        } while (!validationUtil.validateString(status));

        do {
            System.out.println("Enter Marks for Criteria True False");
            marks = sc.nextDouble();
        } while (!validationUtil.validatesDouble(marks));

        CriteriaTrueFalse criteriaTrueFalse = new CriteriaTrueFalse(id, criteriaId, status, marks);

        criteriaTrueFalseRepository.create(criteriaTrueFalse);
        System.out.println("Created Successfully!");
    }

    public void edit() {
        Long id = null;
        Long criteriaId = null;
        String status = null;
        Double marks = null;

        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter criteria True False id to edit");
        id = sc.nextLong();

        CriteriaTrueFalse criteriaTrueFalse = criteriaTrueFalseRepository.findById(id);
        if (criteriaTrueFalse == null) {
            System.out.println("Criteria with id: " + id + " not found");

        } else {
            while (!validationUtil.validatesLong(criteriaId)) {
                System.out.println("Enter Criteria id for Criteria True False");
                criteriaId = sc.nextLong();
            }
            do {
                System.out.println("Enter Criteria True False Status");
                status = scanner.nextLine();
            } while (!validationUtil.validateString(status));

            do {
                System.out.println("Enter Marks for Criteria True False");
                marks = sc.nextDouble();
            } while (!validationUtil.validatesDouble(marks));

            CriteriaTrueFalse crtf = new CriteriaTrueFalse(id, criteriaId, status, marks);

            criteriaTrueFalseRepository.edit(crtf);
            System.out.println("Edited Successfully!");
        }
    }

    public void findAll() {
        Iterator<CriteriaTrueFalse> i = criteriaTrueFalseRepository.findAll().iterator();
        while (i.hasNext()) {
            CriteriaTrueFalse criteriaTrueFalse = i.next();
            System.out.println(criteriaTrueFalse);
        }
    }

    public void findById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Criteria True False id to find");
        Long id = sc.nextLong();

        CriteriaTrueFalse criteriaTrueFalse = criteriaTrueFalseRepository.findById(id);
        if (criteriaTrueFalse == null) {
            System.out.println("Criteria True False with id: " + id + " not found");
        } else {
            System.out.println(criteriaTrueFalse);
        }
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Criteria True False id to delete");
        Long id = sc.nextLong();

        CriteriaTrueFalse criteriaTrueFalse = criteriaTrueFalseRepository.findById(id);
        if (criteriaTrueFalse == null) {
            System.out.println("Criteria True False with id: " + id + " not found");
        } else {
            criteriaTrueFalseRepository.delete(criteriaTrueFalse);
            System.out.println("Criteria True False deleted Successfully");
        }
    }
}
