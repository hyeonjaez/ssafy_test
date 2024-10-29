package com.ssafy.test.resource.model.service;

import java.util.List;

import com.ssafy.test.resource.model.ResourceDto;
import com.ssafy.test.resource.model.mapper.ResourceMapper;
import org.springframework.stereotype.Service;



@Service
public class ResourceServiceImpl implements ResourceService {

	private ResourceMapper resourceMapper;

	public ResourceServiceImpl(ResourceMapper resourceMapper) {
		this.resourceMapper = resourceMapper;
	}

	@Override
	public List<ResourceDto> getResourceList() {
		return resourceMapper.getResourceList();
	}

}
