package me.lkecker.sudokusolver.factories;

import me.lkecker.sudokusolver.domain.Board;

public class Boards {

	public static Board squareOfSize(int size) {
		return new Board(size, size);
	}
	
	public static Board defaultBoard() {
		return squareOfSize(3);
	}
	
}
