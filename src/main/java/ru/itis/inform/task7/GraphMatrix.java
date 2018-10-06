package ru.itis.inform.task7;

public class GraphMatrix {
    private static final int DEFAULT_SIZE = 1000;
    private static final int INF = 1000000;

    private int matrix[][];
    private int dmatrix[][];
    private int verticesCount;
    private int maxSize;


    public GraphMatrix() {
        initGraph(DEFAULT_SIZE);
    }

    public GraphMatrix(int maxSize) {
        initGraph(maxSize);
    }

    private void initGraph(int maxSize) {
        this.maxSize = maxSize;
        this.verticesCount = 0;
        this.matrix = new int[maxSize][maxSize];
    }

    public void addEdge(int vertexA, int vertexB, int weightEdge) {
        if (vertexA >= verticesCount) {
            if (vertexA >= maxSize) {
                throw new IllegalArgumentException();
            } else {
                verticesCount = vertexA + 1;
            }
        }

        if (vertexB >= verticesCount) {
            if (vertexB >= maxSize) {
                throw new IllegalArgumentException();
            } else {
                verticesCount = vertexB + 1;
            }
        }

        if (vertexA < verticesCount && vertexB < verticesCount && this.matrix[vertexA][vertexB] == 0) {
            this.matrix[vertexA][vertexB] = weightEdge;
            this.matrix[vertexB][vertexA] = weightEdge;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addDirectedEdge(int vertexFrom, int vertexTo, int weightEdge) {
        if (vertexFrom >= verticesCount) {
            if (vertexFrom >= maxSize) {
                throw new IllegalArgumentException();
            } else {
                verticesCount = vertexFrom + 1;
            }
        }

        if (vertexTo >= verticesCount) {
            if (vertexTo >= maxSize) {
                throw new IllegalArgumentException();
            } else {
                verticesCount = vertexTo + 1;
            }
        }

        if (vertexFrom < verticesCount && vertexTo < verticesCount && this.matrix[vertexFrom][vertexTo] == 0) {
            this.matrix[vertexFrom][vertexTo] = weightEdge;
        }
    }

    private void generateDMatrix() {
        this.dmatrix = new int[verticesCount][verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            for (int j = 0; j < verticesCount; j++) {
                if (matrix[i][j] == 0 && i != j) {
                    dmatrix[i][j] = INF;
                } else {
                    dmatrix[i][j] = matrix[i][j];
                }
            }
        }
    }

    public int[][] runFloyd() {
        generateDMatrix();
        for (int k = 0; k < verticesCount; k++) {
            for (int i = 0; i < verticesCount; i++) {
                for (int j = 0; j < verticesCount; j++) {
                    dmatrix[i][j] = Math.min(dmatrix[i][j], dmatrix[i][k] + dmatrix[k][j]);
                }
            }
        }
        return dmatrix;
    }

    public void showMatrix() {
        System.out.println("Adjacency matrix:");
        for (int i = 0; i < verticesCount; i++) {
            for (int j = 0; j < verticesCount - 1; j++) {
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.println(matrix[i][verticesCount - 1]);
        }
    }

    public void showReachabilityMatrix() {
        System.out.println("Reachability matrix:");
        for (int i = 0; i < verticesCount; i++) {
            for (int j = 0; j < verticesCount - 1; j++) {
                System.out.print((dmatrix[i][j] == INF ? "INF" : dmatrix[i][j]) + ",  ");
            }
            System.out.println(dmatrix[i][verticesCount - 1]);
        }
    }
}

