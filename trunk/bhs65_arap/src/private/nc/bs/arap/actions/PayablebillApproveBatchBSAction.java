package nc.bs.arap.actions;

import java.util.HashSet;
import java.util.Set;

import nc.bs.arap.util.BillAmUtils;
import nc.bs.arap.util.BillPersistenceUtils;
import nc.bs.arap.util.BillStatusUtils;
import nc.bs.arap.validator.IValidatorCode;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.vo.arap.payable.AggPayableBillVO;
import nc.vo.arap.payable.PayableBillItemVO;
import nc.vo.arap.payable.PayableBillVO;
import nc.vo.arap.vehicle.VehicleInfoVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 *作用: <b>应收单审核</b></br> 适用范围:</br> <b>Ⅰ: 应收应付单据没有结算页签, 它的特点就是审批及生效</b></br>
 * <b>Ⅱ: </b></br> <b>Ⅲ : </b></br> <b>Ⅳ: </b></br>
 * 
 * @author liaobx 2010-8-7 下午04:54:31
 * @since 6.0
 */
public class PayablebillApproveBatchBSAction extends BillAbleApproveBatchBSAction {

	{
		validatorCode.add(IValidatorCode.PayFreezeFlag);
	}

	@Override
	protected AggregatedValueObject[] doApprove(AggregatedValueObject[] bills)
	throws BusinessException {
		// 设置审批及生效/ 包括包括了设置表头update/ 表体unchange
		BillStatusUtils.enApprove4EffectVODefVals(bills);
		// 持久化审批生效 或弃审批不生效
		return BillPersistenceUtils.persistenceApvAndEftVOs(bills);
	}

	@Override
	protected void doAfterApprove(AggregatedValueObject[] bills)
	throws BusinessException {
		super.doAfterApprove(bills);
		String pk_group = (String) bills[0].getParentVO().getAttributeValue(IBillFieldGet.PK_GROUP);
		new BillAmUtils(pk_group).saveRent(bills);
		
		//add chenth 20181202 BHS 生成车辆相关信息
		generateVehicleInfo(bills);
		//add end
	}

	private void generateVehicleInfo(AggregatedValueObject[] bills) {
		Set<String> vehicleLst = new HashSet<String>();
		String vehicle = null;
		for(AggregatedValueObject vo : bills){
			AggPayableBillVO apBillVo = (AggPayableBillVO)vo;
			PayableBillVO headVo = apBillVo.getHeadVO();
			PayableBillItemVO[] bodyvos  = apBillVo.getBodyVOs();
			for(PayableBillItemVO itemVo : bodyvos){
				vehicle = itemVo.getDef29();
				if(vehicle != null){
					vehicleLst.add(vehicle);
					VehicleInfoVO vehicleVo = new VehicleInfoVO();
					vehicleVo.setPk_vehicle(vehicle);
					vehicleVo.setPayBillDate(headVo.getBilldate());
					vehicleVo.setPayNo(headVo.getBillno());
					vehicleVo.setPayBillID(headVo.getPk_payablebill());
					vehicleVo.setStartDate(itemVo.getDef28());
					vehicleVo.setEndDate(itemVo.getDef27());
					vehicleVo.setDescription(itemVo.getDef30());
					vehicleVo.setContractNo(itemVo.getDef26());
					vehicleVo.setAmount(itemVo.getLocal_money_cr());
					vehicleVo.setSupplier(itemVo.getSupplier());
					vehicleVo.setInvoiceNo(itemVo.getInvoiceno());
					vehicleVo.setPart(itemVo.getMaterial());
				}
			}
		}
	}


}

