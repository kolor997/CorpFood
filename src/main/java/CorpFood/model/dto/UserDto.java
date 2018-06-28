package CorpFood.model.dto;

import CorpFood.model.entity.User;

public class UserDto {

    private Long id;
    private String login;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
