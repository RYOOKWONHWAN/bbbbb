package admin.service;

import java.util.List;

import admin.dao.AdminDAO;
import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;
import user.dto.UserDTO;

public interface AdminService {
	//	회원가입(관리자 추가)
	public adminAuthInfo addAdminProcess(AdminDTO dto);
	
	//	로그인 처리
	public AdminDTO loginProcess(adminAuthInfo dto);
	

	// 관리자 탈퇴
	
	public int idcheck(String adminId);
	public List<AdminDTO> printAdminService();
	public List<UserDTO> printUserService();
	public void deleteUserService(String id);
	public void deleteAdminService(String adminId);
	
	public List<UserDTO>searchUserIdService(String user_id);
	public List<AdminDTO> searchAdminIdService(String admin_id);
	public List<UserDTO> searchUserNameService(String user_name);
	public List<AdminDTO> searchAdminNameService(String admin_name);
	public int changePassService(String newpass,String admin_id);
	
	
	
	
	
	
	
	
	
	
	
}
