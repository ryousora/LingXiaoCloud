package mapper;

import model.Note;

import java.util.List;

public interface NoteMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKey(Note record);

    int delete(Integer id);

    List<Note> selectByUserId(Integer userId);
}