import parser.JAXBParser;
import parser.Parser;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name = "multiplyBean", eager = true)
@SessionScoped
@XmlRootElement(name = "task")
@XmlType(propOrder = {"firstArgument", "secondArgument", "result"})
public class MultiplyBean implements Serializable {

    @EJB
    private SessionMultiplier sessionMultiplier;


    private double firstArgument;
    private double secondArgument;
    private double result;


    public MultiplyBean(double firstArgument, double secondArgument) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }

    public MultiplyBean() {
    }


    @XmlElement(name = "firstArgument")
    public double getFirstArgument() {
        return firstArgument;
    }

    public void setFirstArgument(double firstArgument) {
        this.firstArgument = firstArgument;
    }

    @XmlElement(name = "secondArgument")
    public double getSecondArgument() {
        return secondArgument;
    }

    public void setSecondArgument(double secondArgument) {
        this.secondArgument = secondArgument;
    }

    @XmlElement(name = "result")
    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String multiply() {
        this.result = sessionMultiplier.multiply(this.firstArgument, this.secondArgument);
        return "result";
    }

    public String showDataByXML() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().dispatch("result.xml");
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "result.xml";
    }
}
