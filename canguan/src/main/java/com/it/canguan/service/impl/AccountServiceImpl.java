package com.it.canguan.service.impl;

import com.it.canguan.dto.AccountLoginDto;
import com.it.canguan.dto.UpdateUserInfoDto;
import com.it.canguan.pojo.Account;
import com.it.canguan.service.AccountService;
import com.it.canguan.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 武霄阳
* @description 针对表【tb_account】的数据库操作Service实现
* @createDate 2023-08-28 20:44:01
*/
@Service
public class AccountServiceImpl implements AccountService{

    @Resource
    private AccountMapper accountMapper;

    @Override
    public List<Account> login(AccountLoginDto accountLoginDto) {
        return accountMapper.login(accountLoginDto);
    }

    @Override
    public List<Account> searchAccountById(Integer id) {
        List<Account> accountList = accountMapper.searchAccountById(id);
        return accountList;
    }

    @Override
    public List<Account> searchAccountByUserName(String username) {
        List<Account> accountList = accountMapper.searchAccountByUserName(username);
        return accountList;
    }

    @Override
    public void updateUserInfo(UpdateUserInfoDto updateUserInfoDto) {
        accountMapper.updateUserInfo(updateUserInfoDto);
    }

    @Override
    public void register(AccountLoginDto accountLoginDto) {
        accountMapper.register(accountLoginDto);
    }
}
