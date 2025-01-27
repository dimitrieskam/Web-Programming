using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Domain.Domain_Models;
using Repository;
using Service.Interface;

namespace Web.Controllers
{
    public class SemestersController : Controller
    {
        private readonly ISemesterService _semesterService;

        public SemestersController(ISemesterService semesterService)
        {
            _semesterService = semesterService;
        }

        // GET: Semesters
        public IActionResult Index()
        {
            return View(_semesterService.GetAllSemesters());
        }

        // GET: Semesters/Details/5
        public IActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var semester = _semesterService.GetDetailsForSemester(id);
            if (semester == null)
            {
                return NotFound();
            }

            return View(semester);
        }

        // GET: Semesters/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Semesters/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Create([Bind("SemesterCode,SemesterName,Id")] Semester semester)
        {
            if (ModelState.IsValid)
            {
                semester.Id = Guid.NewGuid();
                _semesterService.CreateNewSemester(semester);
                return RedirectToAction(nameof(Index));
            }
            return View(semester);
        }

        // GET: Semesters/Edit/5
        public IActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var semester = _semesterService.GetDetailsForSemester(id);
            if (semester == null)
            {
                return NotFound();
            }
            return View(semester);
        }

        // POST: Semesters/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Edit(Guid id, [Bind("SemesterCode,SemesterName,Id")] Semester semester)
        {
            if (id != semester.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                   _semesterService.UpdeteExistingSemester(semester);
                }
                catch (DbUpdateConcurrencyException)
                {
                    throw;
                }
                return RedirectToAction(nameof(Index));
            }
            return View(semester);
        }

        // GET: Semesters/Delete/5
        public IActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var semester = _semesterService.GetDetailsForSemester(id);
            if (semester == null)
            {
                return NotFound();
            }

            return View(semester);
        }

        // POST: Semesters/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public IActionResult DeleteConfirmed(Guid id)
        {
            _semesterService.DeleteSemester(id);
            return RedirectToAction(nameof(Index));
        }

        //private bool SemesterExists(Guid id)
        //{
        //    return _context.Semesters.Any(e => e.Id == id);
        //}
    }
}
