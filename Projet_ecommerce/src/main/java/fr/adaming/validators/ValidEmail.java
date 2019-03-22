package fr.adaming.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("valEmail")
public class ValidEmail implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object valeur) throws ValidatorException {
		
		String saisie=(String) valeur; 
		if(!saisie.contains("@")) {
			throw new ValidatorException(new FacesMessage("Le mail doit contenir un @"));
		}
		
		
	}
	
	

}
