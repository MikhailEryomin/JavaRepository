import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphTests {
    @Test
    public void addEdge() {
        Graph current = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3))));
        Graph expected = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3),
                new Edge("D", "C", 5))));
        current.addEdge("D", "C", 5);
        assertEquals(expected, current);
        current.addEdge("F", "F", 0);
        expected = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3),
                new Edge("D", "C", 5),
                new Edge("F", "F", 0))));
        assertEquals(expected, current);
    }

    @Test
    public void removeEdge() {
        Graph current = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3))));
        Graph expected = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3))));
        current.removeEdge(new Edge("D", "E", 4));
        assertEquals(expected, current);
        current.removeEdge(new Edge("A", "C", 2));
        expected = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("B", "A", 3),
                new Edge("C", "C", 0))));
        assertEquals(expected, current);
    }

    @Test
    public void addVertex() {
        Graph current = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3))));
        Graph expected = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3))));
        current.addVertex("A");
        assertEquals(expected, current);
        current.addVertex("D");
        expected = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3),
                new Edge("D", "D", 0))));
        assertEquals(expected, current);
    }

    @Test
    public void removeVertex() {
        Graph current = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3))));
        current.removeVertex("A");
        Graph expected = new Graph(new ArrayList<>(List.of(
                new Edge("B", "B", 0),
                new Edge("C", "C", 0))));
        assertEquals(expected, current);
        current.removeVertex("Z");
        assertEquals(expected, current);
        current.removeVertex("C");
        expected = new Graph(new ArrayList<>(List.of(
                new Edge("B", "B", 0))));
        assertEquals(expected, current);
    }

    @Test
    public void setVertexName() {
        Graph current = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3))));
        current.setVertexName("A", "Z");
        Graph expected = new Graph(new ArrayList<>(List.of(
                new Edge("Z", "B", 1),
                new Edge("Z", "C", 2),
                new Edge("B", "Z", 3))));
        assertEquals(expected, current);
        current.setVertexName("D", "X");
        assertEquals(expected, current);
    }

    @Test
    public void setEdgeWeight() {
        Graph current = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3))));
        current.setEdgeWeight("A", "C", 2, 120);
        Graph expected = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 120),
                new Edge("B", "A", 3))));
        assertEquals(expected, current);
        current.setEdgeWeight("D", "E", 3, 15);
        assertEquals(expected, current);
    }

    @Test
    public void getVertexEdges() {
        Graph current = new Graph(new ArrayList<>(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("B", "A", 3),
                new Edge("D", "B", 4))));
        assertEquals(new ArrayList<>(List.of(new Edge("A", "B", 1), new Edge("A", "C", 2))),
                current.getVertexEdges("A", true));
        assertEquals(new ArrayList<>(),
                current.getVertexEdges("E", true));
        assertEquals(new ArrayList<>(List.of(new Edge("A", "B", 1), new Edge("D", "B", 4))),
                current.getVertexEdges("B", false));
    }
}
