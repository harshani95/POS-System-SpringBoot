package com.springbootacademy.pos.dto.paginated;

import com.springbootacademy.pos.dto.response.ResponseOrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseOrderDetails {
    private List<ResponseOrderDetailsDTO> list;
    private long dataCount;
}
