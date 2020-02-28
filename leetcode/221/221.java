class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }
        int ans = 0;
        final int WIDTH = matrix[0].length;

        int [] edgeLength = new int[WIDTH];
        int preEdge = 0, currentEdge;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1') {
                    if(i == 0) {
                        edgeLength[j] = 1;
                    }
                    else if(j == 0) {
                        preEdge = edgeLength[j];
                        edgeLength[j] = 1;
                    }
                    else {
                        if(edgeLength[j - 1] >= preEdge && edgeLength[j] >= preEdge) {
                            currentEdge = preEdge + 1;
                            preEdge = edgeLength[j];
                            edgeLength[j] = currentEdge;
                        }
                        else {
                            currentEdge = Math.min(edgeLength[j - 1], edgeLength[j]) + 1;
                            preEdge = edgeLength[j];
                            edgeLength[j] = currentEdge;
                        }
                    }
                    ans = Math.max(ans, edgeLength[j] * edgeLength[j]);
                }
                else {
                    preEdge = edgeLength[j];
                    edgeLength[j] = 0;
                }
            }
        }
        return ans;
    }
}