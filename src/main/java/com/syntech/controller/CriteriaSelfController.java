package com.syntech.controller;

import com.syntech.model.CriteriaSelf;
import com.syntech.repository.CriteriaSelfRepository;
import com.syntech.util.ValidationUtil;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author bipan
 */
public class CriteriaSelfController {

    private CriteriaSelfRepository criteriaSelfRepository;
    private ValidationUtil validationUtil;

    public CriteriaSelfController() {
        validationUtil = new ValidationUtil();
    }

    public void showMenu(CriteriaSelfRepository criteriaSelfRepository) {
        this.criteriaSelfRepository = criteriaSelfRepository;

        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("Criteria Self");
            System.out.println("Press 6.1 to create criteria Self");
            System.out.println("Press 6.2 to edit criteria Self");
            System.out.println("Press 6.3 to delete criteria Self");
            System.out.println("Press 6.4 to findAll criteria Self");
            System.out.println("Press 6.5 to findById criteria Self");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "6.1":
                    create();
                    break;

                case "6.2":
                    edit();
                    break;

                case "6.3":
                    delete();
                    break;

                case "6.4":
                    findAll();
                    break;

                case "6.5":
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
        Double marks = null;

        Scanner sc = new Scanner(System.in);
        while (!validationUtil.validatesLong(id)) {
            System.out.println("Enter Criteria Self id");
            id = sc.nextLong();
        }
        while (!validationUtil.validatesLong(criteriaId)) {
            System.out.println("Enter Criteria id for Criteria Self");
            criteriaId = sc.nextLong();
        }

        do {
            System.out.println("Enter Criteria Self Marks");
            marks = sc.nextDouble();
        } while (!validationUtil.validatesDouble(marks));

        CriteriaSelf criteriaSelf = new CriteriaSelf(id, criteriaId, marks);
        criteriaSelfRepository.create(criteriaSelf);
        System.out.println("Created Successfully!");
    }

    public void edit() {
        Long id = null;
        Long criteriaId = null;
        Double marks = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter criteria self id to edit");
        id = sc.nextLong();

        CriteriaSelf criteriaSelf = criteriaSelfRepository.findById(id);
        if (criteriaSelf == null) {
            System.out.println("Criteria Self with id: " + id + " not found");

        } else {

            while (!validationUtil.validatesLong(criteriaId)) {
                System.out.println("Enter Criteria id for Criteria Self");
                criteriaId = sc.nextLong();
            }

            do {
                System.out.println("Enter Criteria Self Marks");
                marks = sc.nextDouble();
            } while (!validationUtil.validatesDouble(marks));

            CriteriaSelf crself = new CriteriaSelf(id, criteriaId, marks);
            criteriaSelfRepository.edit(crself);
            System.out.println("Edited Successfully!");
        }
    }

    public void findAll() {
        Iterator<CriteriaSelf> i = criteriaSelfRepository.findAll().iterator();
        while (i.hasNext()) {
            CriteriaSelf criteriaSelf = i.next();
            System.out.println(criteriaSelf);
        }
    }

    public void findById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Criteria Self id to find");
        Long id = sc.nextLong();
        CriteriaSelf criteriaSelf = criteriaSelfRepository.findById(id);
        if (criteriaSelf == null) {
            System.out.println("Criteria Self with id: " + id + " not found");
        } else {
            System.out.println(criteriaSelf);
        }
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Criteria Self id to delete");
        Long id = sc.nextLong();
        CriteriaSelf criteriaSelf = criteriaSelfRepository.findById(id);
        if (criteriaSelf == null) {
            System.out.println("Criteria Self with id: " + id + " not found");
        } else {
            criteriaSelfRepository.delete(criteriaSelf);
            System.out.println("Criteria Self deleted Successfully");
        }
    }

}
