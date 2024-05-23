namespace ToysService.toy.model.request;

public class ToyUpdateRequest
{
    public string? Name { get; set; }

    public string? Description { get; set; }

    public string? AgeRange { get; set; }

    public string? CategoryId { get; set; }

    public decimal? Price { get; set; }
    
    public string? ImageUrl { get; set; }
}