package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("validators.NumbersValidator")
public class NumbersValidator implements Validator {

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        Pattern pattern = Pattern.compile("^[-+]?[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?");
        String data = o.toString();

        if (!pattern.matcher(data).matches()) {
            FacesMessage msg = new FacesMessage("Input text isn`t a number.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

}
