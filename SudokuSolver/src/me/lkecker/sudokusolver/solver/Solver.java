package me.lkecker.sudokusolver.solver;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;
import me.lkecker.sudokusolver.domain.Game;
import me.lkecker.sudokusolver.domain.Solution;
import me.lkecker.sudokusolver.domain.Square;
import me.lkecker.sudokusolver.domain.SquarePossibility;
import me.lkecker.sudokusolver.visualization.Visualization;

@AllArgsConstructor
public class Solver {

	private Game game;

	public Solution solve() {
		this.game.start();
		System.out.println("start");
		// if there is no unresolved square, the given square is valid at this stage
		Visualization.printMatrix(this.game.getBoard().getSquares());
		Optional<Square> optNext = getNextUnresolvedSquare();
		if (optNext.isEmpty()) {
			System.out.println("SOLVED");
			Visualization.printMatrix(this.game.getBoard().getSquares());
		}
		// else: pick a next Square
		Square next = optNext.get();
		// if that square has a value leading to a solved board this square is also
		// valid
		while (next.hasPossibleValues()) {
			next.proposeAnyValue();
			System.out.println("Assumed value for " + next);
			Visualization.printMatrix(this.game.getBoard().getSquares());

			if (isSquarePossibilityValid(next, 1)) {
				next.acceptProposal();
				System.out.println("SOLVED");
				Visualization.printMatrix(this.game.getBoard().getSquares());

				System.out.println("nice, done");
				return null;
			} else {
				System.out.println("LOCKING assumed value for " + next + " as impossible");
				next.discardProposal();
				next.lockImpossibleValues();
			}
		}
		// otherwise, this square is invalid

		return this.game.getSolution();
	}

	private boolean isSquarePossibilityValid(Square square, int indents) {

		// no constraints are violated
		if (game.constraintsViolated(square, indents)) {
			return false;
		}

		// if there is no unresolved square, the given square is valid at this stage
		Optional<Square> optNext = getNextUnresolvedSquare();
		if (optNext.isEmpty()) {
			return true;
		}
		// else: pick a next Square
		Square next = optNext.get();
		// if that square has a value leading to a solved board this square is also
		// valid
		while (next.hasPossibleValues()) {
			next.proposeAnyValue();
			System.out.println(s(indents) + "\nAssumed value for " + next);
			Visualization.printMatrix(this.game.getBoard().getSquares());

			if (isSquarePossibilityValid(next, indents + 1)) {
				next.acceptProposal();
				System.out.println(s(indents) + "accepted proposel for " + next);
				return true;
			}
			System.out.println(s(indents) + "Nevermind, stopped assumed value for " + next);
			next.discardProposal();
			Visualization.printMatrix(this.game.getBoard().getSquares());

		}
		// otherwise, this square is invalid
		System.out.println(s(indents)+"INVALID " + square);
		next.resetImpossibleValues();
		return false;

	}

	public static String s(int indents) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < indents; i++) {
			sb.append("    ");
		}
		return sb.toString();
	}

	private Optional<Square> getNextUnresolvedSquare() {
		return game.getBoard().getSquareSet().stream().filter(Square::isUnresolvedAndNotProposed).findAny();
	}
}
