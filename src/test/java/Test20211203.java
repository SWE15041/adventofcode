import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yanni
 */
public class Test20211203 {

    private void day3_1(String path, Integer expect) {
        String text = ClasspathResources.text(path);
        String[] numbers = text.split("\n");
        int length = numbers[0].length();
        List<Integer> countZero = new ArrayList<>();
        List<Integer> countOne = new ArrayList<>(5);
        for (int i = 0; i < length; i++) {
            countZero.add(i, 0);
            countOne.add(i, 0);
        }
        for (String number : numbers) {
            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i) == '0') {
                    int count = countZero.get(i) == null ? 1 : countZero.get(i) + 1;
                    countZero.set(i, count);
                } else if (number.charAt(i) == '1') {
                    int count = countOne.get(i) == null ? 1 : countOne.get(i) + 1;
                    countOne.set(i, count);
                }
            }
        }

        StringBuilder gammaStr = new StringBuilder();
        StringBuilder epsilonStr = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (countOne.get(i) > countZero.get(i)) {
                gammaStr.append("1");
                epsilonStr.append("0");
            } else {
                gammaStr.append("0");
                epsilonStr.append("1");
            }
        }
        int gamma = Integer.parseInt(gammaStr.toString(), 2);
        int epsilon = Integer.parseInt(epsilonStr.toString(), 2);
        int result = gamma * epsilon;
        System.out.println("二进制：gamma=" + gammaStr);
        System.out.println("二进制：epsilon=" + epsilonStr);
        System.out.printf("十进制：result(%s) = gamma (%s) * epsilon(%s)%n", result, gamma, epsilon);
        Assertions.assertEquals(expect, result);
    }

    @Test
    public void day3_1_1() {
        day3_1("day3_1.txt", 198);
    }

    @Test
    public void day3_1_2() {
        day3_1("day3_2.txt", 845186);
    }


    private void day3_2(String path, Integer expect) {
        String text = ClasspathResources.text(path);
        List<String> numbers = Arrays.stream(text.split("\n")).collect(Collectors.toList());
        String oxygen = get(true, 0, numbers);
        String co2 = get(false, 0, numbers);
        int oxygenRate = Integer.parseInt(oxygen, 2);
        int co2Rate = Integer.parseInt(co2, 2);
        int result = oxygenRate * co2Rate;
        System.out.printf("result(%s) = oxygenRate(%s) * co2Rate(%s)", result, oxygenRate, co2Rate);
        Assertions.assertEquals(expect, result);
    }

    private String get(Boolean isOxygen, Integer position, List<String> numbers) {
        if (numbers.size() == 1) {
            return numbers.get(0);
        }
        int zeroCount = 0;
        int oneCount = 0;
        List<String> zeroNumbers = new ArrayList<>();
        List<String> oneNumbers = new ArrayList<>();
        for (String number : numbers) {
            if (number.charAt(position) == '0') {
                zeroCount++;
                zeroNumbers.add(number);
            } else {
                oneCount++;
                oneNumbers.add(number);
            }
        }
        if (isOxygen) {
            if (zeroCount == oneCount) {
                return get(isOxygen, position + 1, oneNumbers);
            } else {
                return get(isOxygen, position + 1, (zeroCount > oneCount) ? zeroNumbers : oneNumbers);
            }
        } else {
            if (zeroCount == oneCount) {
                return get(isOxygen, position + 1, zeroNumbers);
            } else {
                return get(isOxygen, position + 1, (zeroCount > oneCount) ? oneNumbers : zeroNumbers);
            }
        }
    }

    @Test
    public void day3_2_1() {
        day3_2("day3_1.txt", 230);
    }

    @Test
    public void day3_2_2() {
        day3_2("day3_2.txt", 4636702);
    }
}
