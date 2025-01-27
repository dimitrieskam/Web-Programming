
using Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.DTO
{
    public class AddStudentOnCourseDTO
    {
        public Guid StudentId { get; set; }
        public List<Student>? Students { get; set; }
        public Guid CourseId { get; set; }
      
        public Guid SemesterId { get; set; }
        public List<Semester>? Semesters { get; set; }
    }
}
