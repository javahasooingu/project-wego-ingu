package org.zerock.wego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.wego.domain.common.Criteria;
import org.zerock.wego.domain.common.UserVO;
import org.zerock.wego.domain.profile.ProfileCommentVO;
import org.zerock.wego.domain.profile.ProfileVO;


public interface ProfileMapper {
	
	// 유저 정보 가져오기 
	public abstract List<UserVO> selectUserId(@Param("userId")Integer userId);
	
	// 1. 작성 글에 대한 전체목록조회 : 페이징 처리적용
	public abstract List<ProfileVO> selectAll(@Param("userId")Integer userId, @Param("cri")Criteria cri);

	// 2. 교집합에 있는 게시물 건수 반환( SAN_PARTY_V 테이블만 게시물 수를 계산)
	public abstract Integer selectTotalCount(@Param("userId")Integer userId);	

	// 3. 작성 댓글에 대한 전체목록조회 : 페이징 처리적용
	public abstract List<ProfileCommentVO> selectAllComment(@Param("userId")Integer userId,@Param("cri2")Criteria cri2); 

	// 4. 댓글 게시물 건수 반환
	public abstract Integer selectTotalCountComment(@Param("userId")Integer userId); 		
} // end interface
