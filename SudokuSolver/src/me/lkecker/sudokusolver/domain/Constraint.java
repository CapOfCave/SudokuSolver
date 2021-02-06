package me.lkecker.sudokusolver.domain;

public interface Constraint {

	public boolean isApplicable(ConstraintOrigin constraintOrigin);
	public boolean isValid(ConstraintOrigin constraintOrigin);
}
