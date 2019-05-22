package coreElements;

public class Utils {
    public static Integer numberBetween(Integer x, Integer y){
        return (int) (Math.random()*(y-x) + x);
    }
}
