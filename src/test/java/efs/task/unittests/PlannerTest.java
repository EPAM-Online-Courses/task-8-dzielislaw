package efs.task.unittests;

import efs.task.unittests.ActivityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static efs.task.unittests.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {
    Planner somePlanner;
    @BeforeEach
    public void setPlanner() {somePlanner = new Planner();}
    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldReturnCorrectCaloriesNeeded_whenActivityLevelEnum(ActivityLevel activityLevel){
        //given
        User sampleUser = TEST_USER;
        //when
        int result = somePlanner.calculateDailyCaloriesDemand(sampleUser, activityLevel);
        //then
        assertEquals(CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel), result);
    }

    @Test
    void shouldReturnCorrectDailIntake_whenGivenUser(){
        //given
        User sampleUser = TEST_USER;
        DailyIntake testIntake = TEST_USER_DAILY_INTAKE;
        //when
        DailyIntake resultIntake =  somePlanner.calculateDailyIntake(TEST_USER);
        //then
        assertEquals(testIntake.getCalories(), resultIntake.getCalories());
        assertEquals(testIntake.getCarbohydrate(), resultIntake.getCarbohydrate());
        assertEquals(testIntake.getProtein(), resultIntake.getProtein());
    }
}
