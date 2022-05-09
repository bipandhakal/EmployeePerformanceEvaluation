package com.syntech.util;

import com.syntech.model.Criteria;
import com.syntech.model.CriteriaRange;
import com.syntech.model.CriteriaSelf;
import com.syntech.model.CriteriaTrueFalse;
import com.syntech.model.SupervisorEvaluation;
import java.util.List;

/**
 *
 * @author bipan
 */
public class CalculationUtil {

    public Double criteriaRangeCalculation(List<CriteriaRange> criteriaRangeList, Long achievement) {
        for (CriteriaRange cr : criteriaRangeList) {
            if (achievement.compareTo(cr.getFromRange()) >= 0 && achievement.compareTo(cr.getToRange()) <= 0) {
                return cr.getMarks();
            }
        }
        return 0.0;
    }

    public Double criteriaTrueFalseCalculation(List<CriteriaTrueFalse> criteriaTrueFalseList, String achievements) {
        for (CriteriaTrueFalse ctf : criteriaTrueFalseList) {
            if (achievements.equals(ctf.getStatus())) {
                return ctf.getMarks();
            }
        }
        return 0.0;
    }

//    public Double criteriaAverageCalculation(List<Criteria> criteriaList, Long achievement) {
//        for (Criteria ctr : criteriaList) {
//            if (ctr.getCalculatedBy() == CalculatedBy.AVERAGE) {
//                return ((ctr.getMarks() * achievement) / (ctr.getTarget().doubleValue()));
//            }
//        }
//        return 0.0;
    public Double criteriaAverageCalculation(Criteria ctr, Long achievement) {
        return ((ctr.getMarks() * achievement) / (ctr.getTarget().doubleValue()));
    }

    public Double finalMarks(List<CriteriaRange> criteriaRangeList, Long achievement, List<CriteriaTrueFalse> criteriaTrueFalseList, String achievements, Criteria ctr, Long achievementss, CriteriaSelf cself, SupervisorEvaluation sevaluation) {

        return criteriaRangeCalculation(criteriaRangeList, achievement)
                + criteriaTrueFalseCalculation(criteriaTrueFalseList, achievements)
                + criteriaAverageCalculation(ctr, achievementss)
                + cself.getMarks() + sevaluation.getMarks();
    }
}
