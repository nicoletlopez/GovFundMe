package tk.govfundme.models.services;

import tk.govfundme.models.entities.User;

import java.util.List;

public interface ProfileService
{
    public User viewProfile(String username);

}
