import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yanni
 */
public class Test20211204 {

    @Test
    public void day4_1_1() {
        String randomStr = "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1";
        day4_1("day4_1.txt", randomStr, 4512);
    }

    @Test
    public void day4_1_2() {
        String randomStr = "46,79,77,45,57,34,44,13,32,88,86,82,91,97,89,1,48,31,18,10,55,74,24,11,80,78,28,37,47,17,21,61,26,85,99,96,23,70,3,54,5,41,50,63,14,64,42,36,95,52,76,68,29,9,98,35,84,83,71,49,73,58,56,66,92,30,51,20,81,69,65,15,6,16,39,43,67,7,59,40,60,4,90,72,22,0,93,94,38,53,87,27,12,2,25,19,8,62,33,75";
        day4_1("day4_2.txt", randomStr, 4512);
    }

    /**
     * 5*5的棋盘
     */
    private void day4_1(String path, String randomStr, Integer expect) {
        List<Integer> randoms = Arrays.stream(randomStr.split(",")).map(NumberUtil::getNumber).collect(Collectors.toList());
        String text = ClasspathResources.text(path);
        List<String> numbers = Arrays.stream(text.split("\n")).filter(x -> !"".equals(x)).collect(Collectors.toList());

        List<MyGrid> myGrids = new ArrayList<>();
        MyGrid grid = new MyGrid();
        for (int x = 0; x < numbers.size(); x++) {
            String rows = numbers.get(x);
            int i = x % 5;
            List<Integer> row = Arrays.stream(rows.split(" ")).filter(o -> !"".equals(o)).map(NumberUtil::getNumber).collect(Collectors.toList());
            for (int j = 0; j < row.size(); j++) {
                grid.grids[i][j] = row.get(j);
                grid.markers[i][j] = 0;
            }
            if ((x + 1) % 5 == 0) {
                myGrids.add(grid);
                grid = new MyGrid();
            }
        }

        MyGrid win = win(randoms, myGrids);
        int winScore = win.winValue;
        int unMarkerScore = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (win.markers[i][j] == 0) {
                    unMarkerScore += win.grids[i][j];
                }
            }
        }
        int score = winScore * unMarkerScore;
        System.out.printf("score(%s)= winScore(%s) * unMarkerScore(%s)", score, winScore, unMarkerScore);
        Assertions.assertEquals(expect, score);

    }

    private MyGrid win(List<Integer> randoms, List<MyGrid> myGrids) {
        for (Integer random : randoms) {
            if (random == 24) {
                System.out.println("?");
            }
            for (MyGrid myGrid : myGrids) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        Integer number = myGrid.grids[i][j];
                        if (number.intValue() == random.intValue()) {
                            myGrid.markers[i][j] = 1;
                            Boolean hasWin = hasWin(myGrid.markers, i, j);
                            if (hasWin) {
                                myGrid.i = i;
                                myGrid.j = j;
                                myGrid.winValue = myGrid.grids[i][j];
                                return myGrid;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static class MyGrid {
        Integer[][] grids = new Integer[5][5];
        Integer[][] markers = new Integer[5][5];
        Integer winValue;
        Integer i;
        Integer j;
    }

    public Boolean hasWin(Integer[][] markers, Integer i, Integer j) {
        for (int x = 0; x < 5; x++) {
            if (markers[x][j] != 1) {
                break;
            }
            if (x == 4) return true;
        }
        for (int x = 0; x < 5; x++) {
            if (markers[i][x] != 1) {
                break;
            }
            if (x == 4) return true;
        }

        return false;
    }
}

