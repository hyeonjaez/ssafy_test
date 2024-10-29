package com.ssafy.test.resource.model.mapper;

import java.util.List;

import com.ssafy.test.resource.model.ResourceDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ResourceMapper {
    List<ResourceDto> getResourceList();
}
