import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrettyPrint {
    public static void prettyPrintln(List<int[]> data) {
        System.out.println(
            data.stream()
            .map(Arrays::toString)
            .collect(Collectors.toList())
        );
    }

    public static void prettyPrintln(int[] data) {
        System.out.println(Arrays.toString(data));
    }

    public static void prettyPrintln(int[][] data) {
        System.out.println(format2D(data));
    }

    public static void prettyPrintln(char[][] data) {
        System.out.println(format2D(data));
    }

    public static void prettyPrintln(boolean[][] data) {
        System.out.println(format2D(data));
    }

    // Shared pretty formatting logic
    private static String format2D(Object array2D) {
        Object[] rows = (Object[]) array2D;
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < rows.length; i++) {
            Object row = rows[i];
            sb.append("  ");
            if (row instanceof int[])
                sb.append(Arrays.toString((int[]) row));
            else if (row instanceof char[])
                sb.append(Arrays.toString((char[]) row));
            else if (row instanceof boolean[])
                sb.append(Arrays.toString((boolean[]) row));
            else if (row instanceof long[])
                sb.append(Arrays.toString((long[]) row));
            else if (row instanceof double[])
                sb.append(Arrays.toString((double[]) row));
            else if (row instanceof Object[])
                sb.append(Arrays.deepToString((Object[]) row));
            else
                sb.append(String.valueOf(row));

            if (i < rows.length - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }
}
