import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex {
    private final List<Vertex> neighbours = new ArrayList<>();
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public Vertex(String name) {
        this.name = name;
    }
    public List<String> getNeighbours() {
        List<String> neighboursNames = new ArrayList<>();
        neighbours.forEach(vertex -> neighboursNames.add(vertex.getName()));
        return neighboursNames;
    }
    public void addNeighbour(Vertex other) {
        neighbours.add(other);
    }
    public void removeNeighbour(Vertex other) {
        neighbours.remove(other);
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Vertex that && this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
