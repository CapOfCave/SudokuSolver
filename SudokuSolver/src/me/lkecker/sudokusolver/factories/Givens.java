package me.lkecker.sudokusolver.factories;

import java.util.HashSet;
import java.util.Set;

import me.lkecker.sudokusolver.domain.GivenSquare;
import me.lkecker.sudokusolver.domain.NumberSquare;

public class Givens {

	public static Set<GivenSquare> numbers(Integer[][] integers) {
		Set<GivenSquare> out = new HashSet<>();
		for (int i = 0; i < integers.length; i++) {
			for (int j = 0; j < integers[i].length; j++) {
				if (integers[i][j] != null) {
					out.add(new GivenSquare(i, j, NumberSquare.of(integers[i][j].intValue())));
				}
			}
		}
		return out;
	}

}
