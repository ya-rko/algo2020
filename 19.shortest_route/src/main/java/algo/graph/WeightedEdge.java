package algo.graph;


import java.util.Objects;


public class WeightedEdge extends Edge {
    private double weight;


    public WeightedEdge(int vertex1, int vertex2, int weight) {
        super(vertex1, vertex2);
        this.weight = weight;
    }


    public double getWeight() {
        return weight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WeightedEdge that = (WeightedEdge) o;
        return weight == that.weight;
    }


    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
