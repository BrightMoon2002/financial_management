package com.group4.service.accountService;

import com.group4.model.account.Account;
import com.group4.service.IService;

import java.util.List;

public interface IAccountService extends IService<Account> {

    List<Account> showFriendListByType(String query, int id);
}
