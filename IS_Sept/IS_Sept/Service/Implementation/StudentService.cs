using Domain.Domain_Models;
using Repository.Interface;
using Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Implementation
{
    public class StudentService : IStudentService
    {
        private readonly IRepository<Student> _studentRepository;   

        public StudentService(IRepository<Student> studentRepository)
        {
            _studentRepository = studentRepository;
        }
        public void CreateNewStudent(Student p)
        {
           _studentRepository.Insert(p);
        }

        public void DeleteStudent(Guid id)
        {
            var student = _studentRepository.Get(id);
            _studentRepository.Delete(student);
        }

        public List<Student> GetAllStudents()
        {
            return _studentRepository.GetAll().ToList();
        }

        public Student GetDetailsForStudent(Guid? id)
        {
            return _studentRepository.Get(id);
        }

        public void UpdeteExistingStudent(Student p)
        {
            _studentRepository.Update(p);
        }
    }
}
