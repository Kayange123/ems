package com.kayange.ems.dto;

import com.kayange.ems.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

@Setter
@AllArgsConstructor
@Builder
public class PageResponse{
    private boolean first;
    private boolean last;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private Object data;

    public static PageResponse create(@NonNull Page<?> page, Object data){
        return PageResponse.builder()
                .first(page.isFirst())
                .last(page.isLast())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .data(data)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements()).build();
    }
}
