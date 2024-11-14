package com.dark.api.service;



import com.dark.api.dto.VipDto;

import java.util.List;

public interface VipService {
    List<VipDto> selectVipOrderByMonth();

}
