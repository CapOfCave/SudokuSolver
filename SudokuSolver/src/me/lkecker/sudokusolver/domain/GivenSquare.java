package me.lkecker.sudokusolver.domain;

import lombok.Data;

@Data
public class GivenSquare {

	private final int x, y;
	private final SquarePossibility value;
}
