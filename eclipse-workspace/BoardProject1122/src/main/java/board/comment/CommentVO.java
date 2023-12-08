package board.comment;

import java.sql.Date;

import lombok.Data;

@Data
public class CommentVO {
	  private   String  cidx;
	  private   String  comment_idx;
	  private   String  commentContent;
	  private   Date    commentDate;
}
