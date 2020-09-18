package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDto {
    private List<QuestionDto> questionDtos;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private Integer totalPage;

    private List<Integer> pages =new ArrayList<>();

    public void setPageAllthing(Integer totalCount, Integer page, Integer size) {

        Integer totalPage1 = 0;
        if(totalCount%size==0){
            totalPage1 = totalCount/size;
        }else {
            totalPage1=totalCount/size+1;
        }
        totalPage=totalPage1;

        this.page=page;
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }


        if(page==1){
            showPrevious=false;

        }else{
            showPrevious=true;
        }
        if(page==totalPage){
            showNext=false;
        }else{
            showNext    =true;
        }
        if(pages.contains(1)){
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }

    }
}
