using ToysService.toy.model.request;

namespace ToysService.toy.model;

public class ToyMapper
{
    public ToyCreationParams MapToCreationParams(ToyCreationRequest toyCreationRequest, String imageUrl)
    {
        return new ToyCreationParams(toyCreationRequest.Name, toyCreationRequest.Description,
            toyCreationRequest.AgeRange,
            toyCreationRequest.CategoryId, toyCreationRequest.Price, imageUrl);
    }

    public ToyUpdateParams MapToUpdateParams(ToyUpdateRequest toyUpdateRequest)
    {
        return new ToyUpdateParams(toyUpdateRequest.Name, toyUpdateRequest.Description, toyUpdateRequest.AgeRange,
            toyUpdateRequest.CategoryId, toyUpdateRequest.Price, toyUpdateRequest.ImageUrl);
    }
}