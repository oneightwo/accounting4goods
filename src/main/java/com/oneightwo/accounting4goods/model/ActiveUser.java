package com.oneightwo.accounting4goods.model;

import org.springframework.stereotype.Component;

@Component
public class ActiveUser {

    private User activeUser;

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public String getActiveUserRole() {
        return activeUser.getRole().getRole();
    }

    public String getFullName() {
        return activeUser.getSurname() + " " + activeUser.getName() + " " + activeUser.getPatronymic();
    }

}
