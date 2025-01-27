using Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Interface
{
    public interface IStudentService
    {
        List<Student> GetAllStudents();
        Student GetDetailsForStudent(Guid? id);
        void CreateNewStudent(Student p);
        void UpdeteExistingStudent(Student p);
        void DeleteStudent(Guid id);
    }
}
