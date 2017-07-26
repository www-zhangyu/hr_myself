package ytu.icoding.entity;



/**
 * 员工实发工资档案表
 * @author haier
 *
 */
public class UserWageRecord {
	private int userWageRecordId;
	private int userId;//员工id
	private String userName;
	private int depId;//部门Id
	private int posId;//岗位id
	private int wageTypeId;//帐套ID
	private double basePay;//基本工资
	private double insuranceHouseFund;//五险一金
	private int leave;//请假
	private int late;//迟到
	private int leaveEarly;//早退
	private double extraWork;//加班
	private double bonus;//奖金
	private double annualPay;//年薪
	private double timeWork;//计时工作
	
	private  double paidWage;//实发工资

	private String grantTime;//发放时间

	

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
