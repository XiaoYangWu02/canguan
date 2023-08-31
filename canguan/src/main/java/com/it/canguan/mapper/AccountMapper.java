package com.it.canguan.mapper;

import com.it.canguan.dto.AccountLoginDto;
import com.it.canguan.dto.UpdateUserInfoDto;
import com.it.canguan.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 武霄阳
* @description 针对表【tb_account】的数据库操作Mapper
* @createDate 2023-08-28 20:44:01
* @Entity com.it.canguan.pojo.Account
*/
@Mapper
public interface AccountMapper {


    List<Account> login(AccountLoginDto accountLoginDto);

    List<Account> searchAccountById(Integer id);

    List<Account> searchAccountByUserName(String username);

    void updateUserInfo(UpdateUserInfoDto updateUserInfoDto);

    void register(AccountLoginDto accountLoginDto);
}
