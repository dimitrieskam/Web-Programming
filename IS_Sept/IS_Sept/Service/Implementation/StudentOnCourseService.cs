using Domain.Domain_Models;
using Repository.Interface;
using Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Implementation
{
    public class StudentOnCourseService : IStudentOnCourseService
    {
        private readonly IStudentOnCourseRepository _studentOnCourseRepository;
        public StudentOnCourseService(IStudentOnCourseRepository studentOnCourseRepository)
        {
            _studentOnCourseRepository = studentOnCourseRepository;
        }

        public StudentOnCourse GetDetailsForStudentOnCourse(BaseEntity model)
        {
            return _studentOnCourseRepository.GetDetailsForStudentOnCourse(model);
        }

        public List<StudentOnCourse> GetStudentOnCourses()
        {
            return _studentOnCourseRepository.GetStudentOnCourses();
        }
    }
}
