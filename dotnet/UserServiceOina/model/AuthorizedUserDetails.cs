namespace UserServiceOina.model;

public class AuthorizedUserDetails(Guid renterId, string token, ICollection<string> roles)
{
    public Guid RenterId { get; set; } = renterId;
    public string Token { get; set; } = token;
    public ICollection<string> Roles { get; set; } = roles;
}