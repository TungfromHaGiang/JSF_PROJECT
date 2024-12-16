package converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;



@FacesConverter("DateConverter")
public class DateConverter implements Converter {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 if (value == null || value.toString().trim().isEmpty()) {
			 throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Lỗi định dạng", "Bạn chưa nhập vào"));
	        }
		 // Kiểm tra định dạng trước khi parse
	        if (!value.matches("\\d{4}-\\d{2}-\\d{2}")) {
	            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Lỗi định dạng", "Ngày không hợp lệ. Định dạng yêu cầu là yyyy-MM-dd."));
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
