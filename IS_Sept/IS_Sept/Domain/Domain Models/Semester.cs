using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Domain_Models
{
    public class Semester:BaseEntity
    {
        public string? SemesterCode { get; set; }
        public string? SemesterName { get; set; }
        public virtual ICollection<StudentOnCourse>? StudentOnCourses { get; set; }
    }
}
