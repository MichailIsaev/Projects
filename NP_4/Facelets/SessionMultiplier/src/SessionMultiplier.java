import javax.ejb.Remote;
import javax.ejb.RemoteHome;

@Remote
public interface SessionMultiplier {
    public double multiply(double a, double b);
}
