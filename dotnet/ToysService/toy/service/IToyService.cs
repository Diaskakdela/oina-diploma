using ToysService.toy.entity;
using ToysService.toy.model;
using ToysService.toy.model.request;

namespace ToysService.toy.service;

public interface IToyService
{
    ICollection<Toy> FindAll();

    decimal CalculatePrice(ToyPriceCalculationRequest toyIds);

    Toy? FindById(Guid toyId);

    Toy Create(ToyCreationParams creationParams);

    Toy UpdateById(Guid toyId, ToyUpdateParams updateParams);

    void DeleteById(Guid toyId);
}