using UserServiceOina.model;

namespace UserServiceOina.service;

public interface IAuthService
{
    AuthorizedUserDetails Authenticate(string email, string password);
}