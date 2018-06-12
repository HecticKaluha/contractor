package JMS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageObject implements Serializable {

    private String action;
    private List<Object> parameters;


    public MessageObject() {
        this.action = "";
        this.parameters = new ArrayList<>();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List getParameters() {
        return parameters;
    }

    public void setParameters(List parameters) {
        this.parameters = parameters;
    }
    public void addParameter(Object parameter)
    {
        parameters.add(parameter);
    }
}
