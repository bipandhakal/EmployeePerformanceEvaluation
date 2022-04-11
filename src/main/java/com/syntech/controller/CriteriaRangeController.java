package com.syntech.controller;

import com.syntech.model.CriteriaRange;
import com.syntech.repository.CriteriaRangeRepository;
import com.syntech.util.ValidationUtil;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author bipan
 */
public class CriteriaRangeController {

    private CriteriaRangeRepository criteriaRangeRepository;
    private ValidationUtil validationUtil;

    public CriteriaRangeController() {
        validationUtil = new ValidationUtil();
    }

    public void showMenu(CriteriaRangeRepository criteriaRangeRepository) {
        this.criteriaRangeRepository = criteriaRangeRepository;

        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("Criteria Range");
            System.out.println("Press 4.1 to create criteria range");
            System.out.println("Press 4.2 to edit criteria range");
            System.out.println("Press 4.3 to delete criteria range");
            System.out.println("Press 4.4 to findAll criteria range");
            System.out.println("Press 4.5 to findById criteria range");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "4.1":
                    create();
                    break;

                case "4.2":
                    edit();
                    break;

                case "4.3":
                    delete();
                    break;

                case "4.4":
                    findAll();
                    break;

                case "4.5":
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
        Long from = null;
        Long to = null;
        Double marks = null;

        Scanner sc = new Scanner(System.in);
        while (!validationUtil.validatesLong(id)) {
            System.out.println("Enter Criteria Range id");
            id = sc.nextLong();
        }
        while (!validationUtil.validatesLong(criteriaId)) {
            System.out.println("Enter Criteria id for Criteria Range");
            criteriaId = sc.nextLong();
        }
        do {
            System.out.println("Enter Criteria Range from");
            from = sc.nextLong();
        } while (!validationUtil.validatesLong(from));

        do {
            System.out.println("Enter Criteria Range to");
            to = sc.nextLong();
        } while (!validationUtil.validatesLong(to));

        do {
            System.out.println("Enter Marks for Criteria Range");
            marks = sc.nextDouble();
        } while (!validationUtil.validatesDouble(marks));

        CriteriaRange criteriaRange = new CriteriaRange(id, criteriaId, from, to, marks);
        criteriaRangeRepository.create(criteriaRange);
        System.out.println("Created Successfully!");
    }

    public void edit() {
        Long id = null;
        Long criteriaId = null;
        Long from = null;
        Long to = null;
        Double marks = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter criteria range id to edit");
        id = sc.nextLong();

        CriteriaRange criteriaRange = criteriaRangeRepository.findById(id);
        if (criteriaRange == null) {
            System.out.println("Criteria Range with id: " + id + " not found");

        } else {
            while (!validationUtil.validatesLong(criteriaId)) {
                System.out.println("Enter Criteria id for Criteria Range");
                criteriaId = sc.nextLong();
            }
            while (!validationUtil.validatesLong(from)) {
                System.out.println("Enter Criteria Range from");
                from = sc.nextLong();
            }

            do {
                System.out.println("Enter Criteria Range to");
                to = sc.nextLong();
            } while (!validationUtil.validatesLong(to));

            do {
                System.out.println("Enter Marks for Criteria Range");
                marks = sc.nextDouble();
            } while (!validationUtil.validatesDouble(marks));

            CriteriaRange crtR = new CriteriaRange(id, criteriaId, from, to, marks);
            criteriaRangeRepository.edit(crtR);
            System.out.println("Edited Successfully!");
        }
    }

    public void findAll() {
        Iterator<CriteriaRange> i = criteriaRangeRepository.findAll().iterator();
        while (i.hasNext()) {
            CriteriaRange criteriaRange = i.next();
            System.out.println(criteriaRange);
        }
    }

    public void findById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Criteria Range id to find");
        Long id = sc.nextLong();

        CriteriaRange criteriaRange = criteriaRangeRepository.findById(id);
        if (criteriaRange == null) {
            System.out.println("Criteria Range with id: " + id + " not found");
        } else {
            System.out.println(criteriaRange);
        }
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Criteria Range id to delete");
        Long id = sc.nextLong();

        CriteriaRange criteriaRange = criteriaRangeRepository.findById(id);
        if (criteriaRange == null) {
            System.out.println("Criteria Range with id: " + id + " not found");
        } else {
            criteriaRangeRepository.delete(criteriaRange);
            System.out.println("Criteria Range deleted Successfully");
        }
    }
}
