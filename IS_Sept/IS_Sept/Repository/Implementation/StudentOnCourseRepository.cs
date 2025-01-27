using Domain.Domain_Models;
using Microsoft.EntityFrameworkCore;
using Repository.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository.Implementation
{
    public class StudentOnCourseRepository : IStudentOnCourseRepository
    {
        private readonly ApplicationDbContext _context;
        private DbSet<StudentOnCourse> _studentOnCourses;
        public StudentOnCourseRepository(ApplicationDbContext context)
        {
            _context = context;
            _studentOnCourses = context.Set<StudentOnCourse>();
        }
        public StudentOnCourse GetDetailsForStudentOnCourse(BaseEntity model)
        {
            return _studentOnCourses
                .Include(z => z.Course)
                .Include(z => z.Semester)
                .Include(z => z.Student)
                .SingleOrDefaultAsync(z => z.Id == model.Id).Result;
        }

        public List<StudentOnCourse> GetStudentOnCourses()
        {
            return _studentOnCourses
                .Include(z=>z.Course)
                .Include(z=>z.Student)
                .Include(z=>z.Semester)
                .ToList();
        }
    }
}
