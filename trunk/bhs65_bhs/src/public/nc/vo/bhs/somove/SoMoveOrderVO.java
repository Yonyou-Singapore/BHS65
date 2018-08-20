package nc.vo.bhs.somove;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SoMoveOrderVO extends SuperVO{

	/**
	*子表主键
	*/
	public String billid_b;
	
	public String csaleorderbid;
	
	/**
	*上层单据主键
	*/
	public String billid;
	/**
	*时间戳
	*/
	public UFDateTime ts;
	public String getBillid_b() {
		return billid_b;
	}
	public void setBillid_b(String billid_b) {
		this.billid_b = billid_b;
	}
	public String getCsaleorderbid() {
		return csaleorderbid;
	}
	public void setCsaleorderbid(String csaleorderbid) {
		this.csaleorderbid = csaleorderbid;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public UFDateTime getTs() {
		return ts;
	}
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}
	
	 @Override
	    public IVOMeta getMetaData() {
	    return VOMetaFactory.getInstance().getVOMeta("BHS.SoMoveOrderVO");
	    }
	
	
}
