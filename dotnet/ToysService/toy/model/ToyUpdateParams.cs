using System.ComponentModel.DataAnnotations;

namespace ToysService.toy.model;

public class ToyUpdateParams
{
    public ToyUpdateParams(string? name, string? description, string? ageRange, string? categoryId, decimal? price, string? imageUrl)
    {
        Name = name;
        Description = description;
        AgeRange = ageRange;
        CategoryId = categoryId;
        Price = price;
        ImageUrl = imageUrl;
    }

    
    public string? Name { get; set; }

    public string? Description { get; set; }

    public string? AgeRange { get; set; }

    public string? CategoryId { get; set; }

    public decimal? Price { get; set; }
    
    public string? ImageUrl { get; set; }
}