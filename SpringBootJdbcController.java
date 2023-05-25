package com.example.bookreview;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController 

public class SpringBootJdbcController { 
    @Autowired  
    JdbcTemplate jdbc; 

    @RequestMapping( path="/addReview", method = RequestMethod.POST)
    public String storeData(@RequestBody String request) throws IOException{
            System.out.print(request);
            ObjectMapper myMapper = new ObjectMapper();
            JsonNode extractedData = myMapper.readTree(request);
            String username = extractedData.get("username").asText();
            String reviewContent = extractedData.get("reviewContent").asText();
            String reviewProduct = extractedData.get("reviewbook").asText();
            System.out.println(username);
            System.out.println(reviewContent);
            System.out.println(reviewProduct);
            // replace with your table query
            jdbc.execute("INSERT INTO `sys`.`all_items` (`item_id`, `item_title`, `item_price`) VALUES ("+username+", "+reviewContent+", "+reviewProduct+")");
            return"data inserted Successfully";  
        }
    
    @RequestMapping( "/insert")
    public String index() { 
            jdbc.execute("INSERT INTO `sys`.`hala_book` (`book_title`, `book_details`,`book_author`,`book_genre`,`book_photo`) VALUES ('The Hunger Games', 'In a future North America, where the rulers of Panem maintain control through an annual televised survival competition pitting young people from each of the twelve districts against one another, sixteen-year-old Katnisss skills are put to the test when she voluntarily takes her younger sisters place.','Suzanne Collins','fiction','https://images.gr-assets.com/books/1447303603l/2767052.jpg');");
            return "Insertion successful";
    }

    @RequestMapping(path = "/getItems", method = RequestMethod.GET)
    public List<Item> getItems() throws IOException{
        List<Item> items = jdbc.query("SELECT * FROM `sys`.`review_hala`", new ItemMapper());
        return items;
    
    }
      

}