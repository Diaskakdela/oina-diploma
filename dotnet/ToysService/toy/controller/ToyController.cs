using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using ToysService.core.filter;
using ToysService.toy.model;
using ToysService.toy.model.request;
using ToysService.toy.service;

namespace ToysService.toy.controller;

[ApiController]
[Route("toys")]
public class ToyController(IToyService toyService, ToyMapper toyMapper) : ControllerBase
{
    [HttpGet]
    [AllowAnonymous]
    public IActionResult FindAll()
    {
        return Ok(toyService.FindAll());
    }

    [HttpGet("{id}")]
    [AllowAnonymous]
    [ValidateGuid("id")]
    public IActionResult FindById(string id)
    {
        var toy = toyService.FindById(Guid.Parse(id));
        if (toy == null)
        {
            return NotFound($"No toy found with ID {id}");
        }

        return Ok(toy);
    }

    [HttpPost("calculate-price")]
    [AllowAnonymous]
    public IActionResult CalculatePrice([FromBody] ToyPriceCalculationRequest toyPriceCalculationRequest)
    {
        var price = toyService.CalculatePrice(toyPriceCalculationRequest);

        return Ok(price);
    }

    [HttpPost]
    [Authorize(Roles = "ADMIN")]
    public async Task<IActionResult> Save([FromForm] ToyCreationRequest toyCreationRequest)
    {
        try
        {
            var imageName = await SaveImageAsync(toyCreationRequest.ImageFile);
            var creationParams = toyMapper.MapToCreationParams(toyCreationRequest, imageName);
            var createdToy = toyService.Create(creationParams);
            return Ok(new { Toy = createdToy });
        }
        catch (Exception e)
        {
            return StatusCode(500, "An error occurred while creating toy.");
        }
    }

    [HttpPut("{id}")]
    [Authorize(Roles = "ADMIN")]
    [ValidateGuid("id")]
    public IActionResult Update(string id, [FromBody] ToyUpdateRequest toyUpdateRequest)
    {
        try
        {
            var updateParams = toyMapper.MapToUpdateParams(toyUpdateRequest);
            var updatedToy = toyService.UpdateById(Guid.Parse(id), updateParams);
            return Ok(new { Toy = updatedToy });
        }
        catch (Exception e)
        {
            return StatusCode(500, "An error occurred while updating toy.");
        }
    }

    [HttpDelete("{id}")]
    [Authorize(Roles = "ADMIN")]
    [ValidateGuid("id")]
    public IActionResult Delete(string id)
    {
        try
        {
            toyService.DeleteById(Guid.Parse(id));
            return Ok();
        }
        catch (Exception e)
        {
            return StatusCode(500, "An error occurred while updating toy.");
        }
    }

    private async Task<string> SaveImageAsync(IFormFile file)
    {
        var directoryPath = "path_to_images_directory";
        var fileExtension = Path.GetExtension(file.FileName);
        var fileName = Guid.NewGuid() + fileExtension;
        var filePath = Path.Combine(directoryPath, fileName);

        if (!Directory.Exists(directoryPath))
        {
            Directory.CreateDirectory(directoryPath);
        }

        await using var stream = new FileStream(filePath, FileMode.Create);
        await file.CopyToAsync(stream);
        return fileName;
    }
}