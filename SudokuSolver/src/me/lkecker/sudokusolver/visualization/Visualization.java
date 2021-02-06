package me.lkecker.sudokusolver.visualization;

import me.lkecker.sudokusolver.domain.NumberSquare;
import me.lkecker.sudokusolver.domain.Square;

public class Visualization {

	public static <T> void printMatrix(int[][] matrix, int maxSize) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				System.out.printf("%*d", maxSize, matrix[row][col]);
			}
			System.out.println();
		}
	}

	public static <T> void printMatrix(Square[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				String val;
				Square square = matrix[row][col];
				if (square.getProposedValue() != null) {
					val = "?" + ((NumberSquare) square.getProposedValue()).getValue() + "?";
				} else if (!square.getPossibleValues().isEmpty()) {
					val = "!" + ((NumberSquare) square.getPossibleValues().stream().findAny().get()).getValue() + "!";
				} else {
					val = "---";
				}

//				.getRelevantValue()
//				
//				.map(s -> (NumberSquare) s)
//						.map(n -> Integer.toString(n.getValue())).orElse("-");
				System.out.printf("%3s ", val);
			}
			System.out.println();
		}
	}

}
