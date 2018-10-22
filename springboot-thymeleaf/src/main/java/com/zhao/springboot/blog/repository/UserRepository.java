package com.zhao.springboot.blog.repository;

import com.zhao.springboot.blog.domain.User;

import java.util.List;

/**
 * User Repository接口
 */
public interface UserRepository {
    /**
     * 创建或修改用户
     *
     * @return
     */
    User saveOrUpdateUser(User user);



    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 得到用户列表
     *
     * @return
     */
    List<User> getUsers();


}
