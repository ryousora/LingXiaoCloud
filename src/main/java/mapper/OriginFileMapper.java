package mapper;

import model.OriginFile;

public interface OriginFileMapper {
    int deleteByPrimaryKey(Integer originFileId);

    int insert(OriginFile record);

    int insertSelective(OriginFile record);

    OriginFile selectByPrimaryKey(Integer originFileId);

    int updateByPrimaryKeySelective(OriginFile record);

    int updateByPrimaryKey(OriginFile record);

    /**
     * 通过查询文件的MD5码得到OriginFile
     */
    OriginFile getByFileMd5(String fileMd5);
}