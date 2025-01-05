package com.example.its.web.issue;

import com.example.its.doamin.issue.IssueEntity;
import com.example.its.doamin.issue.IssueService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
// fianlフィールドに対してコンストラクタを自動生成
@RequiredArgsConstructor
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public String showList(Model model) {
        model.addAttribute("issueList", issueService.findAll());
        return "issues/list";
    }

    @GetMapping("creationForm")
    public String showCreationForm(@ModelAttribute IssueForm form) {
        // resources/templates/からの相対パスを記載^.html拡張子は省略できる
        return "issues/creationForm";
    }

    @PostMapping
    public String create(@Validated IssueForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(form);
        }
        issueService.create(form.getSummary(), form.getDescription());
        return "redirect:/issues";
    }

    @GetMapping("/{issueId}")
    public String showDetail(@PathVariable("issueId") Long issueId, Model model) {
        model.addAttribute("issue", issueService.findById(issueId));
        return "issues/detail";
    }

    @DeleteMapping("/{issueId}")
    public String deleteIssue(@PathVariable("issueId") Long issueId) {
        issueService.deleteIssue(issueId);
        return "redirect:/issues";
    }

    @DeleteMapping("/reset")
    public String resetIssues() {
        issueService.deleteAllIssues();
        return "redirect:/issues";
    }

    @PutMapping("/{issueId}")
    public String updateIssue(@PathVariable("issueId") Long issueId, @Validated IssueForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("issue", issueService.findById(issueId));
            return "issues/detail";
        }
        issueService.updateIssue(issueId, form.getSummary(), form.getDescription());
        return "redirect:/issues";
    }


}
