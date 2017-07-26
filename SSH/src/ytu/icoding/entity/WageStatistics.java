package ytu.icoding.entity;

/**
 * 	薪酬统计表
 * @author d
 *
 */
public class WageStatistics {
	private int wageStatisticsId;
	private int userId;//员工id
	private String userName;
	private String depName;//部门

	private double shouldWage;//应发工资
	private double insuranceHouseFund;//五险一金
	private  double paidWage;//实发工资
	
	private String grantDate;//发放时间
	
	private int bonus;///奖惩

	public int getWageStatisticsId() {
		return wageStatisticsId;
	}

	public void setWageStatisticsId(int wageStatisticsId) {
		this.wageStatisticsId = wageStatisticsId;
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

	

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public double getShouldWage() {
		return shouldWage;
	}

	public void setShouldWage(double shouldWage) {
		this.shouldWage = shouldWage;
	}

	public double getInsuranceHouseFund() {
		return insuranceHouseFund;
	}

	public void setInsuranceHouseFund(double insuranceHouseFund) {
		this.insuranceHouseFund = insuranceHouseFund;
	}

	public double getPaidWage() {
		return paidWage;
	}

	public void setPaidWage(double paidWage) {
		this.paidWage = paidWage;
	}

	public String getGrantDate() {
		return grantDate;
	}

	public void setGrantDate(String grantDate) {
		this.grantDate = grantDate;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	

	
	
}
