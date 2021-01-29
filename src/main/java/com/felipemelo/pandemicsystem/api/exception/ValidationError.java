package com.felipemelo.pandemicsystem.api.exception;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/*Esta classe é utilizada para dar forma erros compostos, por exemplo quando é esquecido de passar
 * na mesma requisição de negociação o id dos hospitais e/ou as listas de recursos.*/

public class ValidationError extends StandardError{
private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, OffsetDateTime timeStamp) {
		super(status, msg, timeStamp);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
}
