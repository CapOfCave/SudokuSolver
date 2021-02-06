package me.lkecker.sudokusolver.test;

import java.util.Collections;

import me.lkecker.sudokusolver.domain.Game;
import me.lkecker.sudokusolver.domain.Solution;
import me.lkecker.sudokusolver.factories.Boards;
import me.lkecker.sudokusolver.factories.Givens;
import me.lkecker.sudokusolver.factories.SquarePossibilities;
import me.lkecker.sudokusolver.solver.Solver;
import me.lkecker.sudokusolver.visualization.Visualization;

public class Test {

	public static void main(String[] args) {

		long last = System.nanoTime();
//		Game game = new Game(Boards.squareOfSize(4), SquarePossibilities.numbersInRange(4), Collections.emptySet(),
//				Givens.numbers(new Integer[][] { //
//						{ 1, null, null, null }, //
//						{ null, null, null, 4 }, //
//						{ null, null, 2, null }, //
//						{ null, 3, null, null }//
//
//				}));
		
		Game game = new Game(Boards.squareOfSize(4), SquarePossibilities.numbersInRange(4), Collections.emptySet(),
				Givens.numbers(new Integer[][] { //
						{ 1, null, null, null }, //
						{ null, null, null, 4 }, //
						{ null, null, 2, null }, //
						{ null, 3, null, null }//

				}));


		Solver solver = new Solver(game);
		Solution solution = solver.solve();
//		Visualization.printMatrix(solution.getSquares());

		System.out.println("Tested " + Game.tests + "/"+(Math.pow(4, 4*4))+" game states in " + (System.nanoTime() - last) / 1.e6 + " ms");
		
	}
}
