import static org.junit.Assert.*;
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
}
