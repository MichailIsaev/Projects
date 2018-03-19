package multiply;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "task")
@XmlType(propOrder={"firstArgument", "secondArgument", "result"})
public class Multiply {


    private double firstArgument;
    private double secondArgument;
    private double result;

    public Multiply(double firstArgument, double secondArgument) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }

    public Multiply() {
    }


    @XmlElement(name="firstArgument")
    public double getFirstArgument() {
        return firstArgument;
    }

    public void setFirstArgument(double firstArgument) {
        this.firstArgument = firstArgument;
    }

    @XmlElement(name="secondArgument")
    public double getSecondArgument() {
        return secondArgument;
    }

    public void setSecondArgument(double secondArgument) {
        this.secondArgument = secondArgument;
    }

    @XmlElement(name="result")
    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void multiply() {
        this.result = this.firstArgument * this.secondArgument;
    }
}
