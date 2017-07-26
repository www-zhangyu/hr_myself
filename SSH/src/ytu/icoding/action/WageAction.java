package ytu.icoding.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;

import ytu.icoding.entity.Attendance;
import ytu.icoding.entity.WageStatistics;
import ytu.icoding.service.WageStatisticsService;
import ytu.icoding.util.Tools;

@Controller
public class WageAction extends StandardAction{
	
	@Resource
	private WageStatisticsService wageStatisticsService;
	
	/**
	 * 查询员工工资
	 * @return
	 * @throws IOException 
	 */
	public void listUserWage() throws IOException{
		

		System.out.println("------------------------------------");
		String result;
		
		PrintWriter out = response.getWriter();
		
	
		///获取页面传递的参数
		
		int curPage = Integer.valueOf(request.getParameter("curPage"));//当前页
		
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));//每页大小
		
		String userName =request.getParameter("userName");
		
		String depName =request.getParameter("depName");
		
		String date =request.getParameter("date");
		
		System.out.println("curPage:"+curPage+"   pageSize:"+pageSize);
		
		List<WageStatistics> wages = wageStatisticsService.listAllUserWage(userName, depName, date);
		int totalCount = wages.size();///总记录数

		System.out.println("totalCount:"+totalCount);
		int totalPage = 0;//总页数
		if (totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		}else {
			totalPage = totalCount / pageSize + 1;
		}

		System.out.println("total:"+totalPage);
		//加载当前页的数据
		int beginIndex = (curPage -1) * pageSize;
		
		List<WageStatistics> listWageByPage = wageStatisticsService.listWageStatisticsByPage(beginIndex, pageSize, userName, depName, date);
		
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(listWageByPage);
		
		result = jsonArray.toString();
		String jsonResult = "{}";
		
		jsonResult = "{";
		jsonResult += "\"totalPage\":" + totalPage + ',';
		jsonResult += "\"data\":" + result + "}";
		
		out.write(jsonResult);
		
		
		
		System.out.println("jsonResult:"+jsonResult);

	
	}
	
	
	/**
	 * 导出工资表
	 * @return
	 * @throws Exception 
	 */
	public void exportSalary() throws Exception{
		
		
		String userName =request.getParameter("userName");
		
		String depName =request.getParameter("depName");
		
		String date =request.getParameter("date");
		
		List<WageStatistics> wages = wageStatisticsService.listAllUserWage(userName, depName, date);
		
		response.reset();
		
		String filename=new String(("工资表").getBytes("GBK"),"ISO-8859-1");
		
		response.addHeader("Content-Disposition", "attachment;filename="+filename+".xlsx");// 文件标题 
		
		response.setContentType("application/vnd.ms-excel; charset=gbk");// 导出的文件类型，次数为excel
		
		if(wages == null || wages.size() == 0){  
            return ;  
        } 
		
		// 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("考勤信息表");
       
        // 生成一个样式  
        HSSFCellStyle style = wb.createCellStyle();
        //创建第一行（也可以称为表头）
        HSSFRow row = sheet.createRow(0);
        //样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("员工编号"); 
        cell.setCellStyle(style);
        cell = row.createCell( (short) 1);  
        cell.setCellValue("员工姓名");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("所在部门");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("应发工资");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 4);
        cell.setCellValue("五险一金");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 5);
        cell.setCellValue("奖惩情况");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("实发工资");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("结算日期");  
        cell.setCellStyle(style);
      //向单元格里填充数据
        for (int i = 0; i < wages.size(); i++) {
	         row = sheet.createRow(i + 1);
	         row.createCell(0).setCellValue(wages.get(i).getUserId());
	         row.createCell(1).setCellValue(wages.get(i).getUserName());
	         row.createCell(2).setCellValue(wages.get(i).getDepName());
	         row.createCell(3).setCellValue(wages.get(i).getShouldWage());
	         row.createCell(4).setCellValue(wages.get(i).getInsuranceHouseFund());
	         row.createCell(5).setCellValue(wages.get(i).getBonus());
	         row.createCell(6).setCellValue(wages.get(i).getPaidWage());
	         row.createCell(7).setCellValue(wages.get(i).getGrantDate());
        }
        System.out.println("------------------------------------------------");
        try {
            //默认导出到E盘下
            FileOutputStream out = new FileOutputStream("D://员工工资表"+new Date().getSeconds()+".xls");
            wb.write(out);
            out.close();
            JOptionPane.showMessageDialog(null, "导出成功!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        }
	}

		
		
		
		
	
	public WageStatisticsService getWageStatisticsService() {
		return wageStatisticsService;
	}

	public void setWageStatisticsService(WageStatisticsService wageStatisticsService) {
		this.wageStatisticsService = wageStatisticsService;
	}

	
	

}
