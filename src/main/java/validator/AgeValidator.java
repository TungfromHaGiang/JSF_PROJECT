package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ageValidator")
public class AgeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		   if (value == null) {
	            return;
	        }

	        try {
	            Integer number = Integer.valueOf(value.toString());
	            if (number < 18) {
	            	FacesMessage facesMessage =  new FacesMessage();
	            	facesMessage.setSummary("Tuổi phải lớn hơn 18");
	                throw new ValidatorException(facesMessage);
	            }
	        } catch (NumberFormatException e) {
	        	FacesMessage facesMessage =  new FacesMessage();
            	facesMessage.setSummary("Giá trị phải là một số");
                throw new ValidatorException(facesMessage);
	        }
	}

}
