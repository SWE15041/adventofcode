
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test20211202 {

    private void day2_1(String path, Integer expect) {
        String text = ClasspathResources.text(path);
        String[] split = text.split("\n");
        int totalForward = 0;
        int totalDown = 0;
        int totalUp = 0;
        for (String s : split) {
            int number = getNumber(s);
            if (s.contains("forward")) {
                totalForward += number;
            } else if (s.contains("down")) {
                totalDown += number;
            } else if (s.contains("up")) {
                totalUp += number;
            }
        }
        int result = totalForward * (totalDown - totalUp);
        System.out.println("day2_1: totalForward * (totalDown - totalUp) = " + result);
        Assertions.assertEquals(expect, result);
    }

    @Test
    public void day2_1_1() {
        day2_1("day2_1.txt", 150);
    }

    @Test
    public void day2_1_2() {
        day2_1("day2_2.txt", 1480518);
    }


    /**
     * init : aim=0;
     * if (action==forward) {
     * depth = depth + aim * x;
     * horizontal = horizontal + x;
     * } else if(action=down){
     * aim += x;
     * }else if（action==up）{
     * aim -= x;
     * }
     * result = depth * horizontal;
     */
    private void day2_2(String path, Integer expect) {
        String text = ClasspathResources.text(path);
        String[] split = text.split("\n");
        Integer aim = 0;
        int depth = 0;
        int horizontal = 0;
        for (String s : split) {
            Integer x = getNumber(s);
            if (s.contains("forward")) {
                horizontal = horizontal + x;
                depth = depth + aim * x;
            } else if (s.contains("down")) {
                aim = aim + x;
            } else if (s.contains("up")) {
                aim = aim - x;
            }
        }
        int result = horizontal * depth;
        System.out.println("day2_2: horizontal * depth = " + result);
        Assertions.assertEquals(expect, result);
    }

    @Test
    public void day2_2_1() {
        day2_2("day2_1.txt", 900);
    }

    @Test
    public void day2_2_2() {
        day2_2("day2_2.txt", 1282809906);
    }


    private static Integer getNumber(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(s);
        String x = matcher.replaceAll("");
        return Integer.valueOf(x);
    }

}

