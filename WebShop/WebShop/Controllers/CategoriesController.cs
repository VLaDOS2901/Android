using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebShop.Data;
using WebShop.Data.Entities;
using WebShop.Helpers;
using WebShop.Models;

namespace WebShop.Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class CategoriesController : ControllerBase
	{

		private readonly IMapper _mapper;
		private readonly AppEFContext _appEFContext;

		public CategoriesController(AppEFContext appEFContext, IMapper mapper)
		{
			_mapper = mapper;
			_appEFContext = appEFContext;
		}

		[HttpGet("list")]
		public async Task<IActionResult> GetAll()
		{
			var model = await _appEFContext.Categories
				.Where(x => x.IsDeleted == false)
				.OrderBy(x => x.Priority)
				.Select(x => _mapper.Map<CategoryItemViewModel>(x))
				.ToListAsync();

			return Ok(model);
		}
		[HttpPost("create")]
		public async Task<IActionResult> Create([FromBody] CategoryCreateItemVM model)
		{
			try
			{
				var cat = _mapper.Map<CategoryEntity>(model);
				cat.Image = ImageWorker.SaveImage(model.ImageBase64);
				await _appEFContext.Categories.AddAsync(cat);
				await _appEFContext.SaveChangesAsync();
				return Ok(_mapper.Map<CategoryItemViewModel>(cat));
			}
			catch (Exception ex)
			{

				return BadRequest(new { error = ex.Message });
			}
		}
		[HttpDelete("delete")]
		public async Task<IActionResult> Delete(int id)
		{
			try
			{
				CategoryEntity model = _appEFContext.Categories
				.Where(x => x.Id == id)
				.OrderBy(x => x.Priority)
				.Last();
				_appEFContext.Categories.Remove(model);
				_appEFContext.SaveChangesAsync();
				return Ok("Item deleted");
			}
			catch (Exception ex)
			{

				return BadRequest(new { error = ex.Message });
			}
		}
		[HttpPut("edit/{id}")]
		public async Task<IActionResult> Edit(int id, [FromBody] CategoryCreateItemVM model)
		{
			try
			{
				var cat = await _appEFContext.Categories.FindAsync(id);
				_mapper.Map(model, cat);
				await _appEFContext.SaveChangesAsync();
				return Ok("Saved successfully");
			}
			catch (Exception ex)
			{

				return BadRequest(new { error = ex.Message });
			}
		}
	}

}
