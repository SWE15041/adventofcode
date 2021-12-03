import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test20211201 {

    private void day1_1(String path, Integer expect) {
        String text = ClasspathResources.text(path);
        List<Integer> targets = Arrays.stream(text.split("\n")).map(Test20211201::getNumber).collect(Collectors.toList());
        int result = 0;
        for (int i = 0; i < targets.size(); i++) {
            int j = i + 1;
            if (i == targets.size() - 1) break;
            if (targets.get(j) > targets.get(i)) {
                result++;
            }
        }
        System.out.println("times = " + result);
        Assertions.assertEquals(expect, result);
    }

    @Test
    public void day1_1_1() {
        day1_1("day1_1.txt", 7);
    }

    @Test
    public void day1_1_2() {
        day1_1("day1_2.txt", 1316);
    }


    private void day1_2(String path, Integer expect) {
        String text = ClasspathResources.text(path);// 1344
        List<Integer> sources = Arrays.stream(text.split("\n")).map(Test20211201::getNumber).collect(Collectors.toList());
        int result = 0;
        int preTarget = 0;
        for (int i = 0; i < sources.size(); i++) {
            if (i == sources.size() - 2) break;
            int target = sources.get(i) + sources.get(i + 1) + sources.get(i + 2);
            if (i != 0 && target > preTarget) {
                result++;
            }
            preTarget = target;
        }
        System.out.println("times = " + result);
        Assertions.assertEquals(expect, result);
    }

    @Test
    public void day1_2_1() {
        day1_2("day1_1.txt", 5);
    }

    @Test
    public void day1_2_2() {
        day1_2("day1_2.txt", 1344);
    }

    private static Integer getNumber(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(s);
        String x = matcher.replaceAll("");
        return Integer.valueOf(x);
    }
}

