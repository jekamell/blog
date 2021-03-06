package com.jekamell.crud.forum.controller;

import com.jekamell.crud.forum.model.Comment;
import com.jekamell.crud.forum.model.Topic;
import com.jekamell.crud.forum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
public class TopicController extends com.jekamell.crud.forum.controller.Controller {
    @Autowired
    private ForumService forumService;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showCategoryList(Model model) {
        model.addAttribute(forumService.getAllCategory());

        return "list-category";
    }


    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String showTopicsByCategory(@PathVariable(value = "id") final String id, Model model) {
        model.addAttribute("topicList", forumService.getTopicList(Long.parseLong(id)));
        model.addAttribute("categoryId", id);

        return "list-topic";
    }

    @RequestMapping(value = "/category/{id}/add-topic", method = RequestMethod.GET)
    public String showAddTopicForm(Model model, @PathVariable final long id) {
        Topic topic = new Topic();
        topic.setCategory(forumService.getCategory(id));
        model.addAttribute(topic);

        return "add-topic";
    }

    @RequestMapping(value = "/topic/add", method = RequestMethod.POST)
    public String addTopic(@Valid Topic topic, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(topic);
            return "add-topic";
        }

        forumService.addTopic(topic);

        return "redirect:/topic/show/" + topic.getId();
    }

    @RequestMapping(value = "/topic/show/{id}", method = RequestMethod.GET)
    public String showTopic(@PathVariable Long id, Model model) {
        Topic topic = forumService.getTopic(id);
        Comment comment = new Comment();
        comment.setTopic(forumService.getTopic(id));
        model.addAttribute(topic);
        model.addAttribute(comment);
        model.addAttribute("showModal", false);

        return "show-topic";
    }

    @RequestMapping(value = "/topic/add-comment", method = RequestMethod.POST)
    public String addComment(@Valid Comment comment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Topic topic = forumService.getTopic(comment.getTopic().getId());
            model.addAttribute(topic);
            model.addAttribute(comment);
            model.addAttribute("showModal", bindingResult.hasErrors());

            return "show-topic";
        }
        forumService.addComment(comment);

        return "redirect:/topic/show/" + comment.getTopic().getId();
    }
}
