package data_structure.graph;

import data_structure.stack.StackX;

/**
 * Author : Ray
 * Created At : 2018-06-14 下午10:16
 * Email : ryu18356@gmail.com
 * Description : 简单图，邻接矩阵实现，无向无权
 */
public class Graph {

    private final int MAX_VERTEX = 20;

    private Vertex mVertexList[];

    private int[][] mAdjMatrix;

    private int nVerts;

    StackX mStackX;

    public Graph() {
        mVertexList = new Vertex[MAX_VERTEX];
        mStackX = new StackX(MAX_VERTEX);
        mAdjMatrix = new int[MAX_VERTEX][MAX_VERTEX];
        for (int i = 0; i < mAdjMatrix.length; i++) {
            for (int j = 0; j < mAdjMatrix[i].length; j++) {
                mAdjMatrix[i][j] = 0;
            }
        }
    }

    public void addVertex(char label) {
        mVertexList[nVerts++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        mAdjMatrix[start][end] = 1;
        mAdjMatrix[end][start] = 1;
    }

    public void displayVertex(int index) {
        System.out.print(mVertexList[index].label);
    }

    private int getAdjUnvisitedVertex(int index) {
        for (int i = 0; i < nVerts; i++) {
            if (mAdjMatrix[index][i] == 1 && !mVertexList[i].wasVisited)
                return i;
        }
        return -1;
    }

    /**
     * depth-first-search
     */
    public void dfs() {
        mVertexList[0].wasVisited = true;
        displayVertex(0);
        mStackX.push(0);
        while (!mStackX.isEmpty()) {
            int v = getAdjUnvisitedVertex((int) mStackX.peek());
            if (v == -1) {
                mStackX.pop();
            } else {
                mVertexList[v].wasVisited = true;
                displayVertex(v);
                mStackX.push(v);
            }
        }
        for (int i = 0; i < nVerts; i++) {
            mVertexList[i].wasVisited = false;
        }
    }

}
