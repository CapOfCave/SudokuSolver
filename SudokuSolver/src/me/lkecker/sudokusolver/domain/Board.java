package me.lkecker.sudokusolver.domain;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Board implements ConstraintOrigin {

	private final int width;
	private final int height;

	private Square[][] squares;

	public void populate(Set<SquarePossibility> squarePossibilities) {
		this.squares = new Square[width][height];
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				this.squares[i][j] = new Square(i, j, squarePossibilities);
			}
		}
	}
	
	public void populate(Set<SquarePossibility> squarePossibilities, Set<GivenSquare> givens) {
		this.squares = new Square[width][height];
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				this.squares[i][j] = new Square(i, j, squarePossibilities);
			}
		}
		givens.forEach(given -> this.squares[given.getX()][given.getY()].setValue(given.getValue()));

	}

	public Solution getSolution() {
		return new Solution(squares);
	}

	public Optional<Square> getUnsolvedSquare() {
		return Stream.of(squares).flatMap(Stream::of).filter(Square::isUnresolvedAndNotProposed).findAny();
	}

	public Set<Square> getSquareSet() {
		return Stream.of(squares).flatMap(Stream::of).collect(Collectors.toSet());
	}
}
