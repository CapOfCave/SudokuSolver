package me.lkecker.sudokusolver.domain.constraints;

import me.lkecker.sudokusolver.domain.Constraint;
import me.lkecker.sudokusolver.domain.ConstraintOrigin;
import me.lkecker.sudokusolver.domain.Row;

public class RowConstraint implements Constraint {

	@Override
	public boolean isValid(ConstraintOrigin constraintOrigin) {
		return false;
	}

	@Override
	public boolean isApplicable(ConstraintOrigin constraintOrigin) {
		return constraintOrigin instanceof Row; //TODO later
	}
	
	
	
}
