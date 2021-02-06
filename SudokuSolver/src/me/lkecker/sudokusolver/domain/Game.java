package me.lkecker.sudokusolver.domain;

import java.util.Optional;
import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lkecker.sudokusolver.solver.Solver;

@RequiredArgsConstructor
@Getter
public class Game {

	public static int tests = 0;
	private final Board board;
	private final Set<SquarePossibility> possibilitySet;
	private final Set<Constraint> constraints;
	private final Set<GivenSquare> givens;

	private SolveState solveState;

	public void start() {
		this.solveState = SolveState.PENDING;
		this.board.populate(possibilitySet, givens);

	}

	public Solution getSolution() {
		return board.getSolution();
	}

	public boolean hasFinished() {
		return this.solveState == SolveState.SOLVED || this.solveState == SolveState.INFEASABLE;
	}

	public Optional<Square> getUnsolvedSquare() {
		return board.getUnsolvedSquare();
	}

	public boolean constraintsViolated(Square square, int indents) {
		tests++;
		// RowConstraint
		for (int x = 0; x < board.getWidth(); x++) {
			if (x == square.getX()) {
				continue;
			}
			Optional<SquarePossibility> otherValue = board.getSquares()[x][square.getY()].getRelevantValue();
			if (otherValue.isEmpty()) {
				continue;
			}
			if (square.getRelevantValue().orElseThrow().equals(otherValue.get())) {
				System.out.println(Solver.s(indents) + "RowConstraint violated for " + square + ", REASON: "
						+ board.getSquares()[x][square.getY()]);
				return true;
			}
		}

		// ColumnConstraint
		for (int y = 0; y < board.getHeight(); y++) {
			if (y == square.getY()) {
				continue;
			}
			Optional<SquarePossibility> otherValue = board.getSquares()[square.getX()][y].getRelevantValue();
			if (otherValue.isEmpty()) {
				continue;
			}
			if (square.getRelevantValue().orElseThrow().equals(otherValue.get())) {
				System.out.println(Solver.s(indents) + "ColConstraint violated for " + square + ", REASON: "
						+ board.getSquares()[square.getX()][y]);
				return true;
			}
		}

		// BoxConstraint
		int xbox = square.getX() / 2;
		int ybox = square.getY() / 2;
		for (int i = 0; i < 2; i++) {
			int compX = xbox * 2 + i;
			for (int j = 0; j < 2; j++) {
				int compY = ybox * 2 + j;
				if (compX == square.getX() && compY == square.getY()) {
					continue;
				}
				Optional<SquarePossibility> otherValue = board.getSquares()[compX][compY].getRelevantValue();
				if (otherValue.isEmpty()) {
					continue;
				}
				if (square.getRelevantValue().orElseThrow().equals(otherValue.get())) {
					System.out.println(Solver.s(indents) + "BoxConstraint violated for " + square + ", REASON: "
							+ board.getSquares()[compX][compY]);
					return true;
				}

			}
		}

		return false;
	}
}
