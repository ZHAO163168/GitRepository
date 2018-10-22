package com.zhao.springboot.blog.controller;

import com.zhao.springboot.blog.domain.User;
import com.zhao.springboot.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * 查询所有用户
     *
     * @param model
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(Model model) {
        List<User> users = userRepository.getUsers();
        model.addAttribute("userList", users);
        model.addAttribute("title", "用户管理");
        return new ModelAndView("user/list", "userModel", model);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        userRepository.deleteUser(id);
        return new ModelAndView("redirect:/users"); //重定向到list页面
    }

    /**
     * 获取修改用户的界面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") Long id, Model model) {
        User user = userRepository.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("get/{id}")
    public ModelAndView getUser(@PathVariable("id") Long id, Model model) {
        User user = userRepository.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }

    /**
     * 获取创建表单页面
     *
     * @param model
     * @return
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    @PostMapping
    public ModelAndView saveOrUpdateUser(User user) {
        user = userRepository.saveOrUpdateUser(user);
        return new ModelAndView("users/form", "userModel", user);
    }
}
