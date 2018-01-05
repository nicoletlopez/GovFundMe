package models.services;

import javax.servlet.http.Part;

public interface SignupService
{
    boolean signup(String fname, String lname, String email, String username, String password, String ccNum, String profilePic);
}
