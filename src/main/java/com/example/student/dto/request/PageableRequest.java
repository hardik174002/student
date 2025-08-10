package com.example.student.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageableRequest {
    
    @Min(value = 1,message = "Minimmum page number is 1")
    @JsonProperty("page_number")
    int pageNo;

    @Min(value = 10,message = "Minimmum number of records is 10")
    @JsonProperty("number_of_records")
    int noOfRecords;
}
