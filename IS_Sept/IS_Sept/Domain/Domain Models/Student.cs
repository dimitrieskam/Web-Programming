using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Domain_Models
{
    public class Student:BaseEntity
    {
        public string? StudentIndex { get; set; }
        public string? FirstName { get; set; }
        public string? LastName { get; set; }
        public string? Embg { get; set; }
        public string? PhoneNumber { get; set; }
        public virtual ICollection<StudentOnCourse>? AllCourses { get; set; }
    }
}
