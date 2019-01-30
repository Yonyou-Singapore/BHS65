package nc.vo.arap.vehicle;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class VehicleInfoVO {
	private String pk_vehicle;
	private String payNo;
	private UFDate payBillDate;
	private String startDate;
	private String endDate;
	private String description;
	private String payBillID;
	private String contractNo;
	private UFDouble amount;
	private String supplier;
	private String invoiceNo;
	private String part;
	
	public String getPk_vehicle() {
		return pk_vehicle;
	}
	public void setPk_vehicle(String pk_vehicle) {
		this.pk_vehicle = pk_vehicle;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public UFDate getPayBillDate() {
		return payBillDate;
	}
	public void setPayBillDate(UFDate payBillDate) {
		this.payBillDate = payBillDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPayBillID() {
		return payBillID;
	}
	public void setPayBillID(String payBillID) {
		this.payBillID = payBillID;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public UFDouble getAmount() {
		return amount;
	}
	public void setAmount(UFDouble amount) {
		this.amount = amount;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}

}
