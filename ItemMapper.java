package com.example.bookreview;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ItemMapper implements RowMapper {
    
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException{
        Item item = new Item();
        item.setbook_Id(rs.getString("book_id"));
        item.setbook_title(rs.getString("book_title"));
        item.setbook_details(rs.getString("book_details"));
        item.setbook_photo(rs.getString("book_photo"));
        item.setbook_author(rs.getString("book_author"));
        item.setbook_genre(rs.getString("book_genre"));

        return item;
    }
}
    

