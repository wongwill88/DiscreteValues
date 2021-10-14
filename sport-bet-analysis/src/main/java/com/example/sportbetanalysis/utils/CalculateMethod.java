package com.example.sportbetanalysis.utils;

import org.springframework.stereotype.Component;

/**
 * @ClassName: CalculateMethod
 * @Description: calculate data discrete values
 * @Author: wongwill
 * @Date: 2021/10/13 14:06
 **/
@Component
public class CalculateMethod {
    // calculate all data sum
    double Sum(double[] data) {
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum = sum + data[i];
        }
        return sum;
    }

    //calculate mean values
    double Mean(double[] data) {
        double mean = 0;
        mean = Sum(data) / data.length;
        return mean;
    }

    //sample variance
    double sampleVariance(double[] data) {
        double variance = 0;
        for (int i = 0; i < data.length; i++) {
            variance = variance + (Math.pow((data[i] - Mean(data)), 2));
        }
        variance = variance / (data.length - 1);
        return variance;
    }

    //sample standard deviation
    double sampleStandardDeviation(double[] data) {
        double stdDeviation;
        stdDeviation = Math.sqrt(sampleVariance(data));
        return stdDeviation;
    }


}
