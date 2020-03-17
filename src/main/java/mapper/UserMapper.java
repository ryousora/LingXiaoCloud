package mapper;

import model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查询用户
     */
    User selectByUsername(String username);

    /**
     * 根据用户名和密码查询用户
     */
    User selectByPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 查询rowCount
     */
    int selectRowCount();
}