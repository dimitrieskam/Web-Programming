using Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Interface
{
    public interface IStudentOnCourseService
    {
        List<StudentOnCourse> GetStudentOnCourses();
        StudentOnCourse GetDetailsForStudentOnCourse(BaseEntity model);
    }
}
