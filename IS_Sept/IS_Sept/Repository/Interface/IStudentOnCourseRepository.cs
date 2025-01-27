using Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository.Interface
{
    public interface IStudentOnCourseRepository
    {
        List<StudentOnCourse> GetStudentOnCourses();
        StudentOnCourse GetDetailsForStudentOnCourse(BaseEntity model);
    }
}
