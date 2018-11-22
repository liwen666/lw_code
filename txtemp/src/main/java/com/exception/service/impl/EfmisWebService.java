package com.exception.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;










import util.EfmisCommonUtils;
import util.ResultPO;
import util.UserUtil;

import com.exception.ServiceException;
import com.exception.po.UserInfo;
import com.exception.service.IDocBatchRelationService;
import com.exception.service.IEfmisWebService;


@Service
@Transactional(readOnly = true)
public class EfmisWebService implements IEfmisWebService {

	private static final Logger logger = Logger.getLogger(EfmisWebService.class);
	
	@Resource
	private IDocBatchRelationService docBatchRelationService;

	@Override
	public ResultPO getCirculateType(String docId, String currentDocId,
			String busiTypeId, String currentNodeId, String processDefId,
			String currentUserCode, String strTragetDocParams,
			String parentNodeId, String strExtendParam) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultPO delEfmisData(String docId, String currentDocId,
			String currentNodeId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultPO busiOperation(String docId, String targetDocIds,
			String operationType, String strExtendParam)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
