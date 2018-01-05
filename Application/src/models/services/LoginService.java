package models.services;

import models.entities.User;

import java.util.List;

public interface LoginService
{
    boolean login(String username, String password);
}
