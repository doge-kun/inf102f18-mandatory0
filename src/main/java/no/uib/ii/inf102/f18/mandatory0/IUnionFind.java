package no.uib.ii.inf102.f18.mandatory0;

public interface IUnionFind {

    public boolean connected(int p, int q);

    public int find(int p);

    public void union(int p, int q);
}
