using Domain.Domain_Models;
using Domain.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Interface
{
    public interface ICourseService
    {
        List<Course> GetAllCourses();
        Course GetDetailsForCourse(Guid? id);
        void CreateNewCourse(Course p);
        void UpdeteExistingCourse(Course p);
        void DeleteCourse(Guid id);

        void AddStudentOnCourse(AddStudentOnCourseDTO dto);
    }
}
