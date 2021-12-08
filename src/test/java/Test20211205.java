import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Test20211205 {

    @Test
    public void day5_1_1() {
        day5_1("day5_1.txt", 5);
    }

    @Test
    public void day5_1_2() {
        day5_1("day5_2.txt", 20012);
    }

    /**
     * 条件：只考虑水平和垂直方向的直线
     * 要求：计算点的重合次数>=2
     */
    private void day5_1(String path, Integer expect) {
        List<Line> lines = formatInputData(path);
        List<OverlapPoint> overlapPoints = overlapPoints(lines);
        Map<OverlapPoint, List<OverlapPoint>> pointListMap = overlapPoints.stream().collect(Collectors.groupingBy(overlapPoint -> overlapPoint));
        /**
         * 统计每个点重复的次数
         */
        Iterator<Map.Entry<OverlapPoint, List<OverlapPoint>>> iterator = pointListMap.entrySet().iterator();
        Map<Integer, Integer> countMap = new HashMap<>();
        int leastTwoLinesOverlapCount = 0;
        while (iterator.hasNext()) {
            Map.Entry<OverlapPoint, List<OverlapPoint>> next = iterator.next();
            int overlapNumbers = next.getValue().size();
//            System.out.println(next.getKey() + "\t" + overlapNumbers);
            if (overlapNumbers >= 2) {
                leastTwoLinesOverlapCount++;
            }
            countMap.put(overlapNumbers, countMap.get(overlapNumbers) == null ? 1 : countMap.get(overlapNumbers) + 1);
        }
        System.out.println("leastTwoLinesOverlapCount=" + leastTwoLinesOverlapCount);
        Assertions.assertEquals(expect, leastTwoLinesOverlapCount);
    }

    private List<Line> formatInputData(String path) {
        String text = ClasspathResources.text(path);
        List<String> lineStrs = Arrays.stream(text.split("\n")).collect(Collectors.toList());
        List<Line> lines = new ArrayList<>();
        for (String lineStr : lineStrs) {
//            System.out.printf("'%s',\n", lineStr);
            List<String> points = Arrays.stream(lineStr.split(" -> ")).filter(o -> !"".equals(o)).collect(Collectors.toList());
            if (points.size() != 2) {
                throw new RuntimeException("ERROR INPUT");
            }
            List<String> point1 = Arrays.stream(points.get(0).split(",")).filter(o -> !"".equals(o)).collect(Collectors.toList());
            if (point1.size() != 2) {
                throw new RuntimeException("ERROR INPUT");
            }
            List<String> point2 = Arrays.stream(points.get(1).split(",")).filter(o -> !"".equals(o)).collect(Collectors.toList());
            if (point2.size() != 2) {
                throw new RuntimeException("ERROR INPUT");
            }
            Line line = new Line();
            line.x1 = Integer.parseInt(point1.get(0));
            line.y1 = Integer.parseInt(point1.get(1));
            line.x2 = Integer.parseInt(point2.get(0));
            line.y2 = Integer.parseInt(point2.get(1));
            if (Objects.equals(line.x1, line.x2) || Objects.equals(line.y1, line.y2)) {
                lines.add(line);
            }
        }
        return lines;
    }

    private List<OverlapPoint> overlapPoints(List<Line> lines){
        List<OverlapPoint> overlapPoints = new ArrayList<>();
        for (Line line : lines) {
            int x1 = line.x1;
            int y1 = line.y1;
            int x2 = line.x2;
            int y2 = line.y2;
            if (x1 == x2) {
                int start;
                int end;
                if (y1 > y2) {
                    start = y2;
                    end = y1;
                } else {
                    start = y1;
                    end = y2;
                }
                for (int i = start; i <= end; i++) {
                    OverlapPoint point = new OverlapPoint();
                    point.x = x1;
                    point.y = i;
                    overlapPoints.add(point);
                }
            } else if (y1 == y2) {
                int start;
                int end;
                if (x1 > x2) {
                    start = x2;
                    end = x1;
                } else {
                    start = x1;
                    end = x2;
                }
                for (int i = start; i <= end; i++) {
                    OverlapPoint point = new OverlapPoint();
                    point.x = i;
                    point.y = y1;
                    overlapPoints.add(point);
                }
            }

        }
        return overlapPoints;
    }

   public static class Line {
        //        OverlapPoint point1;
//        OverlapPoint point2;
        Integer x1;
        Integer y1;
        Integer x2;
        Integer y2;
    }

    public static class OverlapPoint {
        Integer x;
        Integer y;
        Integer overlapNumber;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OverlapPoint that = (OverlapPoint) o;
            return Objects.equals(x, that.x) && Objects.equals(y, that.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "OverlapPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }
}
