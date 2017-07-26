package ytu.icoding.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import ytu.icoding.entity.User;
import ytu.icoding.service.AttendanceService;

@Controller("attendanceAction")
public class AttendanceAction extends StandardAction{
	
	

	private static final long serialVersionUID = 1L;
	@Resource
	private AttendanceService attendanceService;
	
	private String result;
	
	/**
	 * 签到
	 * @throws Exception
	 */
	public void checkIn() throws Exception{
		
		PrintWriter out = response.getWriter();
		
		///获取当前用户
		User user = (User)request.getSession().getAttribute("CurrentUser");
		
		//在考勤表中查询当前用户今天是否已签到，不能签两次
		Attendance attendanceByUser = attendanceService.selectAttendanceByUser(user.getUserId());
		if(attendanceByUser != null){
			out.write("今天已签到");
			return ;
		} 
		int checkInStatus = 0 ;
		
		System.out.println("当前用户："+user.getUserId());
		///获取当前时间
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());
		String checkInTime = formatter.format(curDate);
		
		System.out.println("签到时间："+checkInTime);
		
		long curTime = formatter.parse(checkInTime).getTime();
		long ruleTime = formatter.parse("15:55:00").getTime();///公司规定上班时间
		
		long difference = difference(curTime, ruleTime);
		
		System.out.println(difference);
		if( difference < 5) {
			checkInStatus = 1;
		}else if (difference > 5 && difference < 60){	///迟到		
			checkInStatus = 2;
		}else if(difference > 60 ){///旷工
			checkInStatus = 3;
		}
		
		///将记录插入到数据库中
		attendanceService.insertCheckIn(user.getUserId(), checkInTime,checkInStatus);
		out.write("签到成功");
		
		
	}
	
	public void checkOut() throws Exception{
		
		System.out.println("-----------------");
		
		PrintWriter out = response.getWriter();
		
		///获取当前用户
		User user = (User)request.getSession().getAttribute("CurrentUser");
		
		Attendance attendanceByUser = attendanceService.selectAttendanceByUser(user.getUserId());
		
		System.out.println(attendanceByUser);
		
		if(attendanceByUser == null  ){
			out.write("请先签到！");
			return;
		}else{
			
			int checkOutStatus = 0 ;
			///获取当前时间
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());
			String checkOutTime =formatter.format(curDate);
			
			
			long curTime = formatter.parse(formatter.format(curDate)).getTime();
			long ruleTime = formatter.parse("17:00:00").getTime();///公司规定下班时间
			
			long difference = difference(curTime, ruleTime);
			
			if(difference < -5 ){
				checkOutStatus = -2;///早退
			}else{
				checkOutStatus = 0;///正常
			}
			
			out.write("签退成功");
			///更新用户的签退时间
			attendanceService.updateCheckOut(user.getUserId(), checkOutTime, checkOutStatus);
			//attendanceService.insertCheckIn(user.getUserId(), checkOutTime,checkOutStatus);
			
		}
		
		
		
		
	}
	
	/**
	 * 考勤列表  查询条件   默认第一页
	 * @return
	 * @throws Exception
	 */
	public void listAttendance() throws Exception{
		
		PrintWriter out = response.getWriter();
		
	
		///获取页面传递的参数
		
		int curPage = Integer.valueOf(request.getParameter("curPage"));//当前页
		
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));//每页大小
		
		String userName =request.getParameter("userName");
		
		String depName =request.getParameter("depName");
		
		String date =request.getParameter("date");
		
		System.out.println("curPage:"+curPage+"   pageSize:"+pageSize);
		
		List<Attendance> attendances = attendanceService.selectAttendance(userName,depName,date);
		int totalCount = attendances.size();///总记录数

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
		List<Attendance> selectAttendanceByPage = attendanceService.selectAttendanceByPage(beginIndex, pageSize, userName, depName, date);
		
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(selectAttendanceByPage);
		
		result = jsonArray.toString();
		String jsonResult = "{}";
		
		jsonResult = "{";
		jsonResult += "\"totalPage\":" + totalPage + ',';
		jsonResult += "\"data\":" + result + "}";
		
		out.write(jsonResult);
		
		System.out.println("jsonResult:"+jsonResult);

	}
	
	
	/**
	 * 导出数据
	 * @throws Exception
	 */
	public void exportExcel() throws Exception{
		
		////将要导出的数据查询出来
		List<Attendance> deps = attendanceService.selectAttendanceOrderByDep();
		
		System.out.println("deps:"+deps.size());
		//OutputStream out = response.getOutputStream();
		response.reset();
		String filename=new String(("考勤信息表").getBytes("GBK"),"ISO-8859-1");
		response.addHeader("Content-Disposition", "attachment;filename="+filename+".xlsx");// 文件标题 
		response.setContentType("application/vnd.ms-excel; charset=gbk");// 导出的文件类型，次数为excel
		if(deps == null || deps.size() == 0){  
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
        cell.setCellValue("考勤日期");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 4);
        cell.setCellValue("签到时间");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 5);
        cell.setCellValue("签到状态");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("签退时间");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("签退状态");  
        cell.setCellStyle(style);
      //向单元格里填充数据
        for (int i = 0; i < deps.size(); i++) {
	         row = sheet.createRow(i + 1);
	         row.createCell(0).setCellValue(deps.get(i).getUserId());
	         row.createCell(1).setCellValue(deps.get(i).getUserName());
	         row.createCell(2).setCellValue(deps.get(i).getDepName());
	         row.createCell(3).setCellValue(deps.get(i).getCheckDate());
	         row.createCell(4).setCellValue(deps.get(i).getCheckInTime());
	         row.createCell(5).setCellValue(deps.get(i).getCheckInStatus());
	         row.createCell(6).setCellValue(deps.get(i).getCheckOutTime());
	         row.createCell(7).setCellValue(deps.get(i).getCheckOutStatus());
        }
        try {
            //默认导出到E盘下
            FileOutputStream out = new FileOutputStream("D://考勤信息表"+new Date().getMonth()+".xls");
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
	
	/**
	 * 计算两个时间相差多少分钟
	 * @param curTime
	 * @param ruleTime
	 * @return
	 */
	private long difference(long curTime, long ruleTime){
		
		
		
		 long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数     
	     long nh = 1000 * 60 * 60;// 一小时的毫秒数     
	     long nm = 1000 * 60;// 一分钟的毫秒数   
	    
	        long diff;    
	        long day = 0;    
	        long hour = 0;    
	        long min = 0;    
	        long sec = 0;    
	        // 获得两个时间的毫秒时间差异    
	       
	            diff = curTime - ruleTime;    
	            day = diff / nd;// 计算差多少天    
	            hour = diff % nd / nh + day * 24;// 计算差多少小时    
	            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟    
	            // 输出结果    
	            System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"   
	                    + (min - day * 24 * 60) + "分钟" + sec + "秒。");    
	            System.out.println("hour=" + hour + ",min=" + min);    
	            
	            if(hour != 0){
	            	min = hour * 60 + min;
	            }
	                return min;    
	     
	   
	  
		
	}

	public AttendanceService getAttendanceService() {
		return attendanceService;
	}

	public void setAttendanceService(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}
	
	

}
