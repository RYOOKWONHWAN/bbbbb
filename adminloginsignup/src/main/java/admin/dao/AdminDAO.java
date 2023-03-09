package admin.dao;

import admin.dto.AdminDTO;



public interface AdminDAO {
	
	// 관리자 추가
	public int addAdmin(AdminDTO dto);
	public AdminDTO selectByAdminId(String adminId);
	//관리자 수정(비밀번호 변경)
	public void updateAdmin(AdminDTO dto);
	
	// 관리자 삭제
	public void deleteAdmin(AdminDTO dto);
	public int idcheck(String adminId);

}
