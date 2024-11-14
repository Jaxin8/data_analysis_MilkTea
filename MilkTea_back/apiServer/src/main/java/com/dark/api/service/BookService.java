package com.dark.api.service;


import com.dark.api.dto.BookDto;
import com.dark.api.utils.PageResult;
import com.dark.api.vo.BookVO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookService {



    List<BookDto> selectBookOrderByMonth();
PageResult<BookVO> find();
}
