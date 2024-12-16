package converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter("DateConverter")
public class DateConverter implements Converter {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 if (value == null || value.isEmpty()) {
	            return null;
	        }
		 try {
	            // Chuyển đổi từ String sang LocalDate
	            return LocalDate.parse(value, formatter);
	        } catch (Exception e) {
	            // Nếu có lỗi, log và trả về null hoặc thông báo lỗi
	            e.printStackTrace();
	            return null;
	        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
            return "";
        }		
	 // Chuyển đổi từ LocalDate sang String
        LocalDate localDate = (LocalDate) value;
        return localDate.format(formatter);
	}
}
