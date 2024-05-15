using System.ComponentModel.DataAnnotations;

namespace ToysService.toy.model.request;

public class ToyCreationRequest
{
    [Required(ErrorMessage = "Name is required")]
    [StringLength(100, ErrorMessage = "Name must be less than 100 characters")]
    public string Name { get; set; }

    [Required(ErrorMessage = "Description is required")]
    public string Description { get; set; }

    [Required(ErrorMessage = "Age range is required")]
    public string AgeRange { get; set; }

    [Required(ErrorMessage = "Category ID is required")]
    public Guid CategoryId { get; set; }

    [Required(ErrorMessage = "Price is required")]
    [Range(0.01, double.MaxValue, ErrorMessage = "Price must be greater than zero")]
    public decimal Price { get; set; }

    [Required(ErrorMessage = "Image file is required")]
    public IFormFile ImageFile { get; set; }
}