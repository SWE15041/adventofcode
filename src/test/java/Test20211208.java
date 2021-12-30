import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test20211208 {

    @Test
    public void inputOneToPartOne() {
        partOne("day8_1.txt", 26);
    }

    @Test
    public void inputTwoToPartOne() {
        partOne("day8_2.txt", 0);

    }

    public void inputOneToPartTwo() {

    }

    public void inputTwoToPartTwo() {

    }

    private void partOne(String path, Integer expect) {
        String text = ClasspathResources.text(path);
        List<String> lineStrs = Arrays.stream(text.split("\n")).collect(Collectors.toList());
        StringBuilder line = new StringBuilder();
        List<String> newLines = new ArrayList<>();
        for (int i = 0; i < lineStrs.size(); i++) {
            String currentLine = lineStrs.get(i);
            System.out.printf("'%s',\n",currentLine);
//            if ((i + 1) % 2 == 0) {
//                line.append(currentLine);
//                newLines.add(line.toString());
//                System.out.printf("'%s',\n",line);
//                line = new StringBuilder();
//            } else {
//                line.append(currentLine);
//            }
        }
    }

    private void partTwo() {

    }
}

