package no.uib.ii.inf102.f18.mandatory0;

import java.io.*;

public class UnionFind implements IUnionFind {
    private int[] id;
    private int[] size;

    public UnionFind(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        if (id[p] == p) {
            return p;
        }
        id[p] = find(id[p]);
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID > qID) {
            id[pID] = qID;
            size[qID] += size[pID];
        } else {
            id[qID] = pID;
            size[pID] += size[qID];
        }
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            String line = in.readLine();
            String[] tokens = line.split(" ");
            int nAccs = Integer.parseInt(tokens[0]);
            int queries = Integer.parseInt(tokens[1]);

            UnionFind unionFind = new UnionFind(nAccs);
            int a, b, l;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i<queries; i++) {
                line = in.readLine();
                tokens = line.split(" ");
                if (tokens[0].equals("DAEMON")) {
                    a = Integer.parseInt(tokens[1]);
                    b = Integer.parseInt(tokens[2]);

                    unionFind.union(a, b);
                } else {
                    l = Integer.parseInt(tokens[1]);
                    sb.append(unionFind.find(l));
                    sb.append("\n");
                }
            }
            out.write(sb.toString());
            in.close();
            out.close();
        } catch (IOException e) {
            System.exit(1);
        }
    }
}