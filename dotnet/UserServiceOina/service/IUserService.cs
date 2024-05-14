using UserServiceOina.events;
using UserServiceOina.model;
using UserServiceOina.service.impl;

namespace UserServiceOina.service;

public interface IUserService
{
    public event UserRegistrationEventHandler UserRegistered;
    AuthorizedUserDetails RegisterUser(UserRegistrationParams userRegistrationParams);
    AuthorizedUserDetails RegisterAdmin(UserRegistrationParams userRegistrationParams);
}