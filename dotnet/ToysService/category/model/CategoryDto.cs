namespace ToysService.category.model;

public class CategoryDto(Guid id, string categoryName)
{
    public Guid Id { get; set; } = id;
    public String CategoryName { get; set; } = categoryName;
}