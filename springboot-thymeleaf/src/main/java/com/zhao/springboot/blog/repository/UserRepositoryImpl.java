package com.zhao.springboot.blog.repository;

import com.zhao.springboot.blog.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static AtomicLong counter = new AtomicLong(); //用于id
    private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<>(); //用于保存用户


    @Override
    public User saveOrUpdateUser(User user) {
        Long id = user.getId();
        if (id == null) { //新建
            id = counter.incrementAndGet();
            user.setId(id);
        }
        this.userMap.put(id, user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        if (id != null) {
            userMap.remove(id);
        }
    }

    @Override
    public User getUserById(Long id) {
        return userMap.get(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(this.userMap.values());
    }
}
