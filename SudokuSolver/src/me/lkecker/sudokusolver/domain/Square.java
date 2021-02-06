package me.lkecker.sudokusolver.domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Square {

	@Getter
	private final int x;
	@Getter
	private final int y;
	@ToString.Exclude private Set<SquarePossibility> possibleValues;

	@ToString.Exclude private Set<SquarePossibility> impossibleValues;
	@ToString.Exclude private Set<SquarePossibility> uncheckedValues;

	@ToString.Exclude private SquarePossibility proposedValue;
	@ToString.Exclude private Set<SquarePossibility> proposedImpossibleValues;

	public Square(int x, int y, Set<SquarePossibility> squarePossibilities) {
		this.x = x;
		this.y = y;
		this.uncheckedValues = new HashSet<>(squarePossibilities);
		this.possibleValues = new HashSet<>();
		this.impossibleValues = new HashSet<>();
		this.proposedImpossibleValues = new HashSet<>();
	}

	public Optional<SquarePossibility> getAnyUncheckedValue() {
		return uncheckedValues.stream().findAny();
	}

	public boolean isUnresolvedAndNotProposed() {
		return proposedValue == null && !uncheckedValues.isEmpty();
	}

	public boolean hasPossibleValues() {
		return possibleValues.size() + uncheckedValues.size() > 0;
	}

	public void proposeAnyValue() {
		if (!uncheckedValues.isEmpty()) {
			SquarePossibility value = getAnyUncheckedValue().orElseThrow();
			proposedValue = value;
			uncheckedValues.remove(value);
		} else if (!possibleValues.isEmpty()) {
			if (possibleValues.size() > 1) {
				System.err.println("PossibleValues might be ignored for this " + this);
			}
			proposedValue = possibleValues.stream().findAny().get();
		}
	}

	public void discardProposal() {
		this.proposedImpossibleValues.add(this.proposedValue);
		this.proposedValue = null;
	}

	public void acceptProposal() {
		this.possibleValues.add(this.proposedValue);
		this.proposedValue = null;
	}

	public void resetImpossibleValues() {
		this.uncheckedValues.addAll(this.proposedImpossibleValues);
		this.proposedImpossibleValues.clear();
	}

	public void lockImpossibleValues() {
		this.impossibleValues.addAll(this.proposedImpossibleValues);
		this.proposedImpossibleValues.clear();
	}

	public void setValue(SquarePossibility value) {
		if (!this.possibleValues.isEmpty()) {
			throw new IllegalStateException("possible Values not empty");
		}
		this.possibleValues.add(value);
		if (this.possibleValues.size() != 1) {
			throw new IllegalStateException("Possible Value Size must be 1");
		}
		this.uncheckedValues.remove(value);
		this.impossibleValues.addAll(this.uncheckedValues);
		this.uncheckedValues.clear();
	}

	@ToString.Include
	public Optional<SquarePossibility> getRelevantValue() {
		if (possibleValues.size() > 1) {
			System.err.println("Warining: more than one possible Value for this " + this);
		}
		return proposedValue == null ? possibleValues.stream().findAny() : Optional.of(proposedValue);
	}

}
