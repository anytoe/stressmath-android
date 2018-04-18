package mathchallenge.anytoe.com.mathchallenge.model.user;

import java.io.Serializable;

/**
 * Created by anytoe on 10/10/2015.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 5886874049581372015L;

    private String userId;
    private String userName;

    public User() {
        // default empty constructor
    }

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
