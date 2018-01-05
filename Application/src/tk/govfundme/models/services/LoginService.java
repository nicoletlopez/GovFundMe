package tk.govfundme.models.services;

import tk.govfundme.models.entities.User;

import java.util.List;

public interface LoginService
{
    boolean login(String username, String password);
}
