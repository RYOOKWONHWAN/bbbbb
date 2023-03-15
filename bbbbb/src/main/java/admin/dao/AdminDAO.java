package admin.dao;

import java.util.List;

import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;



public interface AdminDAO {
	
	// 관리자 추가
	public int addAdmin(AdminDTO dto);
	public AdminDTO selectByAdminId(adminAuthInfo auth);
	//관리자 수정(비밀번호 변경)
	public void updateAdmin(AdminDTO dto);
	
	// 관리자 삭제
	public void deleteAdmin(AdminDTO dto);
	public int idcheck(String adminId);
	public List<AdminDTO> printAdmin();
	public List<AdminDTO> printUser();
}
