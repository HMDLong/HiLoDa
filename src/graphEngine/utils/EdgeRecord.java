package graphEngine.utils;

public class EdgeRecord implements Comparable<EdgeRecord>{
    public int v1;
    public int v2;
    public int weight;

    public EdgeRecord(int v1, int v2, int weight){
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(EdgeRecord e){
        return this.weight - e.weight;
    }

    public boolean equals(Object o){
        if(o instanceof EdgeRecord){
            EdgeRecord e = (EdgeRecord) o;
            return (e.v1 == this.v1 && e.v2 == this.v2) || (e.v1 == this.v2 && e.v2 == this.v1);
        }
        return false;
    }

    public String toString(){
        return this.v1 + "---" + this.v2 + "=" + this.weight;
    }
}
