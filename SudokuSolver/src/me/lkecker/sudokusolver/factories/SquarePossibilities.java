package me.lkecker.sudokusolver.factories;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import me.lkecker.sudokusolver.domain.NumberSquare;
import me.lkecker.sudokusolver.domain.SquarePossibility;

public class SquarePossibilities {

	public static Set<SquarePossibility> numbersInRange(int range) {
		return IntStream.range(1, range + 1).mapToObj(NumberSquare::of).collect(Collectors.toSet());
	}
	
	public static Set<SquarePossibility> numbersOneToNine() {
		return numbersInRange(9);
	}
}
