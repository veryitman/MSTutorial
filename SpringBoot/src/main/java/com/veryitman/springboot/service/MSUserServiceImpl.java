package com.veryitman.springboot.service;

import com.veryitman.springboot.model.MSUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MSUserServiceImpl implements MSUserService {

    private static final String USER_TABLE_NAME = "user";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createUserTable() {
        log.debug("MSBlog, create user's table.");

        // 创建user表
        String create_table_sql = "CREATE TABLE IF NOT EXISTS `user`(" +
                "`id` INT UNSIGNED AUTO_INCREMENT," +
                "`user_id` INT UNSIGNED," +
                "`user_name` VARCHAR(51) NOT NULL," +
                "`user_pwd` VARCHAR(21) NOT NULL," +
                "`user_nickname` VARCHAR(51) DEFAULT ''," +
                "`user_age` TINYINT UNSIGNED," +
                "`user_gender` TINYINT UNSIGNED," +
                "`user_motto` VARCHAR(120) DEFAULT ''," +
                "`user_phone` VARCHAR(27) DEFAULT ''," +
                "PRIMARY KEY ( `id` )" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
        jdbcTemplate.execute(create_table_sql);
    }

    @Override
    public int addUser(MSUser user) {
        log.debug("MSBlog, add user into user_table.");

        if (null == user) {
            return -1;
        }

        int affectedRows = jdbcTemplate.update("INSERT INTO user (" +
                        "user_id, " +
                        "user_name, " +
                        "user_pwd, " +
                        "user_nickname, " +
                        "user_age, " +
                        "user_gender, " +
                        "user_motto, " +
                        "user_phone" +
                        ") " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                user.getUserID(),
                user.getAccountName(),
                user.getAccountPwd(),
                user.getNickName(),
                user.getAge(),
                user.getGender(),
                user.getMotto(),
                user.getPhone());

        if (affectedRows > 0) {
            log.info("MSBlog, insert data to user_table successfully.");
        } else {
            log.warn("MSBlog, insert data to user_table failed.");
        }

        return affectedRows;
    }

    @Override
    public int deleteByUid(Integer uid) {
        log.info("MSBlog, delete user by uid.");
        int affectedRows = jdbcTemplate.update("DELETE FROM user WHERE user_id = ?", uid);
        return affectedRows;
    }

    @Override
    public List<Map> queryUserByUid(Integer uid) {
        log.info("MSBlog, query user by uid.");
        List list;
        list = jdbcTemplate.queryForList("SELECT user_id, user_name, user_pwd, user_nickname, user_age, user_gender, user_motto, user_phone FROM user WHERE user_id = ?", uid);
        if (null != list && list.size() > 0) {
            log.info(list.get(0).toString());
        } else {
            log.warn("MSBlog, no data.");
        }
        return list;
    }

    @Override
    public List<Map> queryUserByUserName(String userName) {
        log.info("MSBlog, query user by username.");
        List list = null;
        if (null == userName || userName.length() <= 0) {
            log.error("MSBlog, User name must be not empty.");
            return list;
        }
        String sql = "SELECT user_id, user_name, user_pwd, user_nickname, user_age, user_gender, user_motto, user_phone FROM  user WHERE user_name = \'" + userName + "\'";
        list = jdbcTemplate.queryForList(sql);
        if (null != list && list.size() > 0) {
            log.info(list.get(0).toString());
        } else {
            log.warn("MSBlog, no data.");
        }
        return list;
    }

    @Override
    public int deleteUserTable() {
        String sql = "DROP TABLE " + USER_TABLE_NAME;
        int result;
        try {
            jdbcTemplate.execute(sql);
            result = 0;
        } catch (Exception e) {
            result = -1;
            log.error("MSBlog, drop user table happends error: " + e.toString());
        }

        return result;
    }
}
