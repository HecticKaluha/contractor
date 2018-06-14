package JMS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageObject implements Serializable {

    private String action;
    private HashMap<String, Object> parameters;


    public MessageObject() {
        this.action = "";
        this.parameters = new HashMap<>();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, Object> parameters) {
        this.parameters = parameters;
    }
    public void addParameter(String parameterName, Object parameter)
    {
        parameters.put(parameterName, parameter);
    }
}
