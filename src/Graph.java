import java.util.*;

public class Graph {
    private final Map<String, Vertex> vertices = new HashMap<>();
    private final List<Edge> edges = new ArrayList<>();
    public Graph(List<Edge> edges) {
        edges.forEach(edge -> addEdge(edge.getSource().getName(), edge.getDest().getName(), edge.getWeight()));
    }
    public void setVertexName(String name, String newName) {
        if (!vertices.containsKey(name)) return;
        Vertex vertex = vertices.get(name);
        for (Edge edge : edges) {
            Vertex src = edge.getSource();
            Vertex dest = edge.getDest();
            if (src.equals(vertex)) src.setName(newName);
            else if (dest.equals(vertex)) dest.setName(newName);
        }
        vertex.setName(newName);
        vertices.remove(name);
        vertices.put(newName, vertex);
    }

    public void setEdgeWeight(String from, String to, int currWeight, int newWeight) {
        Edge edge = new Edge(from, to, currWeight);
        for (Edge current : edges) if (current.equals(edge)) current.setWeight(newWeight);
    }

    public void addVertex(String name) {
        Vertex newVertex = new Vertex(name);
        vertices.putIfAbsent(name, newVertex);
    }

    public void addEdge(String fromName, String toName, int weight) {
        addVertex(fromName);
        addVertex(toName);
        Edge newEdge = new Edge(fromName, toName, weight);
        //Если указать нулевую дугу, то это просто добавление вершины
        if (!toName.equals(fromName)) {
            vertices.get(fromName).addNeighbour(vertices.get(toName));
            edges.add(newEdge);
        }
    }

    public List<Edge> getVertexEdges(String name, boolean fromVertex) {
        Vertex vertex = vertices.get(name);
        List<Edge> result = new ArrayList<>();
        for (Edge edge : edges) {
            if (fromVertex) {
                if (edge.getSource().equals(vertex)) result.add(edge);
            } else {
                if (edge.getDest().equals(vertex)) result.add(edge);
            }
        }
        return result;
    }

    public void removeVertex(String name) {
        Vertex toRemove = new Vertex(name);
        vertices.remove(name);
        vertices.values().forEach(it -> it.removeNeighbour(toRemove));
        edges.removeIf(edge -> edge.getSource().equals(toRemove) || edge.getDest().equals(toRemove));
    }

    public void removeEdge(Edge edge) {
        if (!edges.contains(edge)) return;
        String fromName = edge.getSource().getName();
        String toName = edge.getDest().getName();
        Vertex from = vertices.get(fromName);
        from.removeNeighbour(vertices.get(toName));
        edges.remove(edge);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Vertex> entry : vertices.entrySet()) {
            String toAppend = entry.getKey() + " -> " + entry.getValue().getNeighbours() + "\n";
            result.append(toAppend);
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Graph that && vertices.equals(that.vertices) && edges.equals(that.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices, edges);
    }
}


