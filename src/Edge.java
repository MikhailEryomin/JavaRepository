import java.util.Objects;

public class Edge {
    private final Vertex source, dest;
    public Vertex getSource() {
        return source;
    }
    public Vertex getDest() {
        return dest;
    }
    private int weight;
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }
    public Edge(String source, String dest, int weight) {
        this.source = new Vertex(source);
        this.dest = new Vertex(dest);
        this.weight = weight;
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Edge that && this.source.equals(that.source) && this.dest.equals(that.dest)
                && this.weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, dest, weight);
    }
}
