package DBPKG.project;

public class TeacherVO {
  	int TEACHER_CODE ;
	String TEACHER_NAME;
	String CLASS_NAME;
	int CLASS_PRICE;
	int  TEACHER_REGIST_DATE;
	
	public int getTEACHER_CODE() {
		return TEACHER_CODE;
	}
	public void setTEACHER_CODE(int tEACHER_CODE) {
		TEACHER_CODE = tEACHER_CODE;
	}
	public String getTEACHER_NAME() {
		return TEACHER_NAME;
	}
	public void setTEACHER_NAME(String tEACHER_NAME) {
		TEACHER_NAME = tEACHER_NAME;
	}
	public String getCLASS_NAME() {
		return CLASS_NAME;
	}
	public void setCLASS_NAME(String cLASS_NAME) {
		CLASS_NAME = cLASS_NAME;
	}
	public int getCLASS_PRICE() {
		return CLASS_PRICE;
	}
	public void setCLASS_PRICE(int cLASS_PRICE) {
		CLASS_PRICE = cLASS_PRICE;
	}
	public int getTEACHER_REGIST_DATE() {
		return TEACHER_REGIST_DATE;
	}
	public void setTEACHER_REGIST_DATE(int tEACHAR_REGIST_DATE) {
		TEACHER_REGIST_DATE = tEACHAR_REGIST_DATE;
	}
	@Override
	public String toString() {
		return "TeacherVO [TEACHER_CODE=" + TEACHER_CODE + ", TEACHER_NAME=" + TEACHER_NAME + ", CLASS_NAME="
				+ CLASS_NAME + ", CLASS_PRICE=" + CLASS_PRICE + ", TEACHER_REGIST_DATE=" + TEACHER_REGIST_DATE + "]";
	}
	
	
}
