using Domain.Domain_Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Service.Interface;

namespace Web.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class AdminController : ControllerBase
    {
        private readonly IStudentService _studentService;
        private readonly ISemesterService _semesterService;
        private readonly IStudentOnCourseService _studentOnCourseService;
        public AdminController(IStudentService studentService, ISemesterService semesterService, IStudentOnCourseService studentOnCourseService)
        {
            _studentService = studentService;
            _semesterService = semesterService;
            _studentOnCourseService= studentOnCourseService;
        }

        [HttpGet("[action]")]
        public List<Student> GetAllStudents()
        {
            return _studentService.GetAllStudents();
        }

        [HttpGet("[action]")]
        public List<Semester> GetAllSemesters()
        {
            return _semesterService.GetAllSemesters();
        }

        [HttpGet("[action]")]
        public List<StudentOnCourse> GetStudentOnCourses()
        {
            return _studentOnCourseService.GetStudentOnCourses();
        }
        [HttpPost("[action]")]
        public StudentOnCourse GetDetailsForStudentOnCourse(BaseEntity model)
        {
            return _studentOnCourseService.GetDetailsForStudentOnCourse(model);
        }
    }
}
