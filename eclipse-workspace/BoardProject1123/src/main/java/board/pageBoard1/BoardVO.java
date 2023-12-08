package board.pageBoard1;

import lombok.Data;

@Data
public class BoardVO {
	  private   String  idx;
	  private   String  sname;
	  private   String  title;
	  private   String  content;
	  private   String  img;
	  private   int  cnt;
	  
	  private   int  rownum;
	  private   int  rnum;
	  private   int  pageSize;
	  private   int  start;
	  private   int  tc;
	  
	  private   String  ch1; 
	  private   String  ch2;
}
