package edu.fudan.gomoku.response;

import java.io.Serializable;

/**
 * @author xiaowangzi
 * @date 17-12-18
 */
public class LoginResponse implements Serializable {

    private Boolean success;

    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
