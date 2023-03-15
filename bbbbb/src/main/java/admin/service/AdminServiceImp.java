package admin.service;

import java.util.List;

import admin.dao.AdminDAO;
import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;


//관리자 관련 서비스: 회원가입,	로그인, 정보수정, 관리자 탈퇴. DB에 정보를 insert, select, update, delete 한다.
//AuthInfo: 회원의 정보값을 세션에 저장 하기 위해 사용.
public class AdminServiceImp implements AdminService {

	private AdminDAO adminDao;
	
	public AdminServiceImp() {

	}

	// adminServiceBean을 생성하고 properties에서 호출.
	public void setAdminDao(AdminDAO adminDao) {
		this.adminDao = adminDao;
	}

	// 관리자 추가
	@Override
	public adminAuthInfo addAdminProcess(AdminDTO dto) {
		adminDao.addAdmin(dto);
		return new adminAuthInfo(dto.getadmin_id(), dto.getadmin_password());
	}


	// 관리자 로그인
	@Override
	public AdminDTO loginProcess(adminAuthInfo login) {
		AdminDTO admin = adminDao.selectByAdminId(login);
		System.out.println("서비스실행");
		
		return admin;
		// TODO Auto-generated method stub

	}

	// 관리자 정보 수정
	@Override
	public AdminDTO updateAdminProcess(String adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	// 관리자 정보를 세션에 저장하기 위해 인증 정보 형태로 리턴
	@Override
	public adminAuthInfo updateAdminProcess(AdminDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	// 관리자 탈퇴
	@Override
	public adminAuthInfo deleteAdminProcess(String adminId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int idcheck(String adminId) {
		// TODO Auto-generated method stub
		System.out.println("서비스 실행됨");
		return adminDao.idcheck(adminId);
	}
	
	@Override
	public List<AdminDTO> printAdminService() {
		// TODO Auto-generated method stub
		return adminDao.printAdmin();
	}
	@Override
	public List<AdminDTO> printUserService() {
		// TODO Auto-generated method stub
		return adminDao.printUser();
	}
}
