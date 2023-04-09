using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Internal;
using Microsoft.Extensions.Configuration;
using Npgsql;
using Npgsql.Internal;
using System.Data;
using WebShop.Data;
using WebShop.Models;

namespace WebShop.Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class ProductsController : ControllerBase
	{
		private readonly AndroidDbContext context;
		private readonly IConfiguration _configuration;
		public ProductsController(IConfiguration configuration, AndroidDbContext context)
		{
			this.context = context;
			_configuration = configuration;
		}
		[HttpGet("list")]
		public async Task<IActionResult> GetAll()
		{
			List<Product> response = new List<Product>();
			var dataList = context.Products.ToList();
			dataList.ForEach(row => response.Add(new Product()
			{
				id = row.id,
				name = row.name,
				price = row.price
			}));
			return Ok(dataList);
		}
	}
}
