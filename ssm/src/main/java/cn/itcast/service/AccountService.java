package cn.itcast.service;

import cn.itcast.domain.Account;

import java.util.List;

public interface AccountService {
    // 查询所有账号
    public List<Account> findAll();

    // 保存账号
    public void saveAccount(Account account);
}
