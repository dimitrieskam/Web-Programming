using Domain.Domain_Models;
using Service.Implementation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Interface
{
    public interface ISemesterService
    {
        List<Semester> GetAllSemesters();
        Semester GetDetailsForSemester(Guid? id);
        void CreateNewSemester(Semester p);
        void UpdeteExistingSemester(Semester p);
        void DeleteSemester(Guid id);
    }
}
