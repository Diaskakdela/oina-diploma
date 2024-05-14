namespace UserServiceOina.model.response;

public class LoginResponse(Guid renterId, string token, ICollection<string> roles)
{
    public Guid RenterId { get; } = renterId;
    public string Token { get; } = token;
    public ICollection<string> Roles { get; } = roles;

    public static LoginResponse FromUserDetails(AuthorizedUserDetails authorizedUserDetails)
    {
        return new LoginResponse(authorizedUserDetails.RenterId, authorizedUserDetails.Token,
            authorizedUserDetails.Roles);
    }
}