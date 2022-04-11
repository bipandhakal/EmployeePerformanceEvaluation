package com.syntech.controller;

import com.syntech.model.SupervisorEvaluation;
import com.syntech.repository.SupervisorEvaluationRepository;
import com.syntech.util.ValidationUtil;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author bipan
 */
public class SupervisorEvaluationController {

    private SupervisorEvaluationRepository supervisorEvaluationRepository;
    private ValidationUtil validationUtil;

    public SupervisorEvaluationController() {
        validationUtil = new ValidationUtil();
    }

    public void showMenu(SupervisorEvaluationRepository supervisorEvaluationRepository) {
        this.supervisorEvaluationRepository = supervisorEvaluationRepository;
        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("Supervisor Evaluation");
            System.out.println("Press 8.1 to create Supervisor Evaluation");
            System.out.println("Press 8.2 to edit Supervisor Evaluation");
            System.out.println("Press 8.3 to delete Supervisor Evaluation");
            System.out.println("Press 8.4 to findAll Supervisor Evaluation");
            System.out.println("Press 8.5 to findById Supervisor Evaluation");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "8.1":
                    create();
                    break;

                case "8.2":
                    edit();
                    break;

                case "8.3":
                    delete();
                    break;

                case "8.4":
                    findAll();
                    break;

                case "8.5":
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
        Double marks = null;

        Scanner sc = new Scanner(System.in);
        while (!validationUtil.validatesLong(id)) {
            System.out.println("Enter Supervisor Evaluation id");
            id = sc.nextLong();
        }

        while (!validationUtil.validatesLong(employeeId)) {
            System.out.println("Enter Employee Id for Supervisor Evaluation");
            employeeId = sc.nextLong();
        }

        while (!validationUtil.validatesLong(criteriaId)) {
            System.out.println("Enter Criteria Id for Supervisor Evaluation");
            criteriaId = sc.nextLong();
        }

        do {
            System.out.println("Enter marks given by Supervisor");
            marks = sc.nextDouble();
        } while (!validationUtil.validatesDouble(marks));

        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(id, employeeId, criteriaId, marks);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        System.out.println("Created Successfully!");
    }

    public void edit() {
        Long id = null;
        Long employeeId = null;
        Long criteriaId = null;
        Double marks = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Supervisor evaluation id to edit");
        id = sc.nextLong();

        SupervisorEvaluation supervisorEvaluation = supervisorEvaluationRepository.findById(id);
        if (supervisorEvaluation == null) {
            System.out.println("Supervisor Evaluation with id: " + id + " not found");
        } else {
            while (!validationUtil.validatesLong(employeeId)) {
                System.out.println("Enter Employee Id for Supervisor Evaluation");
                employeeId = sc.nextLong();
            }

            while (!validationUtil.validatesLong(criteriaId)) {
                System.out.println("Enter Criteria Id for Supervisor Evaluation");
                criteriaId = sc.nextLong();
            }

            do {
                System.out.println("Enter marks given by Supervisor");
                marks = sc.nextDouble();
            } while (!validationUtil.validatesDouble(marks));

            SupervisorEvaluation sEvaluation = new SupervisorEvaluation(id, employeeId, criteriaId, marks);
            supervisorEvaluationRepository.edit(sEvaluation);
            System.out.println("Created Successfully!");
        }
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Supervisor Evaluation id to delete");
        Long id = sc.nextLong();
        SupervisorEvaluation supervisorEvaluation = supervisorEvaluationRepository.findById(id);
        if (supervisorEvaluation == null) {
            System.out.println("Supervisor Evaluation with id: " + id + " not found");
        } else {
            supervisorEvaluationRepository.delete(supervisorEvaluation);
            System.out.println("Supervisor Evaluation removed");
        }
    }

    public void findAll() {
        Iterator<SupervisorEvaluation> i = supervisorEvaluationRepository.findAll().iterator();
        while (i.hasNext()) {
            SupervisorEvaluation supervisorEvaluation = i.next();
            System.out.println(supervisorEvaluation);
        }
    }

    public void findById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Supervisor Evaluation id to find");
        Long id = sc.nextLong();

        SupervisorEvaluation supervisorEvaluation = supervisorEvaluationRepository.findById(id);
        if (supervisorEvaluation == null) {
            System.out.println("Supervisor Evaluation with id: " + id + " not found");
        } else {
            System.out.println(supervisorEvaluation);
        }
    }

}
