package ytu.icoding.entity;



/**
 * Ա��ʵ�����ʵ�����
 * @author haier
 *
 */
public class UserWageRecord {
	private int userWageRecordId;
	private int userId;//Ա��id
	private String userName;
	private int depId;//����Id
	private int posId;//��λid
	private int wageTypeId;//����ID
	private double basePay;//��������
	private double insuranceHouseFund;//����һ��
	private int leave;//���
	private int late;//�ٵ�
	private int leaveEarly;//����
	private double extraWork;//�Ӱ�
	private double bonus;//����
	private double annualPay;//��н
	private double timeWork;//��ʱ����
	
	private  double paidWage;//ʵ������

	private String grantTime;//����ʱ��

	

	public int getUserWageRecordId() {
		return userWageRecordId;
	}

	public void setUserWageRecordId(int userWageRecordId) {
		this.userWageRecordId = userWageRecordId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public int getPosId() {
		return posId;
	}

	public void setPosId(int posId) {
		this.posId = posId;
	}

	public int getWageTypeId() {
		return wageTypeId;
	}

	public void setWageTypeId(int wageTypeId) {
		this.wageTypeId = wageTypeId;
	}

	public double getBasePay() {
		return basePay;
	}

	public void setBasePay(double basePay) {
		this.basePay = basePay;
	}

	public double getInsuranceHouseFund() {
		return insuranceHouseFund;
	}

	public void setInsuranceHouseFund(double insuranceHouseFund) {
		this.insuranceHouseFund = insuranceHouseFund;
	}

	public int getLeave() {
		return leave;
	}

	public void setLeave(int leave) {
		this.leave = leave;
	}

	public int getLate() {
		return late;
	}

	public void setLate(int late) {
		this.late = late;
	}

	public int getLeaveEarly() {
		return leaveEarly;
	}

	public void setLeaveEarly(int leaveEarly) {
		this.leaveEarly = leaveEarly;
	}

	public double getExtraWork() {
		return extraWork;
	}

	public void setExtraWork(double extraWork) {
		this.extraWork = extraWork;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getAnnualPay() {
		return annualPay;
	}

	public void setAnnualPay(double annualPay) {
		this.annualPay = annualPay;
	}

	public double getTimeWork() {
		return timeWork;
	}

	public void setTimeWork(double timeWork) {
		this.timeWork = timeWork;
	}

	public double getPaidWage() {
		return paidWage;
	}

	public void setPaidWage(double paidWage) {
		this.paidWage = paidWage;
	}

	public String getGrantTime() {
		return grantTime;
	}

	public void setGrantTime(String grantTime) {
		this.grantTime = grantTime;
	}
	
	
	
	
}
