package models.services;

public interface SignupService
{
    boolean signup(String fname, String lname, String email, String username, String password, String ccNum);
}
