package me.lkecker.sudokusolver.domain;

import java.util.HashMap;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberSquare implements SquarePossibility {

	private static HashMap<Integer, NumberSquare> cache = new HashMap<>();
	private int value;

	public static SquarePossibility of(int value) {
		if (cache.containsKey(Integer.valueOf(value))) {
			return cache.get(Integer.valueOf(value));
		}
		NumberSquare num = new NumberSquare(value);
		cache.put(value, num);
		return num;
	}
}
