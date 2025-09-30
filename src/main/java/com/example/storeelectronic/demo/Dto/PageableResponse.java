package com.example.storeelectronic.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse <V>{
  private List<V> data;
  private int pagenumber;
  private int pagesize;
  private int  totalElement;
  private int totalSize;
  private boolean lastpage;



}
