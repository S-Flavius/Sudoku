    public static boolean check(int[][] sudoku) {

        // Check if every row is possible and there are no 0s
        for (int[] row : sudoku) {
            for (int number : row) {
                if (number == 0) {
                    return false;
                }
            }
            if (!oneOfEach(row)) {
                return false;
            }
        }

        // Checks if every number comes just once in a column
        for (int i = 0; i < sudoku.length; i++) {
            int counter = 0;
            int[] col = new int[9];
            for (int j = 0; j < sudoku[i].length; j++) {
                col[counter] = sudoku[j][i];
                counter++;
            }
            if (!oneOfEach(col)) {
                return false;
            }
        }

        // Checks if every square is legal
        // Outer loop gets the starting position of every square
        // Inner gets it and then checks if it's real
        for (int i = 0; i <= 6; i += 3) {
            for (int j = 0; j <= 6; j += 3) {
                int counter1 = 0, counter2 = 0;
                int[][] square = new int[3][3];
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        square[counter1][counter2] = sudoku[k + i][l + j];
                        counter2++;
                        if (counter2 == 3) {
                            counter2 = 0;
                            counter1++;
                        }
                    }
                }
                if (!checkSquare(square)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean oneOfEach(int[] line) {
        // Goes through each number from 1-9 and tries to find it
        // If something comes twice, it won't be able to find the next number so it returns false.
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < line.length; j++) {
                if (i == line[j]) {
                    break;
                }
                if (j == line.length - 1 && i != line[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkSquare(int[][] square) {
        // Same idea as in oneOfEach, but for a square that's 3x3
        for (int i = 1; i < 9; i++) {
            boolean found = false; // Extra boolean because a break in 2 fors is hard xD
            // Technically slower, but way easier to implement
            for (int[] numbers : square) {
                for (int number : numbers) {
                    if (i == number) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
