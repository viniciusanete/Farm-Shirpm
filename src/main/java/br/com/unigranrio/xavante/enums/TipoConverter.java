package br.com.unigranrio.xavante.enums;

import java.beans.PropertyEditorSupport;

public class TipoConverter extends PropertyEditorSupport{

	 public void setAsText(final Integer text) throws IllegalArgumentException {
	        setValue(TipoEnum.create(text));
	 }
}