package ncs_test_jhs.ui.service;

import java.util.List;

import ncs_test_jhs.dao.TitleDao;
import ncs_test_jhs.dao.impl.TitleDaoImpl;
import ncs_test_jhs.dto.Title;
//데이터 베이스와 연결하는 서비스 
public class TitleUiService {
    private TitleDao titleDao;

	public TitleUiService() {
		titleDao = TitleDaoImpl.getInstance();
	}
	
	//목록 전부다 불러오기 
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}
    //제거하기
	public void removeTitle(Title title) {
		titleDao.deleteTitle(title);
	}
	//수정하기
	public void modifyTitle(Title title) {
		titleDao.updateTitle(title);
	}
	//추가하기
    public void addUpTitle(Title title) {
    	titleDao.insertTitle(title);
    }
    public int setFirstNum() {
    	return titleDao.selectTitleByAllForInt();
    }
   
   
}
