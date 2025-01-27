using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Domain_Models
{
    public class StudentOnCourse:BaseEntity
    {
        public Guid StudentId { get; set; }
        public Student? Student { get; set; }
        public Guid CourseId { get; set; }
        public Course? Course { get; set; }
        public Guid SemesterId { get; set; }
        public Semester? Semester { get; set; }
    }
}
