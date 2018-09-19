package no.uib.ii.inf102.f18.mandatory0;

import java.util.Scanner;

public class QuickFind implements IUnionFind {
    private int[] id;

    public QuickFind(int n) {
        id = new int[n];
        for(int i=0; i<n; i++){
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if(pID == qID){
            return;
        }

        for(int i=0; i<id.length; i++) {
            if(id[i] == pID || id[i] == qID) {
                if(pID < qID) {
                    id[i] = pID;
                } else {
                    id[i] = qID;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        QuickFind quickFind = new QuickFind(n);
        int dataSize = reader.nextInt();

        for(int i=0; i<dataSize; i++) {
            int p = reader.nextInt();
            int q = reader.nextInt();

            if(quickFind.connected(p, q)) {
                continue;
            }
            quickFind.union(p, q);
        }
        String solution = "";

        for(int i=0; i<n; i++) {
            solution += quickFind.id[i] + " ";
        }

        solution = solution.substring(0, solution.length()-1);
        System.out.println(solution);
    }
}