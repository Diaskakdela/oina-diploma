namespace ToysService.toy.model;

public class ToyCreationParams(
    string name,
    string description,
    string ageRange,
    Guid categoryId,
    decimal price,
    string imageUrl)
{
    public string Name { get; set; } = name;

    public string Description { get; set; } = description;

    public string AgeRange { get; set; } = ageRange;

    public Guid CategoryId { get; set; } = categoryId;

    public decimal Price { get; set; } = price;

    public string ImageUrl { get; set; } = imageUrl;
}