package nc.vo.so.m32.util;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

public class SettleReturnVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1004415748681445569L;
	
	private UFDouble settleQty;
	private UFDouble settlePrice;
	public UFDouble getSettleQty() {
		return settleQty;
	}
	public void setSettleQty(UFDouble settleQty) {
		this.settleQty = settleQty;
	}
	public UFDouble getSettlePrice() {
		return settlePrice;
	}
	public void setSettlePrice(UFDouble settlePrice) {
		this.settlePrice = settlePrice;
	}

}
