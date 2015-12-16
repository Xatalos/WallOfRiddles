package WoR.controller;

import WoR.domain.Riddle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import WoR.repository.RiddleRepository;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("*")
public class RiddleController {

    @Autowired
    private RiddleRepository riddleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String listRiddles(Model model) {
        Pageable pageable = new PageRequest(0, 10000, Sort.Direction.DESC, "id");
        // room for 10000 entries in Heroku so that many riddles on the "wall"...
        Page<Riddle> riddlePage = riddleRepository.findAll(pageable);
        model.addAttribute("riddles", riddlePage.getContent());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createRiddle(@Valid @ModelAttribute Riddle riddle,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("notification", "One or more fields were left empty!");
            return "redirect:/index";
        }
        riddleRepository.save(riddle);
        return "redirect:/index";
    }

    @Transactional
    @RequestMapping(value = "/answer/{riddleId}", method = RequestMethod.POST)
    public String answerRiddle(@PathVariable Long riddleId,
            @RequestParam String ownAnswer,
            RedirectAttributes redirectAttributes) {
        Riddle riddle = riddleRepository.findOne(riddleId);
        String correctAnswer = riddle.getAnswer();
        if (ownAnswer.toLowerCase().equals(correctAnswer.toLowerCase())) {
            riddle.setCorrectGuesses(riddle.getCorrectGuesses() + 1);
            redirectAttributes.addFlashAttribute("notification", "Your answer was correct!");
        } else if (ownAnswer.isEmpty()) {
            redirectAttributes.addFlashAttribute("notification", "You didn't give an answer!");
        } else {
            riddle.setWrongGuesses(riddle.getWrongGuesses() + 1);
            redirectAttributes.addFlashAttribute("notification", "Your answer was wrong!");
        }
        return "redirect:/index";
    }
}
