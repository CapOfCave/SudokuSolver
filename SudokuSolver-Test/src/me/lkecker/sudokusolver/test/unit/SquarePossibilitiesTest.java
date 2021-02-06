package me.lkecker.sudokusolver.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import me.lkecker.sudokusolver.domain.NumberSquare;
import me.lkecker.sudokusolver.domain.SquarePossibility;
import me.lkecker.sudokusolver.factories.SquarePossibilities;

public class SquarePossibilitiesTest {

	@Test
	public void numbersOneToNine_happyDay_createsCorrectSet() {

		Set<SquarePossibility> numbersOneToNine = SquarePossibilities.numbersOneToNine();
		assertEquals(9, numbersOneToNine.size());
		for (int i = 1; i <= 9; i++) {
			final int ii = i;
			assertTrue(numbersOneToNine.stream().map(p -> (NumberSquare) p).mapToInt(NumberSquare::getValue)
					.anyMatch(n -> n == ii));
		}
	}
}
