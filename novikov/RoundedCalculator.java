package ru.novikov;

public class RoundedCalculator extends SimpleCalculator{
    public String formattedDouble;
    public RoundedCalculator() {
        formattedDouble = String.format("%.2f",result);
    }
    public String sendformatteddouble() {
        return formattedDouble;
    }
}
