package me.lkecker.sudokusolver.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import me.lkecker.sudokusolver.domain.GivenSquare;
import me.lkecker.sudokusolver.domain.NumberSquare;
import me.lkecker.sudokusolver.factories.Givens;

public class GivensTest {

	@Test
	public void numbers_happyDay_createsCorrectSet() {

		Set<GivenSquare> numbers = Givens.numbers(new Integer[][] { //
			{ 1, null, null, null }, //
			{ null, null, null, 4 }, //
			{ null, null, 2, null }, //
			{ null, 3, null, null }//
		});
		assertEquals(4, numbers.size());
		Set<GivenSquare> expected = Set.of(
				new GivenSquare(0, 0, NumberSquare.of(1)),
				new GivenSquare(3, 1, NumberSquare.of(4)),
				new GivenSquare(2, 2, NumberSquare.of(2)),
				new GivenSquare(1, 3, NumberSquare.of(3))
				);
		numbers.forEach(expected::contains);
		expected.forEach(numbers::contains);
		
	}
}
