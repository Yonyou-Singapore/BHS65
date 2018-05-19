package nc.ui.bhs.ref;

import nc.ui.scmpub.ref.AbstractListDocRefModel;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class JobOrderRefModel extends AbstractListDocRefModel<SaleOrderHVO> {

	@Override
	public boolean isMutilLangNameRef() {
		setMutilLangNameRef(false);
		return super.isMutilLangNameRef();
	}
	
	@Override
	public String[] getFieldCode() {
		return new String[] { SaleOrderHVO.VBILLCODE, SaleOrderHVO.VDEF14,
				"startdate", "enddate" };
	}

	@Override
	public String[] getFieldName() {
		return new String[] { "Job Order NO", "Skill Requirement",
				"Start Date/Time", "End Date/Time" };
	}

	@Override
	protected Class<SaleOrderHVO> getVOClass() {
		return SaleOrderHVO.class;
	}

	@Override
	public String[] getHiddenFieldCode() {

		return new String[] { SaleOrderHVO.CSALEORDERID };
	}

	@Override
	public String getPkFieldCode() {
		return SaleOrderHVO.CSALEORDERID;
	}

	@Override
	public String getRefCodeField() {
		return SaleOrderHVO.VBILLCODE;
	}

	@Override
	public String getRefNameField() {
		return SaleOrderHVO.VDEF14;
	}

	@Override
	public String getWherePart() {
		setOrderPart("startdate desc ");
		SqlBuilder sql = new SqlBuilder();
		sql.append(" dr=0 and ");
		sql.appendIDIsNotNull(SaleOrderHVO.VDEF14);
		return sql.toString();
	}

}
