using System.ComponentModel.DataAnnotations;

namespace UserServiceOina.model.request;

public class RegistrationRequest(string email, string phone, string password, string firstName, string lastName)
{
    [Required(ErrorMessage = "Email is required")]
    [EmailAddress(ErrorMessage = "Invalid email format")]
    public string Email { get; set; } = email;

    [Required(ErrorMessage = "Phone is required")]
    [Phone(ErrorMessage = "Invalid phone number format")]
    public string Phone { get; set; } = phone;

    [Required(ErrorMessage = "Password is required")]
    public string Password { get; set; } = password;

    [Required(ErrorMessage = "First name is required")]
    public string FirstName { get; set; } = firstName;

    [Required(ErrorMessage = "Last name is required")]
    public string LastName { get; set; } = lastName;
}