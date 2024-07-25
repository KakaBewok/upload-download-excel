package com.noprizal.test_excel.exceptions;

import java.util.List;

public class DuplicateDataException extends RuntimeException {
	private static final long serialVersionUID = 8250644708435873670L;

	private List<Integer> duplicateNims;

	public DuplicateDataException(List<Integer> duplicateNims) {
		super("Duplicate NIMs found: " + duplicateNims);
		this.duplicateNims = duplicateNims;
	}

	public List<Integer> getDuplicateNims() {
		return duplicateNims;
	}
}
