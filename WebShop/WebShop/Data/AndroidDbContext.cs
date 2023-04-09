﻿using Microsoft.EntityFrameworkCore;
using WebShop.Models;

namespace WebShop.Data
{
	public class AndroidDbContext : DbContext
	{
		public AndroidDbContext() { }
		public AndroidDbContext(DbContextOptions options) : base(options) { }
		protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
		{
			optionsBuilder.UseNpgsql("Host=ep-mute-pine-973397.eu-central-1.aws.neon.tech;Database=web-shop;Password=p7FbUj2HzGRf;Persist Security Info=True;Username=vladrewutsky01");
			base.OnConfiguring(optionsBuilder);
		}
		protected override void OnModelCreating(ModelBuilder modelBuilder)
		{
		    modelBuilder.Entity<Product>(e=>e.ToTable("products"));
			base.OnModelCreating(modelBuilder);
		}
		public DbSet<Product> Products { get; set; }
	}
}
