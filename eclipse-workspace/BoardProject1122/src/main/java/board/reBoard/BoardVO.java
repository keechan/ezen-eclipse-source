package board.reBoard;

import lombok.Data;

@Data
public class BoardVO {
  private   String  idx;
  private   String  sname;
  private   String  title;
  private   String  content;
  private   int  cnt;
  private   int  ref;
  private   int  re_step;
  private   int  re_level;
}
