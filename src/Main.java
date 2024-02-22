import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/10m.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line.trim());
                numbers.add(number);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file.");
        }
        System.out.println(numbers);
        long startTime = System.nanoTime();
        System.out.println("Max number is: " + getMax(numbers));
        System.out.println("Min number is: " + getMin(numbers));
        System.out.println("Median is: " + getMedian(numbers));
        System.out.println("Average value is: " + getAverage(numbers));
        System.out.println("Longest increasing sequence is: " + getLongestIncreasingSequence(numbers));
        System.out.println("Longest decreasing sequence is: " + getLongestDecreasingSequence(numbers));

        long endTime = System.nanoTime();
        System.out.println("Total time spent on counting methods: " + (endTime - startTime) / 1e9 + " seconds.");
    }

    private static Integer getMax(List<Integer> numbers) {
        return numbers.stream()
                .max(Integer::compareTo)
                .orElseThrow(() -> new RuntimeException("Can't find max integer."));
    }

    private static Integer getMin(List<Integer> numbers) {
        return numbers.stream()
                .min(Integer::compareTo)
                .orElseThrow(() -> new RuntimeException("Can't find min integer."));
    }

    private static double getMedian(List<Integer> numbers) {
        List<Integer> copyNumbers = new ArrayList<>(numbers);
        Collections.sort(copyNumbers);
        int listSize = copyNumbers.size();
        if (listSize % 2 == 1) {
            return copyNumbers.get(listSize / 2);
        } else {
            int middleFirst = copyNumbers.get(listSize / 2 - 1);
            int middleSecond = copyNumbers.get(listSize / 2);
            return (double) (middleFirst + middleSecond) / 2;
        }
    }

    private static double getAverage(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() ->
                        new RuntimeException("Can't find average value."));
    }

    private static List<Integer> getLongestIncreasingSequence(List<Integer> numbers) {
        List<Integer> tempSequence = new ArrayList<>();
        List<Integer> resultSequence = new ArrayList<>();
        for (Integer number : numbers) {
            if (tempSequence.isEmpty()
                    || number > tempSequence.getLast()) {
                tempSequence.add(number);
            } else {
                if (tempSequence.size() > resultSequence.size()) {
                    resultSequence = new ArrayList<>(tempSequence);
                }
                tempSequence.clear();
                tempSequence.add(number);
            }
        }
        if (tempSequence.size() > resultSequence.size()) {
            resultSequence = new ArrayList<>(tempSequence);
        }
        return resultSequence;
    }

    private static List<Integer> getLongestDecreasingSequence(List<Integer> numbers) {
        List<Integer> tempSequence = new ArrayList<>();
        List<Integer> resultSequence = new ArrayList<>();
        for (Integer number : numbers) {
            if (tempSequence.isEmpty() || number < tempSequence.getLast()) {
                tempSequence.add(number);
            } else {
                if (tempSequence.size() > resultSequence.size()) {
                    resultSequence = new ArrayList<>(tempSequence);
                }
                tempSequence.clear();
                tempSequence.add(number); //
            }
        }
        if (tempSequence.size() > resultSequence.size()) {
            resultSequence = new ArrayList<>(tempSequence);
        }
        return resultSequence;
    }
}