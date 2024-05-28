using ToysService.toy.entity;
using ToysService.toy.exceptions;
using ToysService.toy.factory;
using ToysService.toy.model;
using ToysService.toy.model.request;
using ToysService.toy.repository;

namespace ToysService.toy.service;

public class ToyService(IToyRepository toyRepository, ToyFactory toyFactory) : IToyService
{
    public ICollection<Toy> FindAll()
    {
        return toyRepository.FindAll();
    }

    public decimal CalculatePrice(ToyPriceCalculationRequest request)
    {
        var toys = toyRepository.FindByIds(request.ToyIds.Keys);
        
        if (toys.Count != request.ToyIds.Keys.Count)
        {
            throw new ToyPriceCalculatingException("Not every toy was found.");
        }

        if (toys.Any(toy => toy.Price <= 0))
        {
            throw new ToyPriceCalculatingException("Some of the toys have invalid price values.");
        }

        decimal totalPrice = 0m;
        foreach (var toy in toys)
        {
            int quantity = request.ToyIds[toy.Id];
            if (quantity <= 0)
            {
                throw new ToyPriceCalculatingException("Quantity of toys must be greater than zero.");
            }
            totalPrice += toy.Price * quantity;
        }

        return totalPrice;
    }

    public Toy? FindById(Guid toyId)
    {
        return toyRepository.FindById(toyId);
    }

    public Toy Create(ToyCreationParams creationParams)
    {
        var toy = toyFactory.Create(creationParams);
        return toyRepository.Create(toy);
    }

    public Toy UpdateById(Guid toyId, ToyUpdateParams updateParams)
    {
        var toy = FindById(toyId);
        if (toy == null)
        {
            throw new ToyNotFoundException($"Toy with id={toyId} not found!");
        }

        UpdateExistsParams(toy, updateParams);
        toyRepository.Update(toy);
        return toy;
    }

    public void DeleteById(Guid toyId)
    {
        toyRepository.DeleteById(toyId);
    }

    private void UpdateExistsParams(Toy toyToUpdate, ToyUpdateParams updateParams)
    {
        if (!string.IsNullOrEmpty(updateParams.Name))
            toyToUpdate.Name = updateParams.Name;
        if (!string.IsNullOrEmpty(updateParams.Description))
            toyToUpdate.Description = updateParams.Description;
        if (!string.IsNullOrEmpty(updateParams.AgeRange))
            toyToUpdate.AgeRange = updateParams.AgeRange;
        if (!string.IsNullOrEmpty(updateParams.CategoryId))
            toyToUpdate.CategoryId = Guid.Parse(updateParams.CategoryId);
        if (updateParams.Price > 0)
            toyToUpdate.Price = updateParams.Price.Value;
        if (!string.IsNullOrEmpty(updateParams.ImageUrl))
            toyToUpdate.ImageUrl = updateParams.ImageUrl;
    }
}