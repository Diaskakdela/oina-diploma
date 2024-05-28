using System.ComponentModel.DataAnnotations;

namespace ToysService.toy.model.request;

public class ToyPriceCalculationRequest(IDictionary<Guid, Int32> toyIds)
{
    [Required(ErrorMessage = "toyIds is required")]
    public IDictionary<Guid, Int32> ToyIds { get; } = toyIds;
}