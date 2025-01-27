using Domain.Domain_Models;
using Domain.DTO;
using Repository.Interface;
using Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Implementation
{
    public class CourseService : ICourseService
    {
        private readonly IRepository<Course> _courseRepository;
        private readonly IRepository<StudentOnCourse> _studentOnCourseRepository;
        public CourseService(IRepository<Course> courseRepository, IRepository<StudentOnCourse> studentOnCourseRepository)
        {
            _courseRepository = courseRepository;
            _studentOnCourseRepository = studentOnCourseRepository;
        }

        public void AddStudentOnCourse(AddStudentOnCourseDTO dto)
        {
           StudentOnCourse studentOnCourse = new StudentOnCourse();
            studentOnCourse.StudentId = dto.StudentId;
            studentOnCourse.SemesterId = dto.SemesterId;
            studentOnCourse.CourseId=dto.CourseId;
            studentOnCourse.Course=_courseRepository.Get(dto.CourseId);
            _studentOnCourseRepository.Insert(studentOnCourse);
        }

        public void CreateNewCourse(Course p)
        {
           _courseRepository.Insert(p);
        }

        public void DeleteCourse(Guid id)
        {
            var course = _courseRepository.Get(id);
            _courseRepository.Delete(course);
        }

        public List<Course> GetAllCourses()
        {
            return _courseRepository.GetAll().ToList();
        }

        public Course GetDetailsForCourse(Guid? id)
        {
            return _courseRepository.Get(id);
        }

        public void UpdeteExistingCourse(Course p)
        {
           _courseRepository.Update(p);
        }
    }
}
