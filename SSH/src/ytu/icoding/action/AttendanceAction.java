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
	 * ǩ��
	 * @throws Exception
	 */
	public void checkIn() throws Exception{
		
		PrintWriter out = response.getWriter();
		
		///��ȡ��ǰ�û�
		User user = (User)request.getSession().getAttribute("CurrentUser");
		
		//�ڿ��ڱ��в�ѯ��ǰ�û������Ƿ���ǩ��������ǩ����
		Attendance attendanceByUser = attendanceService.selectAttendanceByUser(user.getUserId());
		if(attendanceByUser != null){
			out.write("������ǩ��");
			return ;
		} 
		int checkInStatus = 0 ;
		
		System.out.println("��ǰ�û���"+user.getUserId());
		///��ȡ��ǰʱ��
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());
		String checkInTime = formatter.format(curDate);
		
		System.out.println("ǩ��ʱ�䣺"+checkInTime);
		
		long curTime = formatter.parse(checkInTime).getTime();
		long ruleTime = formatter.parse("15:55:00").getTime();///��˾�涨�ϰ�ʱ��
		
		long difference = difference(curTime, ruleTime);
		
		System.out.println(difference);
		if( difference < 5) {
			checkInStatus = 1;
		}else if (difference > 5 && difference < 60){	///�ٵ�		
			checkInStatus = 2;
		}else if(difference > 60 ){///����
			checkInStatus = 3;
		}
		
		///����¼���뵽���ݿ���
		attendanceService.insertCheckIn(user.getUserId(), checkInTime,checkInStatus);
		out.write("ǩ���ɹ�");
		
		
	}
	
	public void checkOut() throws Exception{
		
		System.out.println("-----------------");
		
		PrintWriter out = response.getWriter();
		
		///��ȡ��ǰ�û�
		User user = (User)request.getSession().getAttribute("CurrentUser");
		
		Attendance attendanceByUser = attendanceService.selectAttendanceByUser(user.getUserId());
		
		System.out.println(attendanceByUser);
		
		if(attendanceByUser == null  ){
			out.write("����ǩ����");
			return;
		}else{
			
			int checkOutStatus = 0 ;
			///��ȡ��ǰʱ��
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());
			String checkOutTime =formatter.format(curDate);
			
			
			long curTime = formatter.parse(formatter.format(curDate)).getTime();
			long ruleTime = formatter.parse("17:00:00").getTime();///��˾�涨�°�ʱ��
			
			long difference = difference(curTime, ruleTime);
			
			if(difference < -5 ){
				checkOutStatus = -2;///����
			}else{
				checkOutStatus = 0;///����
			}
			
			out.write("ǩ�˳ɹ�");
			///�����û���ǩ��ʱ��
			attendanceService.updateCheckOut(user.getUserId(), checkOutTime, checkOutStatus);
			//attendanceService.insertCheckIn(user.getUserId(), checkOutTime,checkOutStatus);
			
		}
		
		
		
		
	}
	
	/**
	 * �����б�  ��ѯ����   Ĭ�ϵ�һҳ
	 * @return
	 * @throws Exception
	 */
	public void listAttendance() throws Exception{
		
		PrintWriter out = response.getWriter();
		
	
		///��ȡҳ�洫�ݵĲ���
		
		int curPage = Integer.valueOf(request.getParameter("curPage"));//��ǰҳ
		
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));//ÿҳ��С
		
		String userName =request.getParameter("userName");
		
		String depName =request.getParameter("depName");
		
		String date =request.getParameter("date");
		
		System.out.println("curPage:"+curPage+"   pageSize:"+pageSize);
		
		List<Attendance> attendances = attendanceService.selectAttendance(userName,depName,date);
		int totalCount = attendances.size();///�ܼ�¼��

		System.out.println("totalCount:"+totalCount);
		int totalPage = 0;//��ҳ��
		if (totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		}else {
			totalPage = totalCount / pageSize + 1;
		}

		System.out.println("total:"+totalPage);
		//���ص�ǰҳ������
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
	 * ��������
	 * @throws Exception
	 */
	public void exportExcel() throws Exception{
		
		////��Ҫ���������ݲ�ѯ����
		List<Attendance> deps = attendanceService.selectAttendanceOrderByDep();
		
		System.out.println("deps:"+deps.size());
		//OutputStream out = response.getOutputStream();
		response.reset();
		String filename=new String(("������Ϣ��").getBytes("GBK"),"ISO-8859-1");
		response.addHeader("Content-Disposition", "attachment;filename="+filename+".xlsx");// �ļ����� 
		response.setContentType("application/vnd.ms-excel; charset=gbk");// �������ļ����ͣ�����Ϊexcel
		if(deps == null || deps.size() == 0){  
            return ;  
        } 
		// ����һ��������
        HSSFWorkbook wb = new HSSFWorkbook();
        //����һ�����Ӳ�����
        HSSFSheet sheet = wb.createSheet("������Ϣ��");
       
        // ����һ����ʽ  
        HSSFCellStyle style = wb.createCellStyle();
        //������һ�У�Ҳ���Գ�Ϊ��ͷ��
        HSSFRow row = sheet.createRow(0);
        //��ʽ�������
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //����ͷ��һ��һ�δ�����Ԫ��
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("Ա�����"); 
        cell.setCellStyle(style);
        cell = row.createCell( (short) 1);  
        cell.setCellValue("Ա������");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("���ڲ���");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("��������");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 4);
        cell.setCellValue("ǩ��ʱ��");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 5);
        cell.setCellValue("ǩ��״̬");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("ǩ��ʱ��");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("ǩ��״̬");  
        cell.setCellStyle(style);
      //��Ԫ�����������
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
            //Ĭ�ϵ�����E����
            FileOutputStream out = new FileOutputStream("D://������Ϣ��"+new Date().getMonth()+".xls");
            wb.write(out);
            out.close();
            JOptionPane.showMessageDialog(null, "�����ɹ�!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "����ʧ��!");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "����ʧ��!");
            e.printStackTrace();
        }
         
	}
	
	/**
	 * ��������ʱ�������ٷ���
	 * @param curTime
	 * @param ruleTime
	 * @return
	 */
	private long difference(long curTime, long ruleTime){
		
		
		
		 long nd = 1000 * 24 * 60 * 60;// һ��ĺ�����     
	     long nh = 1000 * 60 * 60;// һСʱ�ĺ�����     
	     long nm = 1000 * 60;// һ���ӵĺ�����   
	    
	        long diff;    
	        long day = 0;    
	        long hour = 0;    
	        long min = 0;    
	        long sec = 0;    
	        // �������ʱ��ĺ���ʱ�����    
	       
	            diff = curTime - ruleTime;    
	            day = diff / nd;// ����������    
	            hour = diff % nd / nh + day * 24;// ��������Сʱ    
	            min = diff % nd % nh / nm + day * 24 * 60;// �������ٷ���    
	            // ������    
	            System.out.println("ʱ����" + day + "��" + (hour - day * 24) + "Сʱ"   
	                    + (min - day * 24 * 60) + "����" + sec + "�롣");    
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
