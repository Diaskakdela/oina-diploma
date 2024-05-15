using System.ComponentModel.DataAnnotations;

namespace UserServiceOina.model.request;

public class LoginRequest(string email, string password)
{
    [Required(ErrorMessage = "Email is required")]
    [EmailAddress(ErrorMessage = "Invalid email format")]
    public string Email { get; set; } = email;
    
    [Required(ErrorMessage = "Password is required")]
    public string Password { get; set; } = password;
}