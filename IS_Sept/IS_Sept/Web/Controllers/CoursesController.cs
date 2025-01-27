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
using Domain.DTO;

namespace Web.Controllers
{
    public class CoursesController : Controller
    {
        private readonly ICourseService _courseService;
        private readonly IStudentService _studentService;
        private readonly ISemesterService _semesterService;

        public CoursesController(ICourseService courseService, IStudentService studentService, ISemesterService semesterService)
        {
            _courseService = courseService;
            _studentService = studentService;
            _semesterService = semesterService;
        }

        // GET: Courses
        public IActionResult Index()
        {
            return View(_courseService.GetAllCourses());
        }

        // GET: Courses/Details/5
        public IActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var course =_courseService.GetDetailsForCourse(id);
            if (course == null)
            {
                return NotFound();
            }

            return View(course);
        }

        // GET: Courses/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Courses/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Create([Bind("CourseCode,CourseName,AavailableSlots,Id")] Course course)
        {
            if (ModelState.IsValid)
            {
                course.Id = Guid.NewGuid();
                _courseService.CreateNewCourse(course);
                return RedirectToAction(nameof(Index));
            }
            return View(course);
        }

        // GET: Courses/Edit/5
        public IActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var course = _courseService.GetDetailsForCourse(id);
            if (course == null)
            {
                return NotFound();
            }
            return View(course);
        }

        // POST: Courses/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Edit(Guid id, [Bind("CourseCode,CourseName,AavailableSlots,Id")] Course course)
        {
            if (id != course.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _courseService.UpdeteExistingCourse(course);
                }
                catch (DbUpdateConcurrencyException)
                {
                    throw;
                }
                return RedirectToAction(nameof(Index));
            }
            return View(course);
        }

        // GET: Courses/Delete/5
        public IActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var course = _courseService.GetDetailsForCourse(id);
            if (course == null)
            {
                return NotFound();
            }

            return View(course);
        }

        // POST: Courses/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public IActionResult DeleteConfirmed(Guid id)
        {
            _courseService.DeleteCourse(id);
            return RedirectToAction(nameof(Index));
        }
        public IActionResult AddStudentOnCourse(Guid? id)
        {
            var course = _courseService.GetDetailsForCourse(id);
            if (course.AavailableSlots <= 0)
            {
                return RedirectToAction(nameof(NoMoreAvailableSlots));
            }
            AddStudentOnCourseDTO dto = new AddStudentOnCourseDTO();
            dto.CourseId= course.Id;
            dto.Semesters = _semesterService.GetAllSemesters();
            dto.Students=_studentService.GetAllStudents();

            return View(dto);
        }
        [HttpPost]
        public IActionResult AddStudentOnCourse(AddStudentOnCourseDTO dto)
        {
            if (ModelState.IsValid)
            {
                var course = _courseService.GetDetailsForCourse(dto.CourseId);
                course.AavailableSlots--;
                _courseService.UpdeteExistingCourse(course);
                _courseService.AddStudentOnCourse(dto);

                return RedirectToAction(nameof(Index));
            }
            return View(dto);
        }
        public IActionResult NoMoreAvailableSlots()
        {
            return View();
        }

        //private bool CourseExists(Guid id)
        //{
        //    return _context.Courses.Any(e => e.Id == id);
        //}
    }
}
