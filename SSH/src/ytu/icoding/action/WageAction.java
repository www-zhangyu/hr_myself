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
	 * ��ѯԱ������
	 * @return
	 * @throws IOException 
	 */
	public void listUserWage() throws IOException{
		

		System.out.println("------------------------------------");
		String result;
		
		PrintWriter out = response.getWriter();
		
	
		///��ȡҳ�洫�ݵĲ���
		
		int curPage = Integer.valueOf(request.getParameter("curPage"));//��ǰҳ
		
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));//ÿҳ��С
		
		String userName =request.getParameter("userName");
		
		String depName =request.getParameter("depName");
		
		String date =request.getParameter("date");
		
		System.out.println("curPage:"+curPage+"   pageSize:"+pageSize);
		
		List<WageStatistics> wages = wageStatisticsService.listAllUserWage(userName, depName, date);
		int totalCount = wages.size();///�ܼ�¼��

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
	 * �������ʱ�
	 * @return
	 * @throws Exception 
	 */
	public void exportSalary() throws Exception{
		
		
		String userName =request.getParameter("userName");
		
		String depName =request.getParameter("depName");
		
		String date =request.getParameter("date");
		
		List<WageStatistics> wages = wageStatisticsService.listAllUserWage(userName, depName, date);
		
		response.reset();
		
		String filename=new String(("���ʱ�").getBytes("GBK"),"ISO-8859-1");
		
		response.addHeader("Content-Disposition", "attachment;filename="+filename+".xlsx");// �ļ����� 
		
		response.setContentType("application/vnd.ms-excel; charset=gbk");// �������ļ����ͣ�����Ϊexcel
		
		if(wages == null || wages.size() == 0){  
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
        cell.setCellValue("Ӧ������");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 4);
        cell.setCellValue("����һ��");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 5);
        cell.setCellValue("�������");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("ʵ������");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("��������");  
        cell.setCellStyle(style);
      //��Ԫ�����������
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
            //Ĭ�ϵ�����E����
            FileOutputStream out = new FileOutputStream("D://Ա�����ʱ�"+new Date().getSeconds()+".xls");
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

		
		
		
		
	
	public WageStatisticsService getWageStatisticsService() {
		return wageStatisticsService;
	}

	public void setWageStatisticsService(WageStatisticsService wageStatisticsService) {
		this.wageStatisticsService = wageStatisticsService;
	}

	
	

}
