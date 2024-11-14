package com.dark.api.controller;



import com.dark.api.dto.BookDto;
import com.dark.api.service.BookService;
import com.dark.api.utils.PageResult;
import com.dark.api.utils.Result;
import com.dark.api.vo.BookVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;


    @GetMapping("/bookOrderByMonth")
    public Result<List<BookDto>> selectBookOrderByMonth(){
        List<BookDto> bookDtoList = bookService.selectBookOrderByMonth();
        return Result.ok("查询成功",bookDtoList);
    }
@GetMapping
    @ApiOperation("获取订单列表")
    public Result<List<BookVO>> list() {
        PageResult<BookVO> pageResult = bookService.find();
        return Result.ok("查询成功", pageResult.getList(), pageResult.getTotal());

    }

}
