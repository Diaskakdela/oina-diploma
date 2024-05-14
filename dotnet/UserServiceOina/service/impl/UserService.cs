using UserServiceOina.entity;
using UserServiceOina.events;
using UserServiceOina.exceptions;
using UserServiceOina.factory;
using UserServiceOina.model;
using UserServiceOina.repository;

namespace UserServiceOina.service.impl;

public class UserService(
    IUserRepository userRepository,
    IUserFactory userFactory,
    IJwtService jwtService,
    IRenterService renterService,
    ILogger<UserService> logger) : IUserService
{
    public event UserRegistrationEventHandler? UserRegistered;

    private AuthorizedUserDetails RegisterWithRole(UserRegistrationParams userRegistrationParams, RoleEnum role)
    {
        logger.LogInformation(
            $"Attempting to register new user with email: {userRegistrationParams.Email} and role: {role}");
        if (userRepository.FindUserByEmail(userRegistrationParams.Email) != null)
        {
            logger.LogWarning(
                $"Registration attempt failed: User with email {userRegistrationParams.Email} already exists.");
            throw new UserRegistrationException("User with this email already exists.");
        }

        var user = userFactory.CreateFromRegistrationParams(userRegistrationParams);
        var savedUser = userRepository.RegisterUserWithRole(user, role);
        var renterId = renterService.CreateNewRenter(new RenterCreationParams(savedUser.Id));
        logger.LogInformation($"User registered successfully with ID: {savedUser.Id}");
        var roles = user.UserRoles
            .Select(ur => ur.Role.ToString().ToUpper())
            .ToList();

        var token = jwtService.GenerateToken(JwtUserDetails.Create(savedUser, renterId));

        OnUserRegistered(savedUser);

        logger.LogInformation($"JWT token generated for user ID: {savedUser.Id}");

        return new AuthorizedUserDetails(renterId, token, roles);
    }

    public AuthorizedUserDetails RegisterUser(UserRegistrationParams userRegistrationParams)
    {
        return RegisterWithRole(userRegistrationParams, RoleEnum.User);
    }

    public AuthorizedUserDetails RegisterAdmin(UserRegistrationParams userRegistrationParams)
    {
        return RegisterWithRole(userRegistrationParams, RoleEnum.Admin);
    }

    protected virtual void OnUserRegistered(User user)
    {
        UserRegistered?.Invoke(this, new UserRegisteredEventArgs() { User = user });
    }
}

public delegate void UserRegistrationEventHandler(UserService sender, UserRegisteredEventArgs e);