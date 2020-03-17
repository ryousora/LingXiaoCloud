package mapper;

import model.UserFolder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFolderMapper {
    int deleteByPrimaryKey(Integer folderId);

    int insert(UserFolder record);

    int insertSelective(UserFolder record);

    UserFolder selectByPrimaryKey(Integer folderId);

    int updateByPrimaryKeySelective(UserFolder record);

    int updateByPrimaryKey(UserFolder record);

    /**
     * 根据目录ID列出文件夹列表
     */
    List<UserFolder> listByFolderId(@Param("userId") Integer userId, @Param("folderId") Integer folderId);

    /**
     * 根据父级目录ID列出文件夹列表
     */
    List<UserFolder> listByParentId(@Param("userId") Integer userId, @Param("parentId") Integer parentId);

    /**
     * 列出相似(like)名称的文件夹（搜索）
     */
    List<UserFolder> listByName(@Param("userId") Integer userId, @Param("name") String name);
}