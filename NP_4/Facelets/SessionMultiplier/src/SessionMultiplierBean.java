import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless(name = "SessionMultiplier")
public class SessionMultiplierBean implements SessionMultiplier {

    public SessionMultiplierBean() {
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }
}
