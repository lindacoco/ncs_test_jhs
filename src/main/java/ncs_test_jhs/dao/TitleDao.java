package ncs_test_jhs.dao;

import java.util.List;

import ncs_test_jhs.dto.Title;

public interface TitleDao {
    Title selectTitleByNo(Title title);
    List<Title> selectTitleByAll();
    
    int insertTitle(Title title);
    int updateTitle(Title title);
    int deleteTitle(Title title);
    
    int selectTitleByAllForInt();
    
}
