using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Domain_Models
{
    public class Course:BaseEntity
    {
        public string? CourseCode { get; set; }
        public string? CourseName { get; set; }
        public int AavailableSlots { get; set; }
        public virtual ICollection<StudentOnCourse>? AllStudents { get; set; }
    }
}
