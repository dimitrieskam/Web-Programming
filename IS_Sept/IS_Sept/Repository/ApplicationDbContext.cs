using Domain.Domain_Models;
using Domain.Identity_Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;

namespace Repository
{
    public class ApplicationDbContext : IdentityDbContext<IntegratedSystemsUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Student> Students { get; set; }
        public virtual DbSet<Course> Courses { get; set; }
        public virtual DbSet<Semester> Semesters { get; set; }
        public virtual DbSet<StudentOnCourse> StudentOnCourses { get; set; }
    }
}
