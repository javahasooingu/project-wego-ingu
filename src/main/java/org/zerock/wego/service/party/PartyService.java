package org.zerock.wego.service.party;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.zerock.wego.domain.party.PartyDTO;
import org.zerock.wego.domain.party.PartyViewVO;
import org.zerock.wego.exception.NotFoundPageException;
import org.zerock.wego.exception.OperationFailException;
import org.zerock.wego.exception.ServiceException;
import org.zerock.wego.mapper.PartyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequiredArgsConstructor
@Service
public class PartyService {
	
	private final PartyMapper partyMapper;


	public List<PartyViewVO> getList() throws ServiceException {
		log.trace("getList() invoked.");
		try {
			return this.partyMapper.selectAll();
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getList
	
	public Set<PartyViewVO> getRandom10List() throws ServiceException {
		log.trace("getRandom10List() invoked.");
		try {
			return this.partyMapper.selectRandom10();
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getRandom10List
	
	public Integer getUserIdByPartyId(Integer partyId) {
		log.trace("getUserIdByPartyId() invoked.");
		try {
			
			return this.partyMapper.selectUserIdByPartyId(partyId);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getUserIdByPartyId
	
	
	// 모집글 상세 조회 
	public PartyViewVO getById(Integer partyId) throws Exception{
//		log.trace("getById({}) invoked.", partyId);
		try {
			PartyViewVO party = this.partyMapper.selectById(partyId);

			if(party == null) {
				 throw new NotFoundPageException();
			}// if
			 
			this.partyMapper.visitCountUp(partyId); //조회수증가.
			
			return party;
			
		} catch (NotFoundPageException e) {
			throw e;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}// try-catch
	}// getById
	
	
	public boolean register(PartyDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		try {
			return this.partyMapper.insert(dto) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // register
	
	
	public boolean modify(PartyDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		try {
			return this.partyMapper.update(dto) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // modify
	
	
	// 모집글 삭제 
	public void removeById(Integer partyId) throws Exception{
//		log.trace("isRemovedById({}) invoked.", partyId);
		try {
			boolean isExist = this.partyMapper.isExist(partyId);
			
			if(!isExist) {
				throw new NotFoundPageException();
			}// if
			
			this.partyMapper.deleteById(partyId);
			isExist = this.partyMapper.isExist(partyId);
			
			if(isExist) {
				throw new OperationFailException();
			}// if
			
		} catch (NotFoundPageException e) {
			throw e;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}// try-catch
	}// removeParty
	
}// end class