package map.service;

import java.util.List;

import map.dto.MapDTO;

public interface MapService {
	public List<MapDTO> getlibs(String addr);
	public List<String> GetCenterX(String a);
}
