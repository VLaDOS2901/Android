﻿using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Runtime.CompilerServices;

namespace WebShop.Data.Entities
{

	[Table("tblCategories")]
	public class CategoryEntity : BaseEntity<int>
	{
		[Required, StringLength(250)]
		public string Name { get; set; }
		public int Priority { get; set; }
		[StringLength(255)]
		public string Image { get; set; }
		[StringLength(4000)]
		public string Description { get; set; }
	}
}
