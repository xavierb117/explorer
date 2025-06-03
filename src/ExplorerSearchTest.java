import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // Come up with varied cases

    @Test
    public void testGetExplorer_smallIsland() {
        int[][] island = {
            {1, 1, 0},
            {2, 2, 3}
        };
        int[] location = ExplorerSearch.getExplorer(island);
        assertEquals(0, location[0]);
        assertEquals(2, location[1]);
    }

    @Test
    public void testGetExplorer_smallIslandExplorerAtStart() {
        int[][] island = {
            {0, 1, 1},
            {3, 2, 3}
        };
        int[] location = ExplorerSearch.getExplorer(island);
        assertEquals(0, location[0]);
        assertEquals(0, location[1]);
    }

    @Test
    public void testGetExplorer_largeIsland() {
        int[][] island = {
            {1, 1, 2, 3, 1},
            {2, 2, 3, 1, 1},
            {3, 2, 1, 1, 1},
            {2, 3, 2, 1, 1},
            {2, 3, 1, 0, 1}
        };
        int[] location = ExplorerSearch.getExplorer(island);
        assertEquals(4, location[0]);
        assertEquals(3, location[1]);
    }

    @Test
    public void testGetExplorer_largeIslandExplorerAtEnd() {
        int[][] island = {
            {2, 1, 2, 3, 1},
            {2, 2, 3, 3, 1},
            {3, 2, 2, 1, 2},
            {2, 3, 2, 1, 1},
            {2, 3, 1, 3, 0}
        };
        int[] location = ExplorerSearch.getExplorer(island);
        assertEquals(4, location[0]);
        assertEquals(4, location[1]);
    }

    @Test
    public void testGetExplorer_noExplorer() {
        int[][] island = {
            {1, 1, 3},
            {2, 2, 3},
            {3, 1, 2}
        };
        assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.getExplorer(island);
        });
    }

    @Test
    public void testPossibleMoves_noBlocks() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[] start = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, start);
        Set<String> convertedList = toSet(moves);

        assertEquals(4, convertedList.size());
        assertTrue(convertedList.contains("0, 1"));
        assertTrue(convertedList.contains("2, 1"));
        assertTrue(convertedList.contains("1, 0"));
        assertTrue(convertedList.contains("1, 2"));
    }

    @Test
    public void testPossibleMoves_upBlock() {
        int[][] island = {
            {1, 2, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[] start = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, start);
        Set<String> convertedList = toSet(moves);

        assertEquals(3, convertedList.size());
        assertFalse(convertedList.contains("0, 1"));
        assertTrue(convertedList.contains("2, 1"));
        assertTrue(convertedList.contains("1, 0"));
        assertTrue(convertedList.contains("1, 2"));
    }

    @Test
    public void testPossibleMoves_upAndDownBlocks() {
        int[][] island = {
            {1, 2, 1, 2, 3},
            {1, 1, 2, 1, 1},
            {1, 1, 0, 1, 1},
            {1, 1, 3, 1, 1},
            {1, 1, 1, 1, 1}
        };
        int[] start = {2, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, start);
        Set<String> convertedList = toSet(moves);

        assertEquals(2, convertedList.size());
        assertFalse(convertedList.contains("1, 2"));
        assertFalse(convertedList.contains("3, 2"));
        assertTrue(convertedList.contains("2, 1"));
        assertTrue(convertedList.contains("2, 3"));
    }

    @Test
    public void testPossibleMoves_upAndDownAndLeftBlocks() {
        int[][] island = {
            {1, 2, 1, 2, 3},
            {1, 1, 2, 1, 1},
            {1, 2, 0, 1, 1},
            {1, 1, 3, 1, 1},
            {1, 1, 1, 1, 1}
        };
        int[] start = {2, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, start);
        Set<String> convertedList = toSet(moves);

        assertEquals(1, convertedList.size());
        assertFalse(convertedList.contains("1, 2"));
        assertFalse(convertedList.contains("3, 2"));
        assertFalse(convertedList.contains("2, 1"));
        assertTrue(convertedList.contains("2, 3"));
    }

    @Test
    public void testPossibleMoves_AllBlocked() {
        int[][] island = {
            {1, 2, 2, 2, 3},
            {1, 2, 0, 3, 1},
            {1, 2, 3, 1, 1}
        };
        int[] start = {1, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, start);
        Set<String> convertedList = toSet(moves);

        assertEquals(0, convertedList.size());
        assertFalse(convertedList.contains("0, 2"));
        assertFalse(convertedList.contains("2, 2"));
        assertFalse(convertedList.contains("1, 1"));
        assertFalse(convertedList.contains("1, 3"));
    }

    // Convert List to Set for better testing on what the list contains
    private Set<String> toSet(List<int[]> list) {
        Set<String> converted = new HashSet<>();
        for (int[] arr : list) {
            converted.add(arr[0] + ", " + arr[1]);
        }
        return converted;
    }
}
