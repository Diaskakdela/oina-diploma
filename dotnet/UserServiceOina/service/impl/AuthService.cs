using System.Security.Authentication;
using UserServiceOina.entity;
using UserServiceOina.model;
using UserServiceOina.repository;

namespace UserServiceOina.service.impl;

public class AuthService(IUserRepository userRepository, IJwtService jwtService, IRenterRepository renterRepository)
    : IAuthService
{
    public AuthorizedUserDetails Authenticate(string email, string password)
    {
        var user = userRepository.FindUserByEmail(email);
        if (!IsPasswordCorrect(user, password))
        {
            throw new AuthenticationException("Authentication failed: Invalid login or password.");
        }

        var renterId = renterRepository.FindByUserId(user.Id).Id;
        var token =  jwtService.GenerateToken(JwtUserDetails.Create(user, renterId));
        ICollection<string> roles = user.UserRoles
            .Select(ur => ur.Role.ToString().ToUpper())
            .ToList();
        
        return new AuthorizedUserDetails(renterId, token, roles);
    }

    private bool IsPasswordCorrect(User? user, string providedPassword)
    {
        return user != null && VerifyPassword(providedPassword, user.PasswordHash);
    }

    private bool VerifyPassword(string providedPassword, string hashPassword)
    {
        return BCrypt.Net.BCrypt.Verify(providedPassword, hashPassword);
    }
}