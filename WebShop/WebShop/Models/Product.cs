using System.ComponentModel.DataAnnotations.Schema;

namespace WebShop.Models
{
	public class Product
	{
		//[System.ComponentModel.DataAnnotations.Key]
		public int id { get; set; }
		public string name { get; set; }
		public float price { get; set; }
	}
}
