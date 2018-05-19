package nc.vo.bhs.blackbox;

import java.util.ArrayList;
import java.util.List;

public class SkillRequirementCntVO {
	
	private String skillid;
	private String certificateid;
	private Integer numberofpeople;
	private List<SkillRequirementVO> skillReqList;
	public String getSkillid() {
		return skillid;
	}
	public void setSkillid(String skillid) {
		this.skillid = skillid;
	}
	public Integer getNumberofpeople() {
		return numberofpeople;
	}
	public void setNumberofpeople(Integer numberofpeople) {
		this.numberofpeople = numberofpeople;
	}
	public List<SkillRequirementVO> getSkillReqList() {
		if(this.skillReqList == null){
			this.skillReqList = new ArrayList<SkillRequirementVO>();
		}
		return skillReqList;
	}
	public String getCertificateid() {
		return certificateid;
	}
	public void setCertificateid(String certificateid) {
		this.certificateid = certificateid;
	}
}
