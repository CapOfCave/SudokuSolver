package me.lkecker.sudokusolver.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Solution {

	private final Square[][] squares;
}
