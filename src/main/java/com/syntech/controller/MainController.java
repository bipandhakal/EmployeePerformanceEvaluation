package com.syntech.controller;

import com.syntech.repository.CategoryRepository;
import com.syntech.repository.CriteriaRangeRepository;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.CriteriaSelfRepository;
import com.syntech.repository.CriteriaTrueFalseRepository;
import com.syntech.repository.EmployeeAchievementsRepository;
import com.syntech.repository.EmployeeRepository;
import com.syntech.repository.SupervisorEvaluationRepository;
import java.util.Scanner;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author bipan
 */
@RequestScoped
public class MainController {

    static EmployeeController employeeController;
    static CategoryController categoryController;
    static CriteriaController criteriaController;
    static EmployeeRepository employeeRepository;
    static CategoryRepository categoryRepository;
    static CriteriaRepository criteriaRepository;
    static CriteriaRangeController criteriaRangeController;
    static CriteriaTrueFalseController criteriaTrueFalseController;
    static CriteriaSelfController criteriaSelfController;
    static EmployeeAchievementsController employeeAchievementsController;
    static SupervisorEvaluationController supervisorEvaluationController;
    static CriteriaRangeRepository criteriaRangeRepository;
    static CriteriaTrueFalseRepository criteriaTrueFalseRepository;
    static CriteriaSelfRepository criteriaSelfRepository;
    static EmployeeAchievementsRepository employeeAchievementsRepository;
    static SupervisorEvaluationRepository supervisorEvaluationRepository;

    public static void main(String[] args) {
        employeeController = new EmployeeController();
        categoryController = new CategoryController();
        criteriaController = new CriteriaController();
        criteriaRangeController = new CriteriaRangeController();
        criteriaTrueFalseController = new CriteriaTrueFalseController();
        criteriaSelfController = new CriteriaSelfController();
        employeeAchievementsController = new EmployeeAchievementsController();
        supervisorEvaluationController = new SupervisorEvaluationController();
        employeeRepository = new EmployeeRepository();
        categoryRepository = new CategoryRepository();
        criteriaRepository = new CriteriaRepository();
        criteriaRangeRepository = new CriteriaRangeRepository();
        criteriaTrueFalseRepository = new CriteriaTrueFalseRepository();
        criteriaSelfRepository = new CriteriaSelfRepository();
        employeeAchievementsRepository = new EmployeeAchievementsRepository();
        supervisorEvaluationRepository = new SupervisorEvaluationRepository();
        showMenu();
    }

    public static void showMenu() {

        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("1.Employee");
            System.out.println("2.Category");
            System.out.println("3.Criteria");
            System.out.println("4.Criteria Range");
            System.out.println("5.Criteria True False");
            System.out.println("6.Criteria Self Marks");
            System.out.println("7.Employee Achievements");
            System.out.println("8.Supervisor Evaluation");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "1":
//                    employeeController.showMenu(employeeRepository);
                    break;

                case "2":
//                    categoryController.showMenu(categoryRepository);
                    break;

                case "3":
//                    criteriaController.showMenu(criteriaRepository);
                    break;

                case "4":
//                    criteriaRangeController.showMenu(criteriaRangeRepository);
                    break;

                case "5":
//                    criteriaTrueFalseController.showMenu(criteriaTrueFalseRepository);
                    break;

                case "6":
//                    criteriaSelfController.showMenu(criteriaSelfRepository);
                    break;

                case "7":
//                    employeeAchievementsController.showMenu(employeeAchievementsRepository);
                    break;

                case "8":
                    supervisorEvaluationController.showMenu(supervisorEvaluationRepository);
                    break;

                default:
                    System.out.println("Invalid number");
                    break;
            }
        } while (!num.equals("0"));

    }
}
