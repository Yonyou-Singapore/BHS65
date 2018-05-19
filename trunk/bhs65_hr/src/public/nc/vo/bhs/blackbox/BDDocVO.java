package nc.vo.bhs.blackbox;


public class BDDocVO implements Comparable<BDDocVO>{
	private String id;
	private String code;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int compareTo(BDDocVO o) {
		return this.code.compareTo(o.getCode());
	}
	
	

}
