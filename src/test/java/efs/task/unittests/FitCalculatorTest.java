package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static efs.task.unittests.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended(){
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldThrowIllegalArgument_whenHeightIsZero(){
        //given
        double height = 0.0;
        double weight = 70.0;
        //when
        boolean IllegalArgumentExceptionThrown = false;
        try{
            FitCalculator.isBMICorrect(weight, height);
        }catch (IllegalArgumentException ex) {
            IllegalArgumentExceptionThrown = true;
        }
        //then
        finally {
            assertTrue(IllegalArgumentExceptionThrown);
        }
    }

    @ParameterizedTest (name = "weight: {0}, height: 1.5")
    @ValueSource(doubles = {70.0, 71.0, 72.0})
    void shouldReturnTrue_whenBmiIsValidToRecommendDiet(double weight){
        //when
        double height =  1.5;
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest (name = "weight: {0}, height: {1}")
    //given
    @CsvSource(value = {"60.0, 2.00", "61.0, 2.05", "62.0, 2.10"})
    void shouldReturnFalse_whenBmiIsNotValidToRecommendDiet(double weight, double height){
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @ParameterizedTest (name = "weight: {0}, height: {1}")
    //given
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_whenBmiIsNotValidToRecommendDietDataFromCsvFile(double weight, double height){
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(recommended);
    }

    @Test
    void shouldReturnUserWithWorstBmi_whenGivenUserList(){
        //given
        List<User> UsersList = TEST_USERS_LIST;
        //when
        User WorstBmiUser = FitCalculator.findUserWithTheWorstBMI(UsersList);
        //then
        assertTrue(WorstBmiUser.getWeight() == 97.3 && WorstBmiUser.getHeight() == 1.79);
    }

    @Test
    void shouldReturnNull_whenGivenBlankUserList(){
        //given
        List<User> UsersList = new ArrayList<>();
        //when
        User WorstBmiUser = FitCalculator.findUserWithTheWorstBMI(UsersList);
        //then
        assertEquals(null, WorstBmiUser);
    }
    @Test
    void shouldReturnUsersBmiScoreList_whenGivenUserList(){
        //given
        List<User> UsersList = TEST_USERS_LIST;
        double[] result;
        //when
        result = FitCalculator.calculateBMIScore(UsersList);
        //then
        assertEquals(TEST_USERS_BMI_SCORE.length, result.length);
        for(int i = 0; i < result.length; i++){
            assertEquals(TEST_USERS_BMI_SCORE[i], result[i]);
        }
    }
    
}