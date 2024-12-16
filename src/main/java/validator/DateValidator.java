package validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dateFormatValidator")
public class DateValidator implements Validator{
	 private static final String DATE_FORMAT = "yyyy/MM/dd";
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
            return;
        }
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		 sdf.setLenient(false);
		 try {
	            sdf.parse(value.toString()); // Kiểm tra xem có parse được ngày không
	        } catch (ParseException e) {
	            throw new ValidatorException(new FacesMessage("Định dạng ngày không đúng, yêu cầu là yyyy/MM/dd"));
	        }
	}

}
