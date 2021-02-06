package me.lkecker.sudokusolver.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Row implements ConstraintOrigin {

	private List<Square> squares;
	
	
}
