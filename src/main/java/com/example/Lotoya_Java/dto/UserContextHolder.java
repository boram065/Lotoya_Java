//package com.example.Lotoya_Java.dto;
//
//import com.example.Lotoya_Java.entity.User;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpSession;
//
//@Component
//public class UserContextHolder {
//
//    private static final String USER_SESSION_KEY = "loggedInUser";
//
//    private final HttpSession httpSession;
//
//    public UserContextHolder(HttpSession httpSession) {
//        this.httpSession = httpSession;
//    }
//
//    public void setLoggedInUser(User loggedInUser) {
//        httpSession.setAttribute(USER_SESSION_KEY, loggedInUser);
//    }
//
//    public User getLoggedInUser() {
//        return (User) httpSession.getAttribute(USER_SESSION_KEY);
//    }
//
//    public void clearLoggedInUser() {
//        httpSession.removeAttribute(USER_SESSION_KEY);
//    }
//}