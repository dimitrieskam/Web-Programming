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
    public class SemesterService : ISemesterService
    {
        private readonly IRepository<Semester> _semesterRepository;

        public SemesterService(IRepository<Semester> semesterRepository)
        {
            _semesterRepository = semesterRepository;
        }

        public void CreateNewSemester(Semester p)
        {
            _semesterRepository.Insert(p);
        }

        public void DeleteSemester(Guid id)
        {
            var semester = _semesterRepository.Get(id);
            _semesterRepository.Delete(semester);
        }

        public List<Semester> GetAllSemesters()
        {
            return _semesterRepository.GetAll().ToList();
        }

        public Semester GetDetailsForSemester(Guid? id)
        {
            return _semesterRepository.Get(id);
        }

        public void UpdeteExistingSemester(Semester p)
        {
            _semesterRepository.Update(p);
        }
    }
}
