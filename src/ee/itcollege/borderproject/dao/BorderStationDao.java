package ee.itcollege.borderproject.dao;

import java.util.List;

import ee.itcollege.borderproject.model.BorderStation;

public interface BorderStationDao {

	List<BorderStation> getBorderStations();

	void saveBorderStation(BorderStation BorderStation) ;
	
	void saveBorderStations(List<BorderStation> BorderStations);
	
	BorderStation searchBorderStation(Integer id);
	
	void updateBorderStation(BorderStation BorderStation);
	
	void deleteBorderStation(Integer id);
	
}
