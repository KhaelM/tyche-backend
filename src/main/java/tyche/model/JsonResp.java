/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyche.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class JsonResp {
    private String status;
    private Object data;
    private List<String> errors = new ArrayList<String>();
    
    public void setStatusSuccess() {
    	this.status = "success";
    }
    
    public void setStatusError() {
    	this.status = "error";
    }

    public List<String> getErrors() {
        return errors;
    }
    
    public void addError(String error) {
        errors.add(error);
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
