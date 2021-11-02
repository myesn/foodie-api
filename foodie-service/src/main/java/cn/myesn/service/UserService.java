package cn.myesn.service;

import cn.myesn.pojo.Users;
import cn.myesn.pojo.bo.UserBO;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    boolean existsUserName(String username);

    Users create(UserBO userBO);

    Users login(String username, String password) throws NoSuchAlgorithmException;
}
