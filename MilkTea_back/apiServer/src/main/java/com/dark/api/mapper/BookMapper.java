package com.dark.api.mapper;


import com.dark.api.dto.BookDto;
import com.dark.api.vo.BookVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface BookMapper {

//    @Select("select * from book order by id asc ")
//    public List<Book> selectAllBook();

    @Select("SELECT MONTH(create_time) as month, sum(total) as totalPrice FROM book WHERE YEAR(create_time) = ${year} GROUP BY month ORDER BY month ASC")
    public List<BookDto> selectBookOrderByMonth(int year);

    List<BookVO> find();

}
