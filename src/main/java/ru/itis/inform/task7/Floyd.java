package ru.itis.inform.task7;

public class Floyd {
    public static void main(String[] args) {
        GraphMatrix graph = new GraphMatrix();

        graph.addDirectedEdge(0, 1, 4);
        graph.addDirectedEdge(2, 4, 5);
        graph.addDirectedEdge(1, 2, 1);
        graph.addDirectedEdge(3, 1, 7);
        graph.addDirectedEdge(4, 3, 2);

        graph.showMatrix();

        int[][] matrix = graph.runFloyd();
        graph.showReachabilityMatrix();

        int rowSum = 0;
        int columnSum = 0;
        int nR = 0, nC = 0, nRE = 0, nCE = 0;
        int minR = Integer.MAX_VALUE, minC = Integer.MAX_VALUE, minRE = Integer.MAX_VALUE, minCE = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            int maxInRow = 0;
            for (int j = 0; j < matrix.length; j++) {
                rowSum += matrix[i][j];
                if (maxInRow < matrix[i][j]) {
                    maxInRow = matrix[i][j];
                }
            }
            if (minR > rowSum) {
                nR = i;
                minR = rowSum;
            }
            if (minRE > maxInRow) {
                nRE = i;
                minRE = maxInRow;
            }
            rowSum = 0;
        }

        for (int i = 0; i < matrix.length; i++) {
            int maxInColumn = 0;
            for (int j = 0; j < matrix.length; j++) {
                columnSum += matrix[j][i];
                if (maxInColumn < matrix[j][i]) {
                    maxInColumn = matrix[j][i];
                }
            }
            if (minC > columnSum) {
                nC = i;
                minC = columnSum;
            }
            if (minCE > maxInColumn) {
                nCE = i;
                minCE = maxInColumn;
            }
            columnSum = 0;
        }

        System.out.println();
        System.out.println("min row: " + nR);
        System.out.println("min column:" + nC);
        System.out.println("row with min max element: " + nRE);
        System.out.println("column with min max element: " + nCE);
    }
}
