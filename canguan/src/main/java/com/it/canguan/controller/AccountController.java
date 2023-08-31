package com.it.canguan.controller;

import com.it.canguan.dto.AccountLoginDto;
import com.it.canguan.dto.UpdateUserInfoDto;
import com.it.canguan.pojo.Account;
import com.it.canguan.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequestMapping("/account")
@Controller
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("login")
    public String login(AccountLoginDto accountLoginDto, HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
        List<Account> accounts = accountService.login(accountLoginDto);
        if(accounts == null || accounts.size() ==0 ){
            redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
            return "redirect:/main/toLogin";
        }
        else {
            session.setAttribute("accountSession", accounts.get(0));
        }
        redirectAttributes.addFlashAttribute("right", "登录成功");
        return "redirect:/main/index";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {

        session.removeAttribute("accountSession");

        return "redirect:/main/index";
    }

    @PostMapping("updateUserInfo")
    public String updateUserInfo(UpdateUserInfoDto updateUserInfoDto, RedirectAttributes redirectAttributes, HttpSession session){

        Account accounts2 = (Account) session.getAttribute("accountSession");
        if(accounts2 == null){
            return "redirect:/main/toLogin";
        }

        List<Account> accountList = accountService.searchAccountById(updateUserInfoDto.getId());
        Account account = accountList.get(0);
        List<Account> accounts = accountService.searchAccountByUserName(updateUserInfoDto.getUsername());
        if(accounts != null && accounts.size() !=0 ){
            if(!account.getUsername().equals(accounts.get(0).getUsername())){
                return "redirect:/main/toUserCenter";
            }
        }

        accountService.updateUserInfo(updateUserInfoDto);
        List<Account> accounts1 = accountService.searchAccountById(updateUserInfoDto.getId());
        account = accounts1.get(0);
        session.setAttribute("accountSession", account);

        return "redirect:/main/toUserCenter";
    }

    @PostMapping("register")
    public String register(AccountLoginDto accountLoginDto, RedirectAttributes redirectAttributes){
        List<Account> accounts = accountService.searchAccountByUserName(accountLoginDto.getUsername());
        if(accounts != null && accounts.size() !=0 ){
            redirectAttributes.addFlashAttribute("error", "用户名已存在");
            return "redirect:/main/toRegister";
        }

        accountService.register(accountLoginDto);
        return "redirect:/main/toLogin";

    }

}
