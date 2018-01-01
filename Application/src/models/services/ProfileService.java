package models.services;

import models.entities.Card;
import models.entities.User;

public interface ProfileService
{
    public User viewProfile(String username);
}
