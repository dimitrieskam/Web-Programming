package mk.ukim.finki.wp.jan2023.web;


import mk.ukim.finki.wp.jan2023.model.Candidate;
import mk.ukim.finki.wp.jan2023.model.Gender;
import mk.ukim.finki.wp.jan2023.model.Party;
import mk.ukim.finki.wp.jan2023.service.CandidateService;
import mk.ukim.finki.wp.jan2023.service.PartyService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/","/candidates"})
public class CandidatesController {

    private final CandidateService candidateService;
    private final PartyService partyService;

    public CandidatesController(CandidateService candidateService, PartyService partyService) {
        this.candidateService = candidateService;
        this.partyService = partyService;
    }

    /**
     * This method should use the "list.html" template to display all candidates.
     * The method should be mapped on paths '/' and '/candidates'.
     * The arguments that this method takes are optional and can be 'null'.
     * In the case when the arguments are not passed (both are 'null') all candidates should be displayed.
     * If one, or both of the arguments are not 'null', the candidates that are the result of the call
     * to the method 'listCandidatesYearsMoreThanAndGender' from the CandidateService should be displayed.
     *
     * @param years
     * @param gender
     * @return The view "list.html".
     */
    @GetMapping
    public String showCandidates(@RequestParam(required = false) Integer years,
                                 @RequestParam(required = false) Gender gender, Model model) {
        List<Candidate> candidates;
        if (years == null && gender == null) {
            candidates=this.candidateService.listAllCandidates();
        } else {
            candidates=this.candidateService.listCandidatesYearsMoreThanAndGender(years, gender);
        }
        model.addAttribute("candidates",candidates);
        model.addAttribute("genders", Arrays.stream(Gender.values()).collect(Collectors.toList()));
        return "list";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/candidates/add'.
     *
     * @return The view "form.html".
     */
    @GetMapping("/add")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAdd(Model model) {
        List<Party> parties = this.partyService.listAll();
        model.addAttribute("parties",parties);
        model.addAttribute("genders",Arrays.stream(Gender.values()).collect(Collectors.toList()));

        return "form";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the candidate that is updated.
     * The method should be mapped on path '/candidates/[id]/edit'.
     *
     * @return The view "form.html".
     */
    @GetMapping("/{id}/edit")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showEdit(@PathVariable Long id,Model model) {
        Candidate candidate = this.candidateService.findById(id);
        List<Party> parties = this.partyService.listAll();
        model.addAttribute("parties",parties);
        model.addAttribute("genders",Arrays.stream(Gender.values()).collect(Collectors.toList()));
        model.addAttribute("candidate",candidate);
        return "form";
    }

    /**
     * This method should create a candidate given the arguments it takes.
     * The method should be mapped on path '/candidates'.
     * After the candidate is created, all candidates should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping()
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String create(@RequestParam String name,
                         @RequestParam String bio,
                         @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                         @RequestParam Gender gender,
                         @RequestParam Long party) {
        this.candidateService.create(name, bio, dateOfBirth, gender, party);
        return "redirect:/candidates";
    }

    /**
     * This method should update a candidate given the arguments it takes.
     * The method should be mapped on path '/candidates/[id]'.
     * After the candidate is updated, all candidates should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String bio,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                         @RequestParam Gender gender,
                         @RequestParam Long party) {
        this.candidateService.update(id, name, bio, dateOfBirth, gender, party);
        return "redirect:/candidates";
    }

    /**
     * This method should delete the candidate that has the appropriate identifier.
     * The method should be mapped on path '/candidates/[id]/delete'.
     * After the candidate is deleted, all candidates should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/{id}/delete")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable Long id) {
        this.candidateService.delete(id);
        return "redirect:/candidates";
    }

    /**
     * This method should increase the number of votes of the appropriate candidate by 1.
     * The method should be mapped on path '/candidates/[id]/vote'.
     * After the operation, all candidates should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/{id}/vote")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public String vote(@PathVariable Long id) {
        this.candidateService.vote(id);
        return "redirect:/candidates";
    }
}
