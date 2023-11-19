package com.example.Lotoya_Java.dto;

import com.example.Lotoya_Java.entity.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class UserContextHolder {

    private static final String USER_SESSION_KEY = "loggedInUser";

    public void setLoggedInUser(HttpSession httpSession, User loggedInUser) {
        if (httpSession != null && loggedInUser != null) {
            httpSession.setAttribute(USER_SESSION_KEY, loggedInUser);
        }
    }


    public User getLoggedInUser(HttpSession httpSession) {
        if (httpSession != null) {
            return (User) httpSession.getAttribute(USER_SESSION_KEY);
        }
        return null;
    }


    public void clearLoggedInUser(HttpSession httpSession) {
        if (httpSession != null) {
            httpSession.removeAttribute(USER_SESSION_KEY);
        }
    }

}
